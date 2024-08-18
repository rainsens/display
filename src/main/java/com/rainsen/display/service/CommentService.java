package com.rainsen.display.service;

import com.rainsen.display.model.request.CommentCreateRequest;
import com.rainsen.display.model.request.CommentUpdateRequest;
import com.rainsen.display.model.resource.CommentResource;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<CommentResource> index(Integer page, Integer size);

    Page<CommentResource> project(Long projectId, Integer page, Integer size);

    CommentResource show(Long id);

    void create(CommentCreateRequest info);

    void update(CommentUpdateRequest info);

    void delete(Long id);
}
