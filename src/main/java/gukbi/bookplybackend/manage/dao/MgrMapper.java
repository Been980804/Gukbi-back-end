package gukbi.bookplybackend.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MgrMapper {

  // 총 도서 개수 가져오기
  Integer getBookCount(Map<String, String> sqlData);

  // 현재 페이지 도서목록 가져오기
  List<Map<String, Object>> getBookList(Map<String, Object> pageData);

  // 도서 상세정보 가져오기
  Map<String, Object> getBookInfo(String isbn);

  // 도서 정보 등록
  Integer createBook(Map<String, Object> book);

  // 도서 정보 수정
  Integer bookModify(Map<String, Object> book);

  // 도서 정보 삭제
  Integer bookDelete(String isbn);

  // 총 추천도서 개수 가져오기
  Integer getSugCount(Map<String, String> sqlData);

  // 현재 페이지 추천도서목록 가져오기
  List<Map<String, Object>> getSugList(Map<String, Object> pageData);

  // 추천도서 정보 등록
  Integer sugRegist(String isbn);


  // 대메뉴 가져오기
  List<Map<String, Object>> getLargeMenu(int level);

  // 소메뉴 가져오기
  List<Map<String, Object>> getSmallMenu(String menuNo);


  // 총 회원 개수 가져오기
  Integer getUserCount(Map<String, String> sqlData);

  // 현재 페이지 회원목록 가져오기
  List<Map<String, Object>> getUserList(Map<String, Object> pageData);


  // 총 회원 대여/연체정보 개수 가져오기
  Integer getUserRentCount(Map<String, String> sqlData);

  // 회원 대여/연체정보 가져오기
  List<Map<String, Object>> getUserInfo(Map<String, Object> pageData);

  // 회원 연체내역 총 개수
  Integer getOverdueCount(String memNo);


  // 총 회원 대여/연체정보 개수 가져오기
  Integer getRentCount(Map<String, String> sqlData);

  // 회원 대여/연체정보 데이터 받기
  List<Map<String, Object>> getRentList(Map<String, Object> pageData);


  // 총 예약 개수 가져오기
  Integer getReservCount(Map<String, String> sqlData);

  // 현재 페이지 예약목록 가져오기
  List<Map<String, Object>> getReservList(Map<String, Object> pageData);


  // 총 공지사항 개수 가져오기
  Integer getNotiCount(Map<String, String> sqlData);

  // 현재 페이지 공지사항목록 가져오기
  List<Map<String, Object>> getNotiList(Map<String, Object> pageData);

  // 공지사항 상세정보 가져오기
  Map<String, Object> getNotiInfo(String notiNo);

  // 공지사항 정보 삭제
  Integer notiDelete(String notiNo);

  // 총 문의사항 개수 가져오기
  Integer getInquiryCount(Map<String, String> sqlData);

  // 현재 페이지 문의사항목록 가져오기
  List<Map<String, Object>> getInquiryList(Map<String, Object> pageData);

  // 문의사항 상세정보 가져오기
  Map<String, Object> getInquiryInfo(String inquiryNo);

  // 문의사항 답장 등록
  Integer registAnswer(Map<String, String> sqlData);

  // 총 도서거래 개수 가져오기
  Integer getTradeCount(Map<String, String> sqlData);

  // 현재 페이지 도서거래목록 가져오기
  List<Map<String, Object>> getTradeList(Map<String, Object> pageData);

  // 도서거래 상세정보 가져오기
  Map<String, Object> getTradeInfo(String tradeNo);

  // 총 희망도서 개수 가져오기
  Integer getApplCount(Map<String, String> sqlData);

  // 현재 페이지 희망도서목록 가져오기
  List<Map<String, Object>> getApplList(Map<String, Object> pageData);
}