package gukbi.bookplybackend.manage.service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.dao.MgrMapper;

@Service
public class MgrBookServiceImpl implements MgrBookService {

  @Autowired
  MgrMapper manageMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Value("${app.data4library.url}")
  private String baseUrl;

  @Value("${app.data4library.api-key}")
  private String authKey;

  @Override
  @Transactional // 총 도서 개수 가져오기 
  public ResponseDTO getBookCount(@RequestParam Map<String, String> sqlData) { 
    ResponseDTO res = new ResponseDTO();
    int bookCount = manageMapper.getBookCount(sqlData);

    if(bookCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 도서 개수 조회");
      res.setData("bookCount", bookCount);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 도서목록 가져오기
  public ResponseDTO getBookList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> bookList = manageMapper.getBookList(pageData);

    if(!bookList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("도서 리스트 조회");
      res.setData("bookList", bookList);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 도서 상세정보 가져오기
  public ResponseDTO getBookInfo(String isbn) {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> bookInfo = manageMapper.getBookInfo(isbn);

    if(bookInfo != null) {
      res.setResCode(200);
      res.setResMsg("도서 상세정보 조회");
      res.setData("bookInfo", bookInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 상세정보 조회에 실패했습니다.");
    }
    
    return res;
  }

  @Override
  @Transactional // 도서 책소개 정보 가져오기
  public ResponseDTO getDescript(String isbn) {
    ResponseDTO res = new ResponseDTO();
    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
              .queryParam("authKey", authKey)
              .queryParam("isbn13", isbn)
              .queryParam("format", "json")
              .build(true)
              .toUri();

    String jsonString = WebClient.builder().baseUrl(baseUrl)
              .build()
              .get()
              .uri(uri)
              .retrieve()
              .bodyToMono(String.class)
              .block();

    try {
      JsonNode jsonNode = objectMapper.readTree(jsonString).get("response")
                    .get("detail").get(0).get("book").get("description");
      String result = objectMapper.readValue(jsonNode.toString(), String.class);
      res.setResCode(200);
      res.setResMsg("도서 책소개 정보 조회");
      res.setData("descript", result);
    } catch (Exception e) {
      System.out.println("getDescript error: " + e.getMessage());
      res.setResCode(400);
      res.setResMsg("도서 상세정보 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 도서 정보 등록
  public ResponseDTO createBook(Map<String, Object> book) {
    try {
      ResponseDTO res = new ResponseDTO();
      int result = manageMapper.createBook(book);

      if(result == 1) {
        res.setResCode(200);
        res.setResMsg("도서 정보 등록");
        res.setData("book", result);
      } else {
        res.setResCode(300);
        res.setResMsg("도서 정보 등록에 실패했습니다.");
      }

      return res;
    } catch (DataIntegrityViolationException e) {
      ResponseDTO res = new ResponseDTO();
      res.setResCode(500);
      res.setResMsg("이미 등록되어 있는 도서 정보 입니다.");
      return res;
    }
  }

  @Override
  @Transactional // 도서 정보 수정
  public ResponseDTO bookModify(Map<String, Object> book) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.bookModify(book);

    if(result == 1) {
      res.setResCode(200);
      res.setResMsg("도서 정보 수정");
      res.setData("book", result);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 정보 수정에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 도서 정보 삭제
  public ResponseDTO bookDelete(String isbn) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.bookDelete(isbn);

    if(result == 1) {
      res.setResCode(200);
      res.setResMsg("도서 정보 삭제");
      res.setData("book", result);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 정보 삭제에 실패했습니다.");
    }

    return res;
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  @Transactional // 총 추천도서 개수 가져오기
  public ResponseDTO getSugCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int sugCount = manageMapper.getSugCount(sqlData);

    if(sugCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 도서 개수 조회");
      res.setData("sugCount", sugCount);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 추천도서목록 가져오기
  public ResponseDTO getSugList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> sugList = manageMapper.getSugList(pageData);

    if(!sugList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("도서 리스트 조회");
      res.setData("sugList", sugList);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 추천도서 설정
  public ResponseDTO sugBook(String isbn) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.sugBook(isbn);

    if(result == 1) {
      res.setResCode(200);
      res.setResMsg("도서 정보 수정");
      res.setData("sugBook", result);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 정보 수정에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 추천도서 정보 등록
  public ResponseDTO sugRegist(String isbn) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.sugRegist(isbn);

    if(result == 1) {
      res.setResCode(200);
      res.setResMsg("도서 정보 수정");
      res.setData("sugRegist", result);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 정보 수정에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 추천도서 정보 가져오기
  public ResponseDTO sugBookInfo() {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> bookInfo = manageMapper.sugBookInfo();

    if(bookInfo != null) {
      res.setResCode(200);
      res.setResMsg("추천도서 정보 조회");
      res.setData("sugBookInfo", bookInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("추천도서 정보 조회에 실패했습니다.");
    }

    return res;
  }
}
