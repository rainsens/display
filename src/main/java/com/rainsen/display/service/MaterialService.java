package com.rainsen.display.service;

import com.rainsen.display.model.request.MaterialCreateRequest;
import com.rainsen.display.model.request.MaterialUpdateRequest;
import com.rainsen.display.model.resource.MaterialResource;
import org.springframework.data.domain.Page;

public interface MaterialService {

    Page<MaterialResource> index(Integer page, Integer size);

    MaterialResource show(Long id);

    void create(MaterialCreateRequest info);

    void update(MaterialUpdateRequest info);

    void delete(Long id);
}
