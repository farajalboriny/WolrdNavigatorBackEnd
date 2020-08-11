package com.auth.web;

import com.auth.model.User;
import com.auth.service.FetchGameStatusService;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
  @Autowired private UserService userService;
  @Autowired private FetchGameStatusService fetchGameStatusService;

  @PostMapping(
      value = "/register",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<User> registration(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()) != null) {
      return ResponseEntity.badRequest().build();
    }

    userService.save(user);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping(
      value = "/login",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<User> login(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()) != null) {
      fetchGameStatusService.playerLoggedIn(user.getUsername());
    }
    return Optional.ofNullable(userService.findByUsername(user.getUsername()))
        .map(u -> ResponseEntity.ok().body(user)) // 200 OK
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
