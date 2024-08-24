package com.rainsen.display.service.imp;

import com.google.zxing.WriterException;
import com.rainsen.display.common.Constant;
import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.filter.UserFilter;
import com.rainsen.display.model.entity.Project;
import com.rainsen.display.model.entity.User;
import com.rainsen.display.model.repository.*;
import com.rainsen.display.model.request.ProjectCreateRequest;
import com.rainsen.display.model.request.ProjectUpdateRequest;
import com.rainsen.display.model.resource.ProjectResource;
import com.rainsen.display.service.ProjectService;
import com.rainsen.display.util.QRCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository repository;
    private final MemberRepository memberRepository;
    private final SlideRepository slideRepository;
    private final ImageRepository imageRepository;
    private final LinkRepository linkRepository;
    private final MaterialRepository materialRepository;
    private final CommentRepository commentRepository;
    private final PermitRepository permitRepository;

    @Autowired
    public ProjectServiceImp(
            ProjectRepository repository,
            MemberRepository memberRepository,
            SlideRepository slideRepository,
            ImageRepository imageRepository,
            LinkRepository linkRepository,
            MaterialRepository materialRepository,
            CommentRepository commentRepository,
            PermitRepository permitRepository
    ) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.slideRepository = slideRepository;
        this.imageRepository = imageRepository;
        this.linkRepository = linkRepository;
        this.materialRepository = materialRepository;
        this.commentRepository = commentRepository;
        this.permitRepository = permitRepository;
    }

    @Override
    public Page<ProjectResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<Project> projects = repository.findAll(pageable);
        return projects.map(Project::toResource);
    }

    @Override
    public Page<ProjectResource> myIndex(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<Project> projects = repository.findAllByUser_Id(UserFilter.user().getId(), pageable);
        return projects.map(Project::toResource);
    }

    @Override
    @Cacheable(value = "projectDetailShowing")
    public ProjectResource show(Long id) {
        Project project = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        ProjectResource projectResource = project.toResource();
        projectResource.setUser(project.getUser().toResource());
        return projectResource;
    }

    private void checkBeforeCreate(ProjectCreateRequest info) {
        if (repository.existsByTitle(info.getTitle())) {
            throw new DisException(DisExceptionEnum.PROJECT_NAME_EXISTS);
        }
    }

    @Override
    public void create(ProjectCreateRequest info) {
        checkBeforeCreate(info);
        Project project = new Project();
        BeanUtils.copyProperties(info, project);
        project.setUserId(UserFilter.user().getId());
        repository.save(project);
    }

    @Override
    public void update(ProjectUpdateRequest info) {
        Project project = checkPermission(info.getId());
        BeanUtils.copyProperties(info, project);
        repository.save(project);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        checkPermission(id);

        memberRepository.deleteAllByProject_Id(id);
        slideRepository.deleteAllByProject_Id(id);
        imageRepository.deleteAllByProject_Id(id);
        linkRepository.deleteAllByProject_Id(id);
        materialRepository.deleteAllByProject_Id(id);
        commentRepository.deleteAllByProject_Id(id);
        permitRepository.deleteAllByProject_Id(id);
        repository.deleteById(id);
    }

    @Override
    public String qrcode(Long id) {

        String projectUrl = Constant.APP_URL + "/project/" + id;
        String filename = "project" + id + ".png";
        String savePath = Constant.FILE_UPLOAD_DIR + filename;
        String accessPath = Constant.FILE_ACCESS_PREFIX + filename;

        File file = new File(savePath);
        if (file.exists()) return accessPath;

        try {
            QRCodeUtil.generate(projectUrl, 350, 350, savePath);
        } catch (WriterException | IOException e) {
            throw new DisException(DisExceptionEnum.FILE_WRITTEN_FAILED);
        }

        return accessPath;
    }

    @Override
    public Project checkPermission(Long projectId) {
        Project project = repository.findById(projectId).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        User user = UserFilter.user();
        if (!project.getUser().getId().equals(user.getId()) && !user.isAdmin()) {
            throw new DisException(DisExceptionEnum.INVALID_OPERATION);
        }
        return project;
    }
}
