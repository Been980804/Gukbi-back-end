package gukbi.bookplybackend.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.service.MgrUserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/manage/user") // 메뉴 depth에 따라서 설정
public class MgrUserController {

  private static final int recordPage = 10;

  @Autowired
  MgrUserService userService;

  // 회원 테이블 총 개수
  @GetMapping(value = "/userCount")
  public ResponseDTO getUserCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = userService.getUserCount(sqlData);
    return res;
  }

  // 회원전체목록 데이터 받기
  @GetMapping(value = "/userList/{currentPage}")
  public ResponseDTO getUserList(@PathVariable(value = "currentPage") int currentPage,
      @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));

    ResponseDTO res = userService.getUserList(pageData);
    return res;
  }

  // 회원 대여/연체정보 테이블 총 개수
  @GetMapping(value = "/userInfo/rentCount")
  public ResponseDTO getRentCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = userService.getRentCount(sqlData);
    return res;
  }

  // 회원 대여/연체정보 가져오기
  @GetMapping(value = "/userInfo/rent/{currentPage}")
  public ResponseDTO getUserInfo(@PathVariable(value = "currentPage") int currentPage,
      @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    pageData.put("memNo", sqlData.get("memNo"));

    ResponseDTO res = userService.getUserInfo(pageData);
    return res;
  }

  // 회원 연체정보 총 개수
  @GetMapping(value = "/userInfo/overdueCount/{memNo}")
  public ResponseDTO getOverdueCount(@PathVariable(value = "memNo") String memNo) {
    ResponseDTO res = userService.getOverdueCount(memNo);
    return res;
  }

  // 회원 대여가능여부 변경
  @PutMapping(value = "/userInfo/rent/{memNo}")
  public ResponseDTO setRent(@PathVariable(value = "memNo") String memNo, @RequestBody Boolean rentYn) {
    Map<String, String> sqlData = new HashMap<String, String>();
    sqlData.put("memNo", memNo);
    if (rentYn == true) {
      sqlData.put("rentYn", "Y");
    } else {
      sqlData.put("rentYn", "N");
    }

    ResponseDTO res = userService.setRent(sqlData);
    return res;
  }
}
