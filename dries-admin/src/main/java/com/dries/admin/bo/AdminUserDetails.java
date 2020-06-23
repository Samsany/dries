package com.dries.admin.bo;

import com.dries.admin.entity.UmsAdminEntity;
import com.dries.admin.entity.UmsPermissionEntity;
import com.dries.admin.entity.UmsResourceEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: SpringSecurity需要的用户详情 @Author ZSY
 *
 * @createTime 2020/6/17 11:52
 */
public class AdminUserDetails implements UserDetails {

  private UmsAdminEntity umsAdmin;
  private List<UmsResourceEntity> resourceList;

  public AdminUserDetails(UmsAdminEntity umsAdmin, List<UmsResourceEntity> resourceList) {
    this.umsAdmin = umsAdmin;
    this.resourceList = resourceList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //                .filter(permission -> permission.getValue() !=null )
    //                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
    return resourceList.stream()
        .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return umsAdmin.getPassword();
  }

  @Override
  public String getUsername() {
    return umsAdmin.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return umsAdmin.getStatus().equals(1);
  }
}
