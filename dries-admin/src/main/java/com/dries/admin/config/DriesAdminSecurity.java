package com.dries.admin.config;

import com.dries.admin.entity.UmsResourceEntity;
import com.dries.admin.service.UmsAdminService;
import com.dries.admin.service.UmsResourceService;
import com.dries.security.component.DynamicSecurityService;
import com.dries.security.config.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResourceEntity> resources = resourceService.list();
                for (UmsResourceEntity resource : resources) {
                    map.put(resource.getUrl(),new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }

                return map;
            }
        };
    }


}
