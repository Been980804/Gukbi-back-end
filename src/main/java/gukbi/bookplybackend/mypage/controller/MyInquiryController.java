package gukbi.bookplybackend.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.MyInquiryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/inquiry")
public class MyInquiryController {
    
  private static final int showCnt = 10;

  @Autowired
  private final MyInquiryService inquiryService;

  // 개별문의 총 게시글 개수 조회
  @GetMapping(value="/getMyInquiryCnt/{mem_no}")
  public ResponseDTO getMyInquiryCnt(@PathVariable("mem_no") String mem_no) {
    ResponseDTO res = inquiryService.getMyInquiryCnt(mem_no);
    return res;
  }

  // 개별문의내역 리스트 조회
  @GetMapping(value="/getMyInquiry/{nowPage}")
  public ResponseDTO getMyInquiry(@PathVariable("nowPage") int nowPage, @RequestParam Map<String, String> reqBody){
    Map<String, Object> pageMap = new HashMap<>();
    pageMap.put("showCnt", showCnt);
    pageMap.put("nowPage", (nowPage - 1) * 10);
    pageMap.put("search", reqBody.get("search"));
    pageMap.put("mem_no", reqBody.get("mem_no"));

    ResponseDTO res = inquiryService.getMyInquiry(pageMap);
    return res;
  }

  // 개별문의내역 삭제
  @PostMapping(value="/deleteInquiry")
  public ResponseDTO deleteInquiry(@RequestBody Map<String, String> reqBody) {
    ResponseDTO res = inquiryService.deleteInquiry(reqBody);
    return res;
  }
  
  // 공개여부 설정
  @PutMapping(value="/updateVisibility")
  public ResponseDTO updateVisibility(@RequestBody Map<String, Integer> reqBody) {
    ResponseDTO res = inquiryService.updateVisibility(reqBody);
    System.out.println(reqBody.get("qna_no"));

    return res;
  }
}
