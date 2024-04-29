package gukbi.bookplybackend.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class userController {
  
  @Autowired
  private final UserService userService;

  @PostMapping(value="/login")
  public ResponseDTO login(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = userService.login(reqBody);
    
    return res;
  }
}
