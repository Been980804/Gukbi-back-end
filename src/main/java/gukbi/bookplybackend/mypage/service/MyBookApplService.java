package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MyBookApplService {
  // 희망도서 총 게시글 수
  ResponseDTO getHopeBookCnt(String mem_no);
  // 희망도서 조회
  ResponseDTO getHopeBookList(Map<String, Object> pageMap);
  // 희망도서 취소
  ResponseDTO cancelHopeBook(Map<String, String> reqBody);
  // 도서거래 등록
  ResponseDTO bookApplReg(Map<String, Object> reqBody);
}
