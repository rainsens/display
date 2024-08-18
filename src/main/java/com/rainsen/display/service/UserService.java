package com.rainsen.display.service;

import com.rainsen.display.model.entity.User;
import com.rainsen.display.model.request.UserLoginRequest;
import com.rainsen.display.model.request.UserRegisterRequest;
import com.rainsen.display.model.request.UserUpdateRequest;
import com.rainsen.display.model.resource.UserResource;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResource> index(Integer page, Integer size);

    UserResource show(Long id);

    void register(UserRegisterRequest info);

    User login(UserLoginRequest info);

    void update(UserUpdateRequest info);
}
