package com.rainsen.display.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.common.Constant;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class JWTUtil {

    public static String setToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY_NAME);
        return JWT.create()
                .withClaim(Constant.JWT_LOAD_USER_ID, user.getId())
                .withClaim(Constant.JWT_LOAD_USER_NAME, user.getUsername())
                .withClaim(Constant.JWT_LOAD_USER_ROLE, user.isAdmin())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constant.JWT_EXPIRE_TIME))
                .sign(algorithm);
    }

    private static String getToken(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getHeader(Constant.JWT_TOKEN_NAME);
    }

    private static User verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY_NAME);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        User user = new User();
        user.setId(jwt.getClaim(Constant.JWT_LOAD_USER_ID).asLong());
        user.setUsername(jwt.getClaim(Constant.JWT_LOAD_USER_NAME).asString());
        user.setAdmin(jwt.getClaim(Constant.JWT_LOAD_USER_ROLE).isNull());
        return user;
    }

    public static User auth(ServletRequest servletRequest, ServletResponse servletResponse) {

        String token = getToken(servletRequest);
        if (token == null) {
            ApiResponse.errorOut(servletResponse, DisExceptionEnum.UNAUTHORISED_ACCESS);
            return null;
        }

        User authUser = null;

        try {
            authUser = verifyToken(token);
        } catch (TokenExpiredException e) {
            ApiResponse.errorOut(servletResponse, DisExceptionEnum.TOKEN_EXPIRED);
        } catch (JWTDecodeException e) {
            ApiResponse.errorOut(servletResponse, DisExceptionEnum.TOKEN_WRONG);
        }

        return authUser;
    }
}
