package gukbi.bookplybackend.manage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrUserService {
  
  // 총 회원 개수 가져오기
  ResponseDTO getUserCount(Map<String, String> sqlData);

  // 현재 페이지 회원목록 가져오기
  ResponseDTO getUserList(Map<String, Object> pageData);

  // 총 회원 대여/연체정보 개수 가져오기
  ResponseDTO getRentCount(Map<String, String> sqlData);

  // 회원 대여/연체정보 가져오기
  ResponseDTO getUserInfo(Map<String, Object> pageData);

  // 회원 연체정보 개수 가져오기
  ResponseDTO getOverdueCount(String memNo);
}
