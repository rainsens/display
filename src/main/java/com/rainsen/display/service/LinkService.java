package com.rainsen.display.service;

import com.rainsen.display.model.entity.Link;
import com.rainsen.display.model.request.LinkCreateRequest;
import com.rainsen.display.model.request.LinkUpdateRequest;
import com.rainsen.display.model.resource.LinkResource;
import org.springframework.data.domain.Page;

public interface LinkService {

    Page<LinkResource> index(Integer page, Integer size);

    LinkResource show(Long id);

    void create(LinkCreateRequest info);

    void update(LinkUpdateRequest info);

    void delete(Long id);
}
