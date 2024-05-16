package gukbi.bookplybackend.madang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.service.BookPlyListService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/madang/bookPlyList")
public class BookPlyListController {

  public static final int showCnt = 10;

  @Autowired
  BookPlyListService bookPlyService;

  // 총 북플리 개수 조회
  @GetMapping(value = "/bookPlyListCnt")
  public ResponseDTO getBookPlyListCnt(@RequestParam Map<String, String> reqBody) {
    ResponseDTO res = bookPlyService.getBookPlyListCnt(reqBody);
    return res;
  }

  @GetMapping(value = "/bookPlyList/{nowPage}")
  public ResponseDTO getBookPlyList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String, String> reqBody) {
    Map<String, Object> pageMap = new HashMap<>();
    pageMap.put("showCnt", showCnt);
    pageMap.put("nowPage", (nowPage - 1) * 10);
    pageMap.put("column", reqBody.get("column"));
    pageMap.put("search", reqBody.get("search"));

    ResponseDTO res = bookPlyService.getBookPlyList(pageMap);
    return res;
  }
}
