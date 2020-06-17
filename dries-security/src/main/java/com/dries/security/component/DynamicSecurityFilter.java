package com.dries.security.component;

import com.dries.security.config.IgnoreUrlsConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * @Description: 动态权限过滤器，用于实现基于路径的动态权限过滤
 * @Author ZSY
 * @createTime 2020/6/17 16:40
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
