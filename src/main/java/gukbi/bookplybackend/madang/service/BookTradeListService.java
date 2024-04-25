package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookTradeListService {
    
    // 도서거래 총 게시글 개수 조회
    ResponseDTO getBookTradeCnt(Map<String, Object> reqBody);
    // 도서거래 게시글 조회
    ResponseDTO getBookTradeList(Map<String, Object> pageMap);
    // 도서거래 상세보기
    ResponseDTO getTradeDetail(String tradeNo);
    // 도서거래 수정
    ResponseDTO tradeModi(Map<String,Object> reqBody);
    // 도서거래 등록
    ResponseDTO tradeReg(Map<String,Object> reqBody);
}
