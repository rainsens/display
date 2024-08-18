package com.rainsen.display.service;

import com.rainsen.display.model.entity.Permit;
import com.rainsen.display.model.request.PermitCreateRequest;
import com.rainsen.display.model.request.PermitUpdateRequest;
import com.rainsen.display.model.resource.PermitResource;
import org.springframework.data.domain.Page;

public interface PermitService {

    Page<PermitResource> index(Integer page, Integer size);

    PermitResource show(Long id);

    void create(PermitCreateRequest info);

    void update(PermitUpdateRequest info);

    void delete(Long id);
}
