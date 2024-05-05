package gukbi.bookplybackend.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.user.service.UserService;

@RestController
@RequestMapping("/user")
public class userController {
  
  @Autowired
  UserService userService;

  // 로그인
  @PostMapping(value="/login")
  public ResponseDTO login(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = userService.login(reqBody);
    return res;
  }

  // 아이디 중복 체크
  @GetMapping(value = "/duplicate/{memId}")
  public ResponseDTO duplicate(@PathVariable(value = "memId") String memId) {
    ResponseDTO res = userService.duplicate(memId);
    return res;
  }

  // 회원가입
  @PostMapping(value = "/join")
  public ResponseDTO join(@RequestBody Map<String, Object> sqlData) {
    ResponseDTO res = userService.join(sqlData);
    return res;
  }
}
