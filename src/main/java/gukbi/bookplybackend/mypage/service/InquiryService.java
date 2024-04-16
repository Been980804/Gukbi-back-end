package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface InquiryService {
    // 문의내역 조회
    ResponseDTO getMyInquiry(String mem_no);
    // 문의내역 삭제
    ResponseDTO deleteInquiry(Map<String, String> reqBody);
}
