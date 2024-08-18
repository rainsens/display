package com.rainsen.display.service;

import com.rainsen.display.model.request.SlideCreateRequest;
import com.rainsen.display.model.request.SlideUpdateRequest;
import com.rainsen.display.model.resource.SlideResource;
import org.springframework.data.domain.Page;

public interface SlideService {

    Page<SlideResource> index(Integer page, Integer size);

    SlideResource show(Long id);

    void create(SlideCreateRequest info);

    void update(SlideUpdateRequest info);

    void delete(Long id);
}
