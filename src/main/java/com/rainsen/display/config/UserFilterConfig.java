package com.rainsen.display.config;

import com.rainsen.display.filter.UserFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFilterConfig {

    @Bean
    public UserFilter userFilter() {
        return new UserFilter();
    }

    @Bean(name = "userFilterConf")
    public FilterRegistrationBean<Filter> userFilterConfig() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(userFilter());
        filterRegistrationBean.setName("userFilterConfig");

        filterRegistrationBean.addUrlPatterns("/user/show/*");
        filterRegistrationBean.addUrlPatterns("/user/update/*");
        filterRegistrationBean.addUrlPatterns("/user/logout/*");

        filterRegistrationBean.addUrlPatterns("/project/my/index/*");
        filterRegistrationBean.addUrlPatterns("/project/create/*");
        filterRegistrationBean.addUrlPatterns("/project/update/*");
        filterRegistrationBean.addUrlPatterns("/project/delete/*");
        filterRegistrationBean.addUrlPatterns("/project/qrcode/*");

        filterRegistrationBean.addUrlPatterns("/member/create/*");
        filterRegistrationBean.addUrlPatterns("/member/update/*");
        filterRegistrationBean.addUrlPatterns("/member/delete/*");

        filterRegistrationBean.addUrlPatterns("/slide/create/*");
        filterRegistrationBean.addUrlPatterns("/slide/update/*");
        filterRegistrationBean.addUrlPatterns("/slide/delete/*");

        filterRegistrationBean.addUrlPatterns("/image/create/*");
        filterRegistrationBean.addUrlPatterns("/image/update/*");
        filterRegistrationBean.addUrlPatterns("/image/delete/*");

        filterRegistrationBean.addUrlPatterns("/link/create/*");
        filterRegistrationBean.addUrlPatterns("/link/update/*");
        filterRegistrationBean.addUrlPatterns("/link/delete/*");

        filterRegistrationBean.addUrlPatterns("/material/create/*");
        filterRegistrationBean.addUrlPatterns("/material/update/*");
        filterRegistrationBean.addUrlPatterns("/material/delete/*");

        filterRegistrationBean.addUrlPatterns("/comment/create/*");
        filterRegistrationBean.addUrlPatterns("/comment/update/*");
        filterRegistrationBean.addUrlPatterns("/comment/delete/*");

        filterRegistrationBean.addUrlPatterns("/permit/create/*");
        filterRegistrationBean.addUrlPatterns("/permit/update/*");
        filterRegistrationBean.addUrlPatterns("/permit/delete/*");

        filterRegistrationBean.addUrlPatterns("/survey/create/*");
        filterRegistrationBean.addUrlPatterns("/survey/update/*");
        filterRegistrationBean.addUrlPatterns("/survey/delete/*");

        filterRegistrationBean.addUrlPatterns("/question/create/*");
        filterRegistrationBean.addUrlPatterns("/question/update/*");
        filterRegistrationBean.addUrlPatterns("/question/delete/*");

        filterRegistrationBean.addUrlPatterns("/answer/create/*");
        filterRegistrationBean.addUrlPatterns("/answer/update/*");
        filterRegistrationBean.addUrlPatterns("/answer/delete/*");

        filterRegistrationBean.addUrlPatterns("/upload/*");

        return filterRegistrationBean;
    }
}
