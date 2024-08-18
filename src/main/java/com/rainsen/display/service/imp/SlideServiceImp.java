package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Slide;
import com.rainsen.display.model.repository.SlideRepository;
import com.rainsen.display.model.request.SlideCreateRequest;
import com.rainsen.display.model.request.SlideUpdateRequest;
import com.rainsen.display.model.resource.SlideResource;
import com.rainsen.display.service.ProjectService;
import com.rainsen.display.service.SlideService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImp implements SlideService {

    private final SlideRepository repository;
    private final ProjectService projectService;

    @Autowired
    public SlideServiceImp(
            SlideRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<SlideResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Slide> slides = repository.findAll(pageable);
        return slides.map(Slide::toResource);
    }

    @Override
    public SlideResource show(Long id) {
        Slide slide = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        SlideResource slideResource = slide.toResource();
        slideResource.setProject(slide.getProject().toResource());
        return slideResource;
    }

    @Override
    public void create(SlideCreateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Slide slide = new Slide();
        BeanUtils.copyProperties(info, slide);
        repository.save(slide);
    }

    @Override
    public void update(SlideUpdateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Slide slide = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, slide);
        repository.save(slide);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Slide slide = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(slide.getProjectId());
        repository.deleteById(id);
    }
}
