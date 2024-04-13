package gukbi.bookplybackend.manage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrBoardService {
  
  // 총 공지사항 개수 가져오기
  ResponseDTO getNotiCount(Map<String, String> sqlData);

  // 현재 페이지 공지사항목록 가져오기
  ResponseDTO getNotiList(Map<String, Object> pageData);

  // 공지사항 상세정보 가져오기
  ResponseDTO getNotiInfo(String notiNo);

  // 공지사항 정보 삭제
  ResponseDTO notiDelete(String notiNo);


  // 총 문의사항 개수 가져오기
  ResponseDTO getInquiryCount(Map<String, String> sqlData);

  // 현재 페이지 문의사항목록 가져오기
  ResponseDTO getInquiryList(Map<String, Object> pageData);

  // 문의사항 상세정보 가져오기
  ResponseDTO getInquiryInfo(String inquiryNo);

  // 문의사항 답장 등록
  ResponseDTO registAnswer(Map<String, String> sqlData);


  // 총 도서거래 개수 가져오기
  ResponseDTO getTradeCount(Map<String, String> sqlData);

  // 현재 페이지 도서거래목록 가져오기
  ResponseDTO getTradeList(Map<String, Object> pageData);

  // 도서거래 상세정보 가져오기
  ResponseDTO getTradeInfo(String tradeNo);


  // 총 희망도서 개수 가져오기
  ResponseDTO getApplCount(Map<String, String> sqlData);

  // 현재 페이지 희망도서목록 가져오기
  ResponseDTO getApplList(Map<String, Object> pageData);
}
