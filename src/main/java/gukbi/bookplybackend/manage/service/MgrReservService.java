package gukbi.bookplybackend.manage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrReservService {

  // 예약 테이블 총 개수
  ResponseDTO getReservCount(Map<String, String> sqlData);

  // 예약전체목록 데이터 받기
  ResponseDTO getReservList(Map<String, Object> pageData);
}
