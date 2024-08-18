package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.Member;
import com.rainsen.display.model.repository.MemberRepository;
import com.rainsen.display.model.request.MemberCreateRequest;
import com.rainsen.display.model.request.MemberUpdateRequest;
import com.rainsen.display.model.resource.MemberResource;
import com.rainsen.display.service.MemberService;
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
public class MemberServiceImp implements MemberService {

    private final MemberRepository repository;
    private final ProjectService projectService;

    @Autowired
    public MemberServiceImp(
            MemberRepository repository,
            ProjectService projectService
    ) {
        this.repository = repository;
        this.projectService = projectService;
    }

    @Override
    public Page<MemberResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Member> members = repository.findAll(pageable);
        return members.map(member -> {
            MemberResource memberResource = member.toResource();
            memberResource.setUser(member.getUser().toResource());
            return memberResource;
        });
    }

    @Override
    public MemberResource show(Long id) {
        Member member = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        MemberResource memberResource = member.toResource();
        memberResource.setUser(member.getUser().toResource());
        return memberResource;
    }

    @Override
    public void create(MemberCreateRequest info) {
        checkRepetition(info.getProjectId(), info.getUserId());
        projectService.checkPermission(info.getProjectId());
        Member member = new Member();
        BeanUtils.copyProperties(info, member);
        repository.save(member);
    }

    @Override
    public void update(MemberUpdateRequest info) {
        checkRepetition(info.getProjectId(), info.getUserId());
        projectService.checkPermission(info.getProjectId());
        Member member = repository.findById(info.getId()).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, member);
        repository.save(member);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member member = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        projectService.checkPermission(member.getProjectId());
        repository.deleteById(id);
    }

    private void checkRepetition(Long projectId, Long userId) {
        if (repository.existsByProject_IdAndUser_Id(projectId, userId)) {
            throw new DisException(DisExceptionEnum.REPETITIVE_OPERATION);
        }
    }
}
