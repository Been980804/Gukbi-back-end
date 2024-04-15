package gukbi.bookplybackend.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.dao.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

  private final MyPageMapper myPageMapper;

  @Override
  @Transactional
  public ResponseDTO getRentList(String mem_no) { // 대여중인 도서 조회
    ResponseDTO res = new ResponseDTO();

    List<Map<String, String>> rentList = myPageMapper.getRentList(mem_no);

    if (null != rentList) {
      res.setResCode(200);
      res.setResMsg("대여중인 리스트 조회");
      res.setData("rentList", rentList);
    } else {
      res.setResCode(300);
      res.setResMsg("대여중인 리스트 조회 실패");
    }

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO getRentedList(String mem_no) { // 대여한 도서 조회
    ResponseDTO res = new ResponseDTO();

    List<Map<String, String>> rentedList = myPageMapper.getRentedList(mem_no);

    res.setResCode(200);
    res.setResMsg("대여한 리스트 조회");
    res.setData("rentedList", rentedList);

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO getReserveList(String mem_no) { // 예약중인 도서 조회
    ResponseDTO res = new ResponseDTO();

    List<Map<String, String>> reserveList = myPageMapper.getReserveList(mem_no);

    res.setResCode(200);
    res.setResMsg("대여한 리스트 조회");
    res.setData("reserveList", reserveList);

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO review(Map<String, Object> reqBody) { // 리뷰 작성
    ResponseDTO res = new ResponseDTO();

    int insertRow = myPageMapper.review(reqBody);

    if (insertRow > 0) {
      res.setResCode(200);
      res.setResMsg("리뷰작성 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("리뷰작성 실패");
    }
    return res;
  }

  @Override
  @Transactional
  public ResponseDTO returnBook(Map<String, String> reqBody) { // 도서 반납
    ResponseDTO res = new ResponseDTO();

    int updateRow = myPageMapper.returnBook(reqBody);

    if (updateRow > 0) {
      res.setResCode(200);
      res.setResMsg("도서반납 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("도서반납 실패");
    }
    return res;
  }

  // @Override
  // @Transactional
  // public ResponseDTO changeState(Map<String,String> reqBody) { // 대여상태 변경
  // ResponseDTO res = new ResponseDTO();

  // int updateRow = myPageMapper.changeState(reqBody);

  // if (updateRow > 0) {
  // res.setResCode(200);
  // res.setResMsg("대여상태 수정 성공");
  // } else {
  // res.setResCode(300);
  // res.setResMsg("대여상태 수정 실패");
  // }
  // return res;
  // }

  @Override
  @Transactional
  public ResponseDTO cancelReserveBook(Map<String, String> reqBody) { // 예약한 도서 취소
    ResponseDTO res = new ResponseDTO();

    int updateRow = myPageMapper.cancelReserveBook(reqBody);

    if (updateRow > 0) {
      res.setResCode(200);
      res.setResMsg("예약취소 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("예약취소 실패");
    }
    return res;
  }
}
