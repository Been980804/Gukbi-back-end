package gukbi.bookplybackend.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;
import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.service.MgrBookService;

@RestController
@RequestMapping("/manage/book") // 메뉴 depth에 따라서 설정
public class MgrBookController { // 도서목록 관련 기능

  private static final int recordPage = 10;
  private static final int sugRecordPage = 5;

  @Autowired
  MgrBookService bookService;

  // 도서 테이블 총 개수
  @GetMapping(value = "/bookCount")
  public ResponseDTO getBookCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = bookService.getBookCount(sqlData);
    return res;
  }

  // 도서전체목록 데이터 받기
  @GetMapping(value = "/bookList/{currentPage}")
  public ResponseDTO getBookList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", recordPage);
    pageData.put("currentPage", (currentPage - 1) * 10);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = bookService.getBookList(pageData);
    return res;
  }

  // 도서 상세정보 가져오기
  @GetMapping(value = "/bookInfo/{isbn}")
  public ResponseDTO getBookInfo(@PathVariable(value = "isbn") String isbn) {
    ResponseDTO res = bookService.getBookInfo(isbn);
    return res;
  }

  // 도서 책소개 정보 가져오기
  @GetMapping(value = "/descript/{isbn}")
  public ResponseDTO getDescript(@PathVariable(value = "isbn") String isbn) {
    ResponseDTO res = bookService.getDescript(isbn);
    return res;
  }

  // 도서 정보 등록
  @PostMapping(value = "/books")
  public ResponseDTO createBook(@RequestParam Map<String, Object> book) {
    ResponseDTO res = new ResponseDTO();
    return res;
  }

  // 도서 정보 수정
  @PutMapping(value = "/bookModify")
  public ResponseDTO bookModify(@RequestParam Map<String, Object> book) {
    ResponseDTO res = bookService.bookModify(book);
    return res;
  }

  // 도서 정보 삭제
  @PutMapping(value = "/bookDelete/{isbn}")
  public ResponseDTO bookDelete(@PathVariable(value = "isbn") String isbn) {
    ResponseDTO res = bookService.bookDelete(isbn);
    return res;
  }

  // 도서 테이블 총 개수
  @GetMapping(value = "/sugCount")
  public ResponseDTO getSugCount(@RequestParam Map<String, String> sqlData) {
    ResponseDTO res = bookService.getSugCount(sqlData);
    return res;
  }

  // 도서전체목록 데이터 받기
  @GetMapping(value = "/sugList/{currentPage}")
  public ResponseDTO getSugList(@PathVariable(value = "currentPage") int currentPage, @RequestParam Map<String, String> sqlData) {
    Map<String, Object> pageData = new HashMap<String, Object>();
    pageData.put("recordPage", sugRecordPage);
    pageData.put("currentPage", (currentPage - 1) * sugRecordPage);
    pageData.put("column", sqlData.get("column"));
    pageData.put("search", sqlData.get("search"));
    
    ResponseDTO res = bookService.getSugList(pageData);
    return res;
  }

  // 추천도서 정보 등록
  @PostMapping(value = "/sugRegist/{isbn}")
  public ResponseDTO sugRegist(@PathVariable(value = "isbn") String isbn) {
    ResponseDTO res = bookService.sugRegist(isbn);
    return res;
  }
}