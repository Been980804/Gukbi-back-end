package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MyInquiryService {
  // 문의내역 게시글 수 조회
  ResponseDTO getMyInquiryCnt(String mem_no);
  // 문의내역 조회
  ResponseDTO getMyInquiry(Map<String, Object> pageMap);
  // 문의내역 삭제
  ResponseDTO deleteInquiry(Map<String, String> reqBody);
  // 공개여부 수정
  ResponseDTO updateVisibility(Map<String, Integer> reqBody);
}
