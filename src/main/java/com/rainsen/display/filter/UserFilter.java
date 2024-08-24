package com.rainsen.display.filter;

import com.rainsen.display.model.entity.User;
import com.rainsen.display.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class UserFilter implements Filter {

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            try {
                User authUser = JWTUtil.auth(servletRequest, servletResponse);
                userThreadLocal.set(authUser);
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {
                userThreadLocal.remove();
            }
        }
    }

    public static User user() {
        return userThreadLocal.get();
    }

    @Override
    public void destroy() {}
}
