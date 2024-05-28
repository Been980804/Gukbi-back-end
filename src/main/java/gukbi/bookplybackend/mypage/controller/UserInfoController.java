package gukbi.bookplybackend.mypage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/userInfo")
public class UserInfoController {

  @Autowired
  private final UserInfoService userInfoService;

  // 아이디 중복체크
  @GetMapping(value = "/idCheck/{mem_id}")
  public ResponseDTO idCheck(@PathVariable("mem_id") String mem_id) {
    ResponseDTO res = userInfoService.getIdDuplicate(mem_id);
    return res;
  }

  // 현재 로그인한 비밀번호 가져오기
  @GetMapping(value = "getCurrentPwd/{mem_id}")
  public ResponseDTO getCurrentPwd(@PathVariable("mem_id") String mem_no) {
    ResponseDTO res = userInfoService.getCurrentPwd(mem_no);
    return res;
  }

  // 비밀번호 수정
  @PostMapping(value = "/pwdUpdate")
  public ResponseDTO pwdUpdate(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = userInfoService.pwdUpdate(reqBody);
    return res;
  }

  // 회원탈퇴
  @PostMapping(value = "/accountDelete")
  public ResponseDTO accountDelete(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = userInfoService.accountDelete(reqBody);
    return res;
  }

  // 회원 정보 수정
  @PutMapping(value = "/updateUserInfo")
  public ResponseDTO updateUserInfo(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = userInfoService.updateUserInfo(reqBody);
    return res;
  }

  // google Login 실행
  @GetMapping("/googleLogin")
  public ResponseDTO googleLogin() {
    ResponseDTO res = userInfoService.googleLogin();
    return res;
  }
  
  @GetMapping("/googleLogin/callback")
  public ResponseDTO googleLoginToken(@RequestParam(name = "code") String code) {
    System.out.println("code:::::::" + code);
    ResponseDTO res = userInfoService.getGoogleToken(code);
    return null;
  }
  
  
}
