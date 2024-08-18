package com.rainsen.display.service.imp;

import com.rainsen.display.exception.DisException;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.User;
import com.rainsen.display.model.repository.UserRepository;
import com.rainsen.display.model.request.UserLoginRequest;
import com.rainsen.display.model.request.UserRegisterRequest;
import com.rainsen.display.model.request.UserUpdateRequest;
import com.rainsen.display.model.resource.UserResource;
import com.rainsen.display.service.UserService;
import com.rainsen.display.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserResource> index(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<User> users = repository.findAll(pageable);
        return users.map(User::toResource);
    }

    @Override
    public UserResource show(Long id) {
        User user = repository.findById(id).orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        return user.toResource();
    }

    private void checkBeforeRegister(String username, String email) {
        User user = repository.findByUsernameOrEmail(username, email);
        if (user != null && StringUtils.hasText(user.getUsername())) {
            throw new DisException(DisExceptionEnum.USERNAME_EXISTS);
        }
        if (user != null && StringUtils.hasText(user.getEmail())) {
            throw new DisException(DisExceptionEnum.EMAIL_EXISTS);
        }
    }

    @Override
    public void register(UserRegisterRequest info) {

        checkBeforeRegister(info.getUsername(), info.getEmail());

        User newUser = new User();
        newUser.setUsername(info.getUsername());
        newUser.setEmail(info.getEmail());

        try {
            newUser.setPassword(MD5Util.getMD5String(info.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        repository.save(newUser);
    }

    public User login(UserLoginRequest info) {
        String password;
        try {
            password = MD5Util.getMD5String(info.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        User user = repository.findByEmail(info.getEmail());

        if (user == null) {
            throw new DisException(DisExceptionEnum.USERNAME_NOT_EXISTS);
        }
        if (!password.equals(user.getPassword())) {
            throw new DisException(DisExceptionEnum.PASSWORD_WRONG);
        }

        return user;
    }

    @Override
    public void update(UserUpdateRequest info) {
        User user = repository.findById(info.getId())
                .orElseThrow(()->new DisException(DisExceptionEnum.RECORD_NOT_EXISTS));
        BeanUtils.copyProperties(info, user);
        repository.save(user);
    }
}
