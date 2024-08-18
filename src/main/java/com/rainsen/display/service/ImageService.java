package com.rainsen.display.service;

import com.rainsen.display.model.request.ImageCreateRequest;
import com.rainsen.display.model.request.ImageUpdateRequest;
import com.rainsen.display.model.resource.ImageResource;
import org.springframework.data.domain.Page;

public interface ImageService {

    Page<ImageResource> index(Integer page, Integer size);

    ImageResource show(Long id);

    void create(ImageCreateRequest info);

    void update(ImageUpdateRequest info);

    void delete(Long id);
}
