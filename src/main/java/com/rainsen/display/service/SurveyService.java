package com.rainsen.display.service;

import com.rainsen.display.model.request.SurveyCreateRequest;
import com.rainsen.display.model.request.SurveyUpdateRequest;
import com.rainsen.display.model.resource.SurveyResource;
import org.springframework.data.domain.Page;

public interface SurveyService {

    Page<SurveyResource> index(Integer page, Integer size);

    SurveyResource show(Long id);

    void create(SurveyCreateRequest info);

    void update(SurveyUpdateRequest info);

    void delete(Long id);

    String qrcode(Long id);
}
