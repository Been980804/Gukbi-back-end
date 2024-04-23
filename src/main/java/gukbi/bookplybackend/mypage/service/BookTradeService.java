package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookTradeService {
    // 도서거래 총 게시물 개수 조회
    ResponseDTO getTradeCnt(String mem_no);
    // 도서거래 게시물 조회
    ResponseDTO getTradeList(Map<String, Object> pageMap);
    // 도서거래 게시물 삭제
    ResponseDTO deleteTrade(Map<String, Object> reqBody);
}
