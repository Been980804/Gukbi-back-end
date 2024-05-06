package gukbi.bookplybackend.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    int getRentedCnt(String mem_no); // 대여한 총 도서 수 조회
    List<Map<String, String>> getRentedList(Map<String, Object> pageMap); // 대여한 도서 조회
    List<Map<String, String>> getReserveList(String mem_no); // 예약중인 도서 조회
    int review(Map<String,Object> reqBody); // 리뷰 작성
    int returnBook(Map<String,String> reqBody); // 도서 반납
    int changeRentState(String rent_no);
    int cancelReserveBook(Map<String,String> reqBody); // 예약한 도서 취소

    // 희망도서
    int getHopeBookCnt(String mem_no);
    List<Map<String, String>> getHopeBookList(Map<String, Object> pageMap); // 희망도서 조회
    int cancelHopeBook(Map<String, String> reqBody); // 희망도서 취소

    // 북플리
    int getBookPlyCnt(String mem_no); // 북플리 개수 조회
    List<Map<String, String>> getBookPlyList(Map<String, Object> pageMap); // 북플리 목록 조회
    int updateVisibilityBpl(Map<String, String> reqBody); // 공개여부 수정
    List<Map<String, String>> detailList(String bpl_no); // 북플리 상세보기 조회
    Map<String, String> getLikeInfo(@Param("bpl_no") String bpl_no,@Param("mem_no") String mem_no); // 상세보기 페이지에 좋아요수 조회
    Map<String, String> checkLike(Map<String, String> reqBody); // 좋아요테이블에 유무 체크
    int updateLikeTable(Map<String, String> reqBody); // 좋아요테이블 수정
    int insertLike(Map<String, String> reqBody); // 좋아요테이블 생성
    int updateLikeCnt(Map<String, String> reqBody); // 좋아요수 수정
    Map<String, String> likeCount(Map<String, String> reqBody); // 좋아요수 조회
    int createBpl(Map<String, String> reqBody); // 북플리 생성
    int deleteBpl(Map<String, Object> reqBody); // 북플리 삭제
    int deleteBook(Map<String, Object> reqBody); // 북플리 상세보기 도서 삭제
    int updateBookCnt(Map<String, Object> reqBody); // 도서권수 업데이트(삭제시)
    int updateBookCnt2(String reqBody); // 도서권수 업데이트(생성시)
    int updateBpl(Map<String, String> reqBody); // 북플리 수정
    int addBpl(Map<String,String> reqBody); // 북플리상세보기 도서 추가
    Map<String, String> getBookExist(Map<String,String> reqBody); // 상세북플리 테이블에 유무 체크
    int addBpl_update(Map<String,String> reqBody); // 테이블있을시 use_flag 수정
    List<Map<String, Object>> getMyBookPlyList(String mem_no); // 북플리에 도서 추가시 내 북플리 목록 조회

    // 문의내역
    int getMyInquiryCnt(String mem_no); // 문의내역 게시글 수 조회
    List<Map<String, String>> getMyInquiry(Map<String, Object> pageMap); // 문의내역 조회
    int deleteInquiry(Map<String, String> reqBody); // 문의내역 삭제
    int updateVisibilityQna(Map<String, Integer> reqBody); // 공개여부 수정

    // 도서거래
    int getTradeCnt(String mem_no); // 거래내역 총 게시글 수 조회
    List<Map<String, String>> getTradeList(Map<String, Object> pageMap); // 거래내역 조회
    int deleteTrade(Map<String, Object> reqBody); // 거래내역 삭제
    int bookApplReg(Map<String, Object> reqBody); // 도서거래 등록
}
