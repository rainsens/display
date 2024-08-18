package com.rainsen.display.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.common.Constant;
import com.rainsen.display.model.entity.User;
import com.rainsen.display.model.request.UserLoginRequest;
import com.rainsen.display.model.request.UserRegisterRequest;
import com.rainsen.display.model.request.UserUpdateRequest;
import com.rainsen.display.model.resource.UserResource;
import com.rainsen.display.service.UserService;
import com.rainsen.display.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Register a new user account.")
    @PostMapping("/register")
    public ApiResponse<Void> create(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request);
        return ApiResponse.success();
    }

    @Operation(description = "Sign in a specific user account.")
    @GetMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody UserLoginRequest request) {
        User user = userService.login(request);
        String token = JWTUtil.setToken(user);
        return ApiResponse.success(token);
    }

    @Operation(description = "Sign out.")
    @GetMapping("/logout")
    public ApiResponse<String> logout() {
        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY_NAME);
        String token = JWT.create()
                .withClaim(Constant.JWT_LOAD_USER_ID, "")
                .withClaim(Constant.JWT_LOAD_USER_NAME, "")
                .withClaim(Constant.JWT_LOAD_USER_ROLE, "")
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.JWT_EXPIRE_TIME))
                .sign(algorithm);
        return ApiResponse.success(token);
    }

    @Operation(description = "Show related information about a user.")
    @GetMapping("/show/{id}")
    public ApiResponse<UserResource> show(@PathVariable Long id) {
        return ApiResponse.success(userService.show(id));
    }

    @Operation(description = "Update some account information.")
    @PutMapping("/update")
    public ApiResponse<Void> update(@Valid @RequestBody UserUpdateRequest request) {
        userService.update(request);
        return ApiResponse.success();
    }
}
