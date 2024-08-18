package com.rainsen.display.filter;

import com.rainsen.display.common.ApiResponse;
import com.rainsen.display.exception.DisExceptionEnum;
import com.rainsen.display.model.entity.User;
import com.rainsen.display.util.JWTUtil;
import jakarta.servlet.*;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        try {
            User authUser = JWTUtil.auth(servletRequest, servletResponse);

            if (authUser == null) {return;}

            UserFilter.userThreadLocal.set(authUser);

            if (authUser.isAdmin()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ApiResponse.errorOut(servletResponse, DisExceptionEnum.UNAUTHORISED_ACCESS);
            }
        } finally {
            UserFilter.userThreadLocal.remove();
        }

    }

    @Override
    public void destroy() {}
}
