package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookPlyService {
    // 북플리 개수 조회
    ResponseDTO getBookPlyCnt(String mem_no);
    // 북플리 목록 조회
    ResponseDTO getBookPlyList(Map<String, Object> pageMap);
    // 공개여부 수정
    ResponseDTO updateVisibility(Map<String,String> bpl_no);
    // 북플리 상세보기 조회
    ResponseDTO getDetailBookList(String bpl_no, String mem_no);
    // 좋아요 수정
    ResponseDTO updateLike(Map<String, String> reqBody);
    // 북플리 생성
    ResponseDTO createBpl(Map<String, String> reqBody);
    // 북플리 삭제
    ResponseDTO deleteBpl(Map<String, Object> reqBody);
    // 북플리 상세보기 도서 삭제
    ResponseDTO deleteBook(Map<String, Object> reqBody);
    // 북플리 수정
    ResponseDTO updateBpl(Map<String, String> reqBody);
    // 북플리에 도서 추가
    ResponseDTO addBpl(Map<String, Object> reqBody);
}
