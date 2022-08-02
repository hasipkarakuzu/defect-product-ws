package com.mhk.repairproductws.configuration;

import com.mhk.repairproductws.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  public SecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.httpBasic().and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/defect/list/**").hasRole("owner")
        .antMatchers("/defect/**").hasRole("admin")
        .antMatchers("/user/**").permitAll()
        .and()
        .csrf().disable().formLogin().disable();
  }


}
