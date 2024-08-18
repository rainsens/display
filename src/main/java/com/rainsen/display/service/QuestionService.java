package com.rainsen.display.service;

import com.rainsen.display.model.request.QuestionCreateRequest;
import com.rainsen.display.model.request.QuestionUpdateRequest;
import com.rainsen.display.model.resource.QuestionResource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {

    Page<QuestionResource> index(Integer page, Integer size);

    List<QuestionResource> survey(Long surveyId);

    QuestionResource show(Long id);

    void create(QuestionCreateRequest info);

    void update(QuestionUpdateRequest info);

    void delete(Long id);
}
