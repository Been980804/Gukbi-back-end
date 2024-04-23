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

  // 총 도서 개수 가져오기
  Integer getBookCount(Map<String, String> sqlData);

  // 현재 페이지 도서목록 가져오기
  List<Map<String, Object>> getBookList(Map<String, Object> pageData);

  // 도서 상세정보 가져오기
  Map<String, Object> getBookInfo(String isbn);

  // 총 카테고리 도서 개수 가져오기
  Integer getCatCount(Map<String, String> sqlData);
}
