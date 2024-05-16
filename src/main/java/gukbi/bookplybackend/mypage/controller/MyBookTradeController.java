package gukbi.bookplybackend.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.MyBookTradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/trade")
public class MyBookTradeController {

  private static final int showCnt = 10;

  @Autowired
  private final MyBookTradeService bookTradeService;

  // 거래내역 총 게시글 개수 조회
  @GetMapping(value = "/bookTradeCnt/{mem_no}")
  public ResponseDTO getTradeCnt(@PathVariable("mem_no") String mem_no) {
    ResponseDTO res = bookTradeService.getTradeCnt(mem_no);
    return res;
  }

  // 거래내역 조회
  @GetMapping(value = "/getTradeList/{nowPage}")
  public ResponseDTO getTradeList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String, String> reqBody) {
    Map<String, Object> pageMap = new HashMap<>();
    pageMap.put("showCnt", showCnt);
    pageMap.put("nowPage", (nowPage - 1) * 10);
    pageMap.put("mem_no", reqBody.get("mem_no"));

    ResponseDTO res = bookTradeService.getTradeList(pageMap);
    return res;
  }

  // 거래내역 삭제
  @PostMapping("/deleteTrade")
  public ResponseDTO deleteTrade(@RequestBody Map<String, Object> reqBody) {
    ResponseDTO res = bookTradeService.deleteTrade(reqBody);
    return res;
  }
}
