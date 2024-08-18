package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Material;
import com.rainsen.display.model.repository.MaterialRepository;
import com.rainsen.display.model.request.MaterialCreateRequest;
import com.rainsen.display.model.request.MaterialUpdateRequest;
import com.rainsen.display.model.resource.MaterialResource;
import com.rainsen.display.service.MaterialService;
import com.rainsen.display.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImp implements MaterialService {

    private final MaterialRepository repository;
    private final ProjectService projectService;

    @Autowired
    public MaterialServiceImp(
            MaterialRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<MaterialResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Material> materials = repository.findAll(pageable);
        return materials.map(Material::toResource);
    }

    @Override
    public MaterialResource show(Long id) {
        Material material = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        MaterialResource materialResource = material.toResource();
        materialResource.setProject(material.getProject().toResource());
        return materialResource;
    }

    @Override
    public void create(MaterialCreateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Material material = new Material();
        BeanUtils.copyProperties(info, material);
        repository.save(material);
    }

    @Override
    public void update(MaterialUpdateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Material material = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, material);
        repository.save(material);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Material material = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(material.getProjectId());
        repository.deleteById(id);
    }
}
