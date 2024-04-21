package gukbi.bookplybackend.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import gukbi.bookplybackend.common.dao.CommonMapper;
import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public class CommonServiceImpl implements CommonService {

  @Autowired
  CommonMapper commonMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  @Transactional // 대메뉴 가져오기
  public ResponseDTO getLargeMenu(int level) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> menuList = commonMapper.getLargeMenu(level);

    if(!menuList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("대메뉴 리스트 조회");
      res.setData("menuList", menuList);
    } else {
      res.setResCode(300);
      res.setResMsg("대메뉴 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 소메뉴 가져오기
  public ResponseDTO getSmallMenu() {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> menuList = commonMapper.getSmallMenu();

    if(!menuList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("소메뉴 리스트 조회");
      res.setData("menuList", menuList);
    } else {
      res.setResCode(300);
      res.setResMsg("소메뉴 리스트 조회에 실패했습니다.");
    }

    return res;
  }

	@Override
  @Transactional // 추천도서 정보 가져오기
	public ResponseDTO sugBookInfo() {
		ResponseDTO res = new ResponseDTO();
    Map<String, Object> bookInfo = commonMapper.sugBookInfo();

    if(bookInfo != null) {
      res.setResCode(200);
      res.setResMsg("추천도서 정보 조회");
      res.setData("sugBookInfo", bookInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("추천도서 정보 조회에 실패했습니다.");
    }

    return res;
	}

  @Override
  @Transactional // 총 도서 개수 가져오기 
  public ResponseDTO getBookCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int bookCount = commonMapper.getBookCount(sqlData);

    if(bookCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 도서 개수 조회");
      res.setData("bookCount", bookCount);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 개수 조회에 실패했습니다.");
    }

    return res;
  }
  
}
