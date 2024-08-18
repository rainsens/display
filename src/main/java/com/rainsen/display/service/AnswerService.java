package com.rainsen.display.service;

import com.rainsen.display.model.request.AnswerCreateRequest;
import com.rainsen.display.model.request.AnswerUpdateRequest;
import com.rainsen.display.model.resource.AnswerResource;
import org.springframework.data.domain.Page;

public interface AnswerService {

    Page<AnswerResource> index(Integer page, Integer size);

    AnswerResource show(Long id);

    void create(AnswerCreateRequest info);

    void update(AnswerUpdateRequest info);

    void delete(Long id);
}
