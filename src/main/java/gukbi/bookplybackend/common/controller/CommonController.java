package gukbi.bookplybackend.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.common.service.CommonService;

@RestController
public class CommonController { // 공통으로 처리가 필요한 기능들 추가

  @Autowired
  CommonService commonService;

  // 대메뉴 가져오기
  @GetMapping("/sidebar/largeMenu/{level}")
  public ResponseDTO getLargeMenu(@PathVariable(value = "level") int level) {
    ResponseDTO res = commonService.getLargeMenu(level);
    return res;
  }

  // 소메뉴 가져오기
  @GetMapping("/sidebar/smallMenu")
  public ResponseDTO getSmallMenu() {
    ResponseDTO res = commonService.getSmallMenu();
    return res;
  }

  // 추천도서 등록정보 가져오기
  @GetMapping(value = "/main/sugBookInfo")
  public ResponseDTO sugBookInfo() {
    ResponseDTO res = commonService.sugBookInfo();
    return res;
  }
}
