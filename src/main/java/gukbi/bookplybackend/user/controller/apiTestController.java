package gukbi.bookplybackend.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class apiTestController {

  @Autowired
  private final UserService userService;

  // 프론트 백 api test
  @PutMapping(value="/{number}")
  public String apiTest(@PathVariable String number, @RequestBody Map<String, String> reqBody) {

    reqBody.put("number", number);

    String res = reqBody.get("number") + ":::" + reqBody.get("msg");

    return res;
  }

  // db test
  @GetMapping(value="/user/{number}")
  public ResponseDTO getUserList(@PathVariable String number) {
    ResponseDTO res = userService.getUserList(number);
    
    return res;
  }

  
}
