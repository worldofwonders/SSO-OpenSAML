package org.sms.project.security;

import java.util.HashSet;
import java.util.Set;
import org.sms.organization.user.entity.User;
import org.sms.organization.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author zhenxing.Liu
 */
public class SysUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService sysUserService;

  @Override
  public UserDetails loadUserByUsername(String login_id) throws UsernameNotFoundException {
    User user = sysUserService.findUserByLogin_Id(login_id);
    this.initAuths(user);
    return user;
  }

  private void initAuths(User user) {
    Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
    auths.add(new SimpleGrantedAuthority("ADMIN"));
    user.setAuthorities(auths);
  }
}