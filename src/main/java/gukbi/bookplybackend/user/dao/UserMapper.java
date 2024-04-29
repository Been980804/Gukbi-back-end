package gukbi.bookplybackend.user.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

  Map<String, String> selectUserInfo(Map<String, String> map);

  // 아이디 중복 체크
  Map<String, Object> duplicate(String memId);
}
