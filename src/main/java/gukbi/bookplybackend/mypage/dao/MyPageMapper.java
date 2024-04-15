package gukbi.bookplybackend.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MyPageMapper {
    
    // 회원    
    int getIdDuplicate(String mem_id); // 아이디 중복체크    
    String getCurrentPwd(String mem_no); // 현재비밀번호 조회    
    int pwdUpdate(Map<String, String> reqBody); // 비밀번호 수정    
    int accountDelete(Map<String,String> reqBody); // 회원 탈퇴    
    int updateUserInfo(Map<String, String> reqBody); // 회원정보 수정

    // 대여내역
    List<Map<String, String>> getRentList(String mem_no); // 대여중인 도서 조회
    List<Map<String, String>> getRentedList(String mem_no); // 대여한 도서 조회
    List<Map<String, String>> getReserveList(String mem_no); // 예약중인 도서 조회
    int review(Map<String,Object> reqBody); // 리뷰 작성
    int returnBook(Map<String,String> reqBody); // 도서 반납
    // int changeState(Map<String,String> reqBody); // 대여상태 변경 
    int cancelReserveBook(Map<String,String> reqBody); // 예약한 도서 취소
}
