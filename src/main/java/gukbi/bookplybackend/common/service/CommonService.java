package gukbi.bookplybackend.common.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface CommonService {
  
  // 대메뉴 가져오기
  ResponseDTO getLargeMenu(int level);

  // 소메뉴 가져오기
  ResponseDTO getSmallMenu();

  // 추천도서 정보 가져오기
  ResponseDTO sugBookInfo();

  // 총 도서 개수 가져오기
  ResponseDTO getBookCount(Map<String, String> sqlData);

  // 현재 페이지 도서목록 가져오기
  ResponseDTO getBookList(Map<String, Object> pageData);

  // 도서 상세정보 가져오기
  ResponseDTO getBookInfo(String isbn);

  // 도서 책소개 정보 가져오기
  ResponseDTO getDescript(String isbn);

  // 총 카테고리 도서 개수 가져오기
  ResponseDTO getCatCount(Map<String, String> sqlData);

  // 카테고리 현재 페이지 도서목록 가져오기
  ResponseDTO getCatList(Map<String, Object> pageData);

  // 최신 공지사항 목록 가져오기
  ResponseDTO getNotiList();

  // 북플리 추천 목록 가져오기
  ResponseDTO getBookPly();

  // 책바구니에 담기
  ResponseDTO basket(Map<String, Object> sqlData);

  // 책바구니에 빼기
  ResponseDTO basketDelete(Map<String, Object> sqlData);

  // 책바구니 목록 가져오기
  ResponseDTO basketList(String memNo);

  // 도서대여내역 조회
  ResponseDTO getBookStatus(String bookNo);
}
