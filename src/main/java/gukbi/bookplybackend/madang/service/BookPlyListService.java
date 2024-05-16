package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookPlyListService {
  // 총 북플리 개수 조회
  ResponseDTO getBookPlyListCnt(Map<String, String> reqBody);
  // 북플리 목록 조회
  ResponseDTO getBookPlyList(Map<String, Object> pageMap);
}
