package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.filter.UserFilter;
import com.rainsen.display.model.entity.Permit;
import com.rainsen.display.model.repository.PermitRepository;
import com.rainsen.display.model.request.PermitCreateRequest;
import com.rainsen.display.model.request.PermitUpdateRequest;
import com.rainsen.display.model.resource.PermitResource;
import com.rainsen.display.service.PermitService;
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
public class PermitServiceImp implements PermitService {

    private final PermitRepository repository;
    private final ProjectService projectService;

    @Autowired
    public PermitServiceImp(
            PermitRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<PermitResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Permit> permits = repository.findAll(pageable);
        return permits.map(Permit::toResource);
    }

    @Override
    public PermitResource show(Long id) {
        Permit permit = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        PermitResource permitResource = permit.toResource();
        permitResource.setUser(permit.getUser().toResource());
        permitResource.setProject(permit.getProject().toResource());
        return permitResource;
    }

    @Override
    public void create(PermitCreateRequest info) {
        projectService.checkPermission(info.getProjectId());
        checkRepetition(info.getProjectId(), info.getUserId());
        Permit permit = new Permit();
        BeanUtils.copyProperties(info, permit);
        repository.save(permit);
    }

    @Override
    public void update(PermitUpdateRequest info) {
        projectService.checkPermission(info.getProjectId());
        checkRepetition(info.getProjectId(), info.getUserId());
        Permit permit = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, permit);
        repository.save(permit);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Permit permit = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(permit.getProjectId());
        repository.deleteById(id);
    }

    private void checkRepetition(Long projectId, Long userId) {
        if (repository.existsByProject_IdAndUser_Id(projectId, userId)) {
            throw new DisException(DisExceptionEnum.REPETITIVE_OPERATION);
        }
    }
}
