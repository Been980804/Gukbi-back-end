package gukbi.bookplybackend.manage.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrBookService {

  // 총 도서 개수 가져오기
  ResponseDTO getBookCount(Map<String, String> sqlData);

  // 현재 페이지 도서목록 가져오기
  ResponseDTO getBookList(Map<String, Object> pageData);

  // 도서 상세정보 가져오기
  ResponseDTO getBookInfo(String isbn);

  // 도서 책소개 정보 가져오기
  ResponseDTO getDescript(String isbn);

  // 도서 정보 등록
  ResponseDTO createBook(Map<String, Object> book);

  // 도서 정보 수정
  ResponseDTO bookModify(Map<String, Object> book);

  // 도서 정보 삭제
  ResponseDTO bookDelete(String isbn);

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // 총 추천도서 개수 가져오기
  ResponseDTO getSugCount(Map<String, String> sqlData);

  // 현재 페이지 추천도서목록 가져오기
  ResponseDTO getSugList(Map<String, Object> pageData);

  // 추천도서 설정
  ResponseDTO sugBook(String isbn);

  // 추천도서 정보 등록
  ResponseDTO sugRegist(String isbn);
}
