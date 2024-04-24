package gukbi.bookplybackend.madang.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MadangMapper {
    // 북플리 총 개수 조회
    int getBookPlyListCnt(Map<String,String> reqBody);
    // 북플리 조회
    List<Map<String, Object>> getBookPlyList(Map<String,Object> pageMap);
}
