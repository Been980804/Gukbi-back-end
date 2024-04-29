package gukbi.bookplybackend.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.user.dao.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  @Transactional
  public ResponseDTO login(Map<String, String> reqBody) {
    ResponseDTO res = new ResponseDTO();

    Map<String, String> resMap = userMapper.selectUserInfo(reqBody);

    if(null != resMap) {
      String pwd = reqBody.get("pwd");

      if(pwd.equals(resMap.get("mem_pwd"))) {
        res.setResCode(200);
        res.setResMsg("Login Success");
        res.setData("userInfo", resMap);
        resMap.remove("mem_pwd");
      } else {
        res.setResCode(300);
        res.setResMsg("ID 또는 PW가 일치하지 않습니다.");
      }
    } else {            
      res.setResCode(300);
      res.setResMsg("ID 또는 PW가 일치하지 않습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 아이디 중복 체크
  public ResponseDTO duplicate(String memId) {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> userInfo = userMapper.duplicate(memId);

    if(userInfo != null) {
      res.setResCode(200);
      res.setResMsg("아이디 중복체크 조회");
      res.setData("userInfo", userInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("아이디 중복체크 조회에 실패했습니다.");
    }

    return res;
  }  
}
