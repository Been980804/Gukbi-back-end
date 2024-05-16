package gukbi.bookplybackend.madang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MadangMapper {

  // 북플리    
  int getBookPlyListCnt(Map<String,String> reqBody); // 북플리 총 개수 조회
  List<Map<String, Object>> getBookPlyList(Map<String,Object> pageMap); // 북플리 조회

  // 도서 거래
  int getBookTradeCnt(Map<String, Object> reqBody); // 도서거래 게시글 개수 조회
  List<Map<String, Object>> getBookTradeList(Map<String, Object> pageMap); // 도서거래 게시글 조회
  Map<String, Object> getTradeDetail(String trade_no); // 도서거래 상세정보 조회
  int tradeModi(Map<String, Object> reqBody); // 도서거래 수정
  int getMaxTradeNo(); // trade_no 마지막 번호 가져오기
  int tradeReg(Map<String, Object> reqBody); // 도서거래 등록

  // 문의사항
  List<Map<String,Object>> getFaq(); // 자주하는 질문 조회
  int getQnaCnt(Map<String,Object> reqBody); // 문의내역 게시글 수 조회
  List<Map<String,Object>> getQna(Map<String,Object> pageMap); // 문의내역 게시글 조회
  int getMaxInquiryNo(); // qna_no 마지막 번호 가져오기
  int inquiryReg(Map<String, Object> reqBody); // 문의사항 등록

  // 공지사항
  int notiListCnt(Map<String, Object> reqBody); // 공지사항 게시글 수 조회
  List<Map<String, Object>> notiList(Map<String, Object> pageMap); // 공지사항 조회
  int updateNotiViews(String noti_no); // 공지사항 조회수 증가
  Map<String, Object> notiDetail(String notiNo); // 공지사항 상세보기

  // 도서신청
  int hopeBookListCnt(Map<String, Object> reqBody); // 도서신청 게시글 수 조회
  List<Map<String, Object>> hopeBookList(Map<String, Object> pageMap); // 도서신청 게시글 조회
}
