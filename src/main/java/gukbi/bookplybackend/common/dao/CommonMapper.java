package gukbi.bookplybackend.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonMapper {
  
  // 대메뉴 가져오기
  List<Map<String, Object>> getLargeMenu(int level);

  // 소메뉴 가져오기
  List<Map<String, Object>> getSmallMenu();

  // 추천도서 정보 가져오기
  Map<String, Object> sugBookInfo();
}
