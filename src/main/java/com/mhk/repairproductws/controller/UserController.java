package com.mhk.repairproductws.controller;

import com.mhk.repairproductws.entity.User;
import com.mhk.repairproductws.service.UserService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;


  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("register")
  ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.registerUser(user));
  }


}
