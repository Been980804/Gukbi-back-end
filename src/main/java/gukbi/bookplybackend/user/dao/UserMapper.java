package gukbi.bookplybackend.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

  List<Map<String, String>> getUserList(String number);

  // userInfo
  Map<String, String> selectUserInfo(Map<String, String> map);
}
