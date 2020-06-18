package com.dries.admin.config;

import com.dries.admin.service.UmsAdminService;
import com.dries.admin.service.UmsResourceService;
import com.dries.security.config.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author ZSY
 * @createTime 2020/6/18 13:58
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DriesAdminSecurity extends SecurityConfig {

    @Resource
    private UmsAdminService adminService;

    @Resource
    private UmsResourceService resourceService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> adminService.loadUserByUsername(username);
    }

}
