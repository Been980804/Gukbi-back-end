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
import gukbi.bookplybackend.madang.service.BookApplListService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/madang/hopeBookList")
public class BookApplListController {

  public static final int showCnt = 10;

  @Autowired
  BookApplListService bookApplListService;

  // 도서 신청 게시글 수 조회
  @GetMapping(value = "/hopeBookListCnt")
  public ResponseDTO hopeBookListCnt(@RequestParam Map<String, Object> reqBody) {
    ResponseDTO res = bookApplListService.hopeBookListCnt(reqBody);
    return res;
  }

  // 도서 신청 게시글 조회
  @GetMapping(value = "/hopeBookList/{nowPage}")
  public ResponseDTO hopeBookList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String, Object> reqBody) {
    Map<String, Object> pageMap = new HashMap<>();
    pageMap.put("showCnt", showCnt);
    pageMap.put("nowPage", (nowPage - 1) * 10);
    pageMap.put("column", reqBody.get("column"));
    pageMap.put("search", reqBody.get("search"));

    ResponseDTO res = bookApplListService.hopeBookList(pageMap);
    return res;
  }
}
