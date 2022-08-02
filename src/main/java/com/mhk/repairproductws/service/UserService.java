package com.mhk.repairproductws.service;

import com.mhk.repairproductws.entity.Role;
import com.mhk.repairproductws.entity.User;
import com.mhk.repairproductws.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  final PasswordEncoder passwordEncoder;


  public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails userDetails = null;
    Optional<User> optionalUser = userRepository.findByEmailEqualsIgnoreCase(username);
    if (optionalUser.isPresent()) {
      User userInDb = optionalUser.get();
      userDetails = new org.springframework.security.core.userdetails.User(

          //userInDb.getEmail(),
          username,
          userInDb.getPassword(),
          userInDb.isEnabled(),
          userInDb.isTokenExpired(),
          true,
          true,
          getAuthorities(userInDb.getRoles())
      );
    } else {
      throw new UsernameNotFoundException("username not found");
    }

    return userDetails;
  }

  public User registerUser(User user) {
    Optional<User> optionalUser = userRepository.findByEmailEqualsIgnoreCase(user.getEmail());
    if (optionalUser.isPresent()) {
      throw new UsernameNotFoundException("this email already in use, please use another email");
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    }
  }


  private List<GrantedAuthority> getAuthorities(List<Role> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
