package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookTradeListService {
    
    // 거래도서 총 게시글 개수 조회
    ResponseDTO getBookTradeCnt(Map<String, Object> reqBody);
    // 거래도서 게시글 조회
    ResponseDTO getBookTradeList(Map<String, Object> pageMap);
}
