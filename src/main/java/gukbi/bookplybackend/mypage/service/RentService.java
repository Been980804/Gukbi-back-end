package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface RentService {
  // 대여중인 도서 조회
  ResponseDTO getRentList(String mem_no);
  // 대여한 총 도서 수 조회
  ResponseDTO getRentedCnt(String mem_no);
  // 대여한 도서 조회
  ResponseDTO getRentedList(Map<String,Object> pageMap);
  // 예약중인 도서 조회
  ResponseDTO getReserveList(String mem_no);
  // 리뷰 작성
  ResponseDTO review(Map<String, Object> reqBody);
  // 도서 반납
  ResponseDTO returnBook(Map<String,String> reqBody);
  // 대여상태 변경
  ResponseDTO changeRentState(String rent_no);
  // 예약도서 취소
  ResponseDTO cancelReserveBook(Map<String,String> reqBody);
}
