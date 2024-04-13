package gukbi.bookplybackend.manage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.dao.MgrMapper;

@Service
public class MgrReservServiceImpl implements MgrReservService {

  @Autowired
  MgrMapper manageMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  @Transactional // 예약 테이블 총 개수
  public ResponseDTO getReservCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int reservCount = manageMapper.getReservCount(sqlData);

    if(reservCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 예약 개수 조회");
      res.setData("reservCount", reservCount);
    } else {
      res.setResCode(300);
      res.setResMsg("예약 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 예약전체목록 데이터 받기
  public ResponseDTO getReservList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> reservList = manageMapper.getReservList(pageData);

    if(!reservList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("예약 리스트 조회");
      res.setData("reservList", reservList);
    } else {
      res.setResCode(300);
      res.setResMsg("예약 리스트 조회에 실패했습니다.");
    }

    return res;
  }
}
