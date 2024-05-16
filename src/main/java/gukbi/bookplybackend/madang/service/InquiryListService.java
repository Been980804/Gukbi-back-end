package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface InquiryListService {
    
  // 자주하는 질문 조회
  ResponseDTO getFaq();
  // 문의내역 개수 조회
  ResponseDTO getQnaCnt(Map<String, Object> reqBody);
  // 문의내역 조회
  ResponseDTO getQna(Map<String, Object> pageMap);
  // 문의사항 등록
  ResponseDTO inquiryReg(Map<String, Object> reqBody);
}
