package com.rainsen.display.service;

import com.rainsen.display.model.entity.Project;
import com.rainsen.display.model.request.ProjectCreateRequest;
import com.rainsen.display.model.request.ProjectUpdateRequest;
import com.rainsen.display.model.resource.ProjectResource;
import org.springframework.data.domain.Page;

public interface ProjectService {

    Page<ProjectResource> index(Integer page, Integer size);

    ProjectResource show(Long id);

    void create(ProjectCreateRequest info);

    void update(ProjectUpdateRequest info);

    void delete(Long id);

    String qrcode(Long id);

    Project checkPermission(Long projectId);
}
