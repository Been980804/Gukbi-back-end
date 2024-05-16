package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookApplListService {
    
  // 도서 거래 게시글 수 조회
  ResponseDTO hopeBookListCnt(Map<String, Object> reqBody);
  // 도서 거래 게시글 조회
  ResponseDTO hopeBookList(Map<String, Object> pageMap);
}
