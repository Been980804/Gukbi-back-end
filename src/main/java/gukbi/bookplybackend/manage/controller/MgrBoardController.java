package gukbi.bookplybackend.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.service.MgrBoardService;

@RestController
@RequestMapping("/manage/board")
public class MgrBoardController {
  
  private static final int recordPage = 10;

  @Autowired
  MgrBoardService boardService;

  // 공지사항 테이블 총 개수
  @GetMapping(value = "/notice/notiCount")
  public ResponseDTO getNotiCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = boardService.getNotiCount(sqlData);
    return res;
  }

  // 공지사항 전체목록 데이터 받기
  @GetMapping(value = "/notice/notiList/{currentPage}")
  public ResponseDTO getNotiList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = boardService.getNotiList(pageData);
    return res;
  }

  // 공지사항 상세정보 가져오기
  @GetMapping(value = "/notice/notiInfo/{notiNo}")
  public ResponseDTO getNotiInfo(@PathVariable(value = "notiNo") String notiNo) {
    ResponseDTO res = boardService.getNotiInfo(notiNo);
    return res;
  }

  // 공지사항 정보 삭제
  @PutMapping(value = "/notice/notiDelete/{notiNo}")
  public ResponseDTO notiDelete(@PathVariable(value = "notiNo") String notiNo) {
    ResponseDTO res = boardService.notiDelete(notiNo);
    return res;
  }

  // 문의사항 테이블 총 개수
  @GetMapping(value = "/inquiry/inquiryCount")
  public ResponseDTO getInquiryCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = boardService.getInquiryCount(sqlData);
    return res;
  }

  // 문의사항 전체목록 데이터 받기
  @GetMapping(value = "/inquiry/inquiryList/{currentPage}")
  public ResponseDTO getInquiryList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = boardService.getInquiryList(pageData);
    return res;
  }

  // 문의사항 상세정보 가져오기
  @GetMapping(value = "/inquiry/inquiryInfo/{inquiryNo}")
  public ResponseDTO getInquiryInfo(@PathVariable(value = "inquiryNo") String inquiryNo) {
    ResponseDTO res = boardService.getInquiryInfo(inquiryNo);
    return res;
  }

  // 문의사항 답장 등록
  @PostMapping(value = "/inquiry/answer")
  public ResponseDTO registAnswer(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = boardService.registAnswer(sqlData);
    return res;
  }

  // 도서거래 테이블 총 개수
  @GetMapping(value = "/trade/tradeCount")
  public ResponseDTO getTradeCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = boardService.getTradeCount(sqlData);
    return res;
  }

  // 도서거래 전체목록 데이터 받기
  @GetMapping(value = "/trade/tradeList/{currentPage}")
  public ResponseDTO getTradeList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = boardService.getTradeList(pageData);
    return res;
  }

  // 도서거래 상세정보 가져오기
  @GetMapping(value = "/trade/tradeInfo/{tradeNo}")
  public ResponseDTO getTradeInfo(@PathVariable(value = "tradeNo") String tradeNo) {
    ResponseDTO res = boardService.getTradeInfo(tradeNo);
    return res;
  }

  // 희망도서 테이블 총 개수
  @GetMapping(value = "/appl/applCount")
  public ResponseDTO getApplCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = boardService.getApplCount(sqlData);
    return res;
  }

  // 희망도서 전체목록 데이터 받기
  @GetMapping(value = "/appl/applList/{currentPage}")
  public ResponseDTO getApplList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = boardService.getApplList(pageData);
    return res;
  }
}
