package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Question;
import com.rainsen.display.model.repository.AnswerRepository;
import com.rainsen.display.model.repository.QuestionRepository;
import com.rainsen.display.model.request.QuestionCreateRequest;
import com.rainsen.display.model.request.QuestionUpdateRequest;
import com.rainsen.display.model.resource.QuestionResource;
import com.rainsen.display.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {

    private final QuestionRepository repository;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionServiceImp(QuestionRepository repository, AnswerRepository answerRepository) {
        this.repository = repository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Page<QuestionResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Question> questions = repository.findAll(pageable);
        return questions.map(question -> {
            QuestionResource questionResource = question.toResource();
            questionResource.setSurvey(question.getSurvey().toResource());
            return questionResource;
        });
    }

    @Override
    public List<QuestionResource> survey(Long surveyId) {
        // All questions of a specific questionnaire.
        List<Question> questions = repository.findAllBySurvey_Id(surveyId);
        return questions.stream().map(Question::toResource).toList();
    }

    @Override
    public QuestionResource show(Long id) {
        Question question = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        QuestionResource questionResource = question.toResource();
        questionResource.setSurvey(question.getSurvey().toResource());
        return questionResource;
    }

    @Override
    public void create(QuestionCreateRequest info) {
        if (repository.existsBySurvey_IdAndQuestion(info.getSurveyId(), info.getQuestion())) {
            throw new DisException(DisExceptionEnum.REPETITIVE_OPERATION);
        }
        Question question = new Question();
        BeanUtils.copyProperties(info, question);
        repository.save(question);
    }

    @Override
    public void update(QuestionUpdateRequest info) {
        Question question = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, question);
        repository.save(question);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DisException(DisExceptionEnum.RECORD_NOT_EXISTS);
        }
        answerRepository.deleteAllByQuestion_Id(id);
        repository.deleteById(id);
    }
}
