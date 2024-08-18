package com.rainsen.display.service.imp;

import com.google.zxing.WriterException;
import com.rainsen.display.common.Constant;
import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Survey;
import com.rainsen.display.model.repository.AnswerRepository;
import com.rainsen.display.model.repository.QuestionRepository;
import com.rainsen.display.model.repository.SurveyRepository;
import com.rainsen.display.model.request.SurveyCreateRequest;
import com.rainsen.display.model.request.SurveyUpdateRequest;
import com.rainsen.display.model.resource.SurveyResource;
import com.rainsen.display.service.SurveyService;
import com.rainsen.display.util.QRCodeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class SurveyServiceImp implements SurveyService {

    private final SurveyRepository repository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public SurveyServiceImp(
            SurveyRepository repository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository
    ) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Page<SurveyResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Survey> surveys = repository.findAll(pageable);
        return surveys.map(Survey::toResource);
    }

    @Override
    public SurveyResource show(Long id) {
        Survey survey = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        return survey.toResource();
    }

    private void checkBeforeCreate(SurveyCreateRequest info) {
        if (repository.existsByTitle(info.getTitle())) {
            throw new DisException(DisExceptionEnum.SURVEY_NAME_EXISTS);
        }
    }

    @Override
    public void create(SurveyCreateRequest info) {
        checkBeforeCreate(info);
        Survey survey = new Survey();
        BeanUtils.copyProperties(info, survey);
        repository.save(survey);
    }

    @Override
    public void update(SurveyUpdateRequest info) {
        Survey survey = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, survey);
        repository.save(survey);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DisException(DisExceptionEnum.RECORD_NOT_EXISTS);
        }
        answerRepository.deleteAllBySurvey_Id(id);
        questionRepository.deleteAllBySurvey_Id(id);
        repository.deleteById(id);
    }

    @Override
    public String qrcode(Long id) {

        String surveyUrl = Constant.APP_URL + "/survey/" + id;
        String filename = "survey" + id + ".png";
        String savePath = Constant.FILE_UPLOAD_DIR + filename;
        String accessPath = Constant.FILE_ACCESS_PREFIX + filename;

        File file = new File(savePath);
        if (file.exists()) return accessPath;

        try {
            QRCodeUtil.generate(surveyUrl, 350, 350, savePath);
        } catch (WriterException | IOException e) {
            throw new DisException(DisExceptionEnum.FILE_WRITTEN_FAILED);
        }

        return accessPath;
    }
}
