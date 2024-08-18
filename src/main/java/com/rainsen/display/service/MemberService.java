package com.rainsen.display.service;

import com.rainsen.display.model.request.MemberCreateRequest;
import com.rainsen.display.model.request.MemberUpdateRequest;
import com.rainsen.display.model.resource.MemberResource;
import org.springframework.data.domain.Page;

public interface MemberService {

    Page<MemberResource> index(Integer page, Integer size);

    MemberResource show(Long id);

    void create(MemberCreateRequest info);

    void update(MemberUpdateRequest info);

    void delete(Long id);
}
