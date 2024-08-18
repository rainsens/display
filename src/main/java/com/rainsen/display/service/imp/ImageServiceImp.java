package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Image;
import com.rainsen.display.model.repository.ImageRepository;
import com.rainsen.display.model.request.ImageCreateRequest;
import com.rainsen.display.model.request.ImageUpdateRequest;
import com.rainsen.display.model.resource.ImageResource;
import com.rainsen.display.service.ImageService;
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
public class ImageServiceImp implements ImageService {

    private final ImageRepository repository;
    private final ProjectService projectService;

    @Autowired
    public ImageServiceImp(
            ImageRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<ImageResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Image> images = repository.findAll(pageable);
        return images.map(Image::toResource);
    }

    @Override
    public ImageResource show(Long id) {
        Image image = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        ImageResource imageResource = image.toResource();
        imageResource.setProject(image.getProject().toResource());
        return imageResource;
    }

    @Override
    public void create(ImageCreateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Image image = new Image();
        BeanUtils.copyProperties(info, image);
        repository.save(image);
    }

    @Override
    public void update(ImageUpdateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Image image = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, image);
        repository.save(image);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Image image = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(image.getProjectId());
        repository.deleteById(id);
    }
}
