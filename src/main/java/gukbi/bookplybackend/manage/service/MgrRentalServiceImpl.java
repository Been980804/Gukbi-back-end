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
public class MgrRentalServiceImpl implements MgrRentalService {

  @Autowired
  MgrMapper manageMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  @Transactional // 총 회원 대여/연체정보 개수 가져오기
  public ResponseDTO getRentCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int rentCount = manageMapper.getRentCount(sqlData);

    if(rentCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 회원 대여/연체정보 개수 조회");
      res.setData("rentCount", rentCount);
    } else {
      res.setResCode(300);
      res.setResMsg("회원 대여/연체정보 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 회원 대여/연체정보 데이터 받기
  public ResponseDTO getRentList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> rentList = manageMapper.getRentList(pageData);

    if(!rentList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("대여/연체정보 리스트 조회");
      res.setData("rentList", rentList);
    } else {
      res.setResCode(300);
      res.setResMsg("대여/연체정보 리스트 조회에 실패했습니다.");
    }

    return res;
  }

}
