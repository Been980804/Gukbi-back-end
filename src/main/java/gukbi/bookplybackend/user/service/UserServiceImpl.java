package gukbi.bookplybackend.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.user.dao.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  @Transactional
  public ResponseDTO getUserList(String number) {
    ResponseDTO res = new ResponseDTO();

    List<Map<String, String>> userList = userMapper.getUserList(number);

    res.setResCode(200);
    res.setResMsg("유저 리스트 조회");
    res.setData("userList", userList);

    return res;
  }

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
}
