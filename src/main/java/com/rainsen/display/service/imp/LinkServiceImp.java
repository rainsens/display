package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Link;
import com.rainsen.display.model.repository.LinkRepository;
import com.rainsen.display.model.request.LinkCreateRequest;
import com.rainsen.display.model.request.LinkUpdateRequest;
import com.rainsen.display.model.resource.LinkResource;
import com.rainsen.display.service.LinkService;
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
public class LinkServiceImp implements LinkService {

    private final LinkRepository repository;
    private final ProjectService projectService;

    @Autowired
    public LinkServiceImp(
            LinkRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<LinkResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Link> links = repository.findAll(pageable);
        return links.map(Link::toResource);
    }

    @Override
    public LinkResource show(Long id) {
        Link link = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        LinkResource linkResource = link.toResource();
        linkResource.setProject(link.getProject().toResource());
        return linkResource;
    }

    @Override
    public void create(LinkCreateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Link link = new Link();
        BeanUtils.copyProperties(info, link);
        repository.save(link);
    }

    @Override
    public void update(LinkUpdateRequest info) {
        projectService.checkPermission(info.getProjectId());
        Link link = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, link);
        repository.save(link);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Link link = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(link.getProjectId());
        repository.deleteById(id);
    }
}
