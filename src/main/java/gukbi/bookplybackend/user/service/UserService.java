package gukbi.bookplybackend.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface UserService {
  
  // 로그인
  ResponseDTO login(Map<String, String> reqBody);

  // 아이디 중복 체크
  ResponseDTO duplicate(String memId);

  // 회원가입
  ResponseDTO join(Map<String, Object> sqlData);
}
