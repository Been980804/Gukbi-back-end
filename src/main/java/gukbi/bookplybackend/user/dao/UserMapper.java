package gukbi.bookplybackend.user.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
  // 로그인
  Map<String, String> selectUserInfo(Map<String, String> map);

  // 아이디 중복 체크
  Map<String, Object> duplicate(String memId);

  // 회원가입
  Integer join(Map<String, Object> sqlData);
}
