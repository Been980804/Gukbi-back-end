package gukbi.bookplybackend.manage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrRentalService {
  
  // 총 회원 대여/연체정보 개수 가져오기
  ResponseDTO getRentCount(Map<String, String> sqlData);

  // 회원 대여/연체정보 데이터 받기
  ResponseDTO getRentList(Map<String, Object> pageData);
}
