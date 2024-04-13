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
import gukbi.bookplybackend.manage.service.MgrReservService;

@RestController
@RequestMapping("/manage/reserv")
public class MgrReservController {
  
  private static final int recordPage = 10;

  @Autowired
  MgrReservService reservService;

  // 예약 테이블 총 개수
  @GetMapping(value = "/reservCount")
  public ResponseDTO getReservCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = reservService.getReservCount(sqlData);
    return res;
  }

  // 예약전체목록 데이터 받기
  @GetMapping(value = "/reservList/{currentPage}")
  public ResponseDTO getBookList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = reservService.getReservList(pageData);
    return res;
  }
}
