package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.filter.UserFilter;
import com.rainsen.display.model.entity.Answer;
import com.rainsen.display.model.repository.AnswerRepository;
import com.rainsen.display.model.request.AnswerCreateRequest;
import com.rainsen.display.model.request.AnswerUpdateRequest;
import com.rainsen.display.model.resource.AnswerResource;
import com.rainsen.display.service.AnswerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImp implements AnswerService {

    private final AnswerRepository repository;

    @Autowired
    public AnswerServiceImp(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<AnswerResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Answer> answers = repository.findAll(pageable);
        return answers.map(Answer::toResource);
    }

    @Override
    public AnswerResource show(Long id) {
        Answer answer = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        AnswerResource answerResource = answer.toResource();
        answerResource.setUser(answer.getUser().toResource());
        answerResource.setSurvey(answer.getSurvey().toResource());
        answerResource.setQuestion(answer.getQuestion().toResource());
        return answerResource;
    }

    @Override
    public void create(AnswerCreateRequest info) {
        if (repository.existsBySurvey_IdAndQuestion_IdAndAnswer(
                info.getSurveyId(), info.getQuestionId(), info.getAnswer())
        ) {
            throw new DisException(DisExceptionEnum.REPETITIVE_OPERATION);
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(info, answer);
        answer.setUserId(UserFilter.user().getId());
        answer.setSurveyId(info.getSurveyId());
        answer.setQuestionId(info.getQuestionId());
        repository.save(answer);
    }

    @Override
    public void update(AnswerUpdateRequest info) {
        Answer answer = repository.findById(info.getId())
                .orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, answer);
        repository.save(answer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DisException(DisExceptionEnum.RECORD_NOT_EXISTS);
        }
        repository.deleteById(id);
    }
}
