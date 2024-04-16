package gukbi.bookplybackend.mypage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.dao.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookPlyServiceImpl implements BookPlyService {

    private final MyPageMapper myPageMapper;

    @Override
    @Transactional
    public ResponseDTO getBookPlyList(String mem_no) { // 북플리 목록 조회
        ResponseDTO res = new ResponseDTO();

        List<Map<String, String>> bookPlyList = myPageMapper.getBookPlyList(mem_no);

        res.setResCode(200);
        res.setResMsg("북플리 목록 조회");
        res.setData("bookPlyList", bookPlyList);

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO updateVisibility(Map<String, String> reqBody) { // 공개여부 수정
        ResponseDTO res = new ResponseDTO();

        int updateRow = myPageMapper.updateVisibility(reqBody);

        if (updateRow > 0) {
            res.setResCode(200);
            res.setResMsg("공개여부 수정 성공");
        } else {
            res.setResCode(300);
            res.setResMsg("공개여부 수정 실패");
        }

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO getDetailBookList(String bpl_no, String mem_no) { // 북플리 상세보기 조회
        ResponseDTO res = new ResponseDTO();

        List<Map<String, String>> detailBookList = myPageMapper.detailList(bpl_no);

        Map<String, String> likeInfo = myPageMapper.getLikeInfo(bpl_no, mem_no);

        res.setResCode(200);
        res.setResMsg("상세보기 책 조회 성공");
        res.setData("detailBookList", detailBookList);
        res.setData("isLiked", likeInfo.get("isLiked"));

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO updateLike(Map<String, String> reqBody) { // 좋아요수 수정
        ResponseDTO res = new ResponseDTO();

        // 선호 북플리테이블에 담겨있는지 확인
        Map<String, String> likeExist = myPageMapper.checkLike(reqBody);

        if (null != likeExist) { // use_flag가 Y인지 N인지에 따라 like_cnt 증가, 감소
            int updateRow = myPageMapper.updateLikeTable(reqBody);

            if (updateRow > 0) {
                int updateLikeRow = myPageMapper.updateLikeCnt(reqBody);
                Map<String, String> likeCount = myPageMapper.likeCount(reqBody);

                if (updateLikeRow > 0 && likeCount != null) {
                    res.setResCode(200);
                    res.setResMsg("좋아요수 수정 성공");
                    res.setData("likeCount", likeCount);
                } else {
                    res.setResCode(300);
                    res.setResMsg("좋아요수 수정 실패");
                }
            } else {
                res.setResCode(300);
                res.setResMsg("좋아요 수정 실패");
            }

        } else { // 선호 북플리테이블에 insert
            int insertRow = myPageMapper.insertLike(reqBody);

            if (insertRow > 0) {
                int updateLikeRow = myPageMapper.updateLikeCnt(reqBody);
                Map<String, String> likeCount = myPageMapper.likeCount(reqBody);

                if (updateLikeRow > 0 && likeCount != null) {
                    res.setResCode(200);
                    res.setResMsg("좋아요수 수정 성공");
                    res.setData("likeCount", likeCount);
                } else {
                    res.setResCode(300);
                    res.setResMsg("좋아요수 수정 실패");
                }
            } else {
                res.setResCode(300);
                res.setResMsg("선호북플리 생성 실패");
            }
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseDTO createBpl(Map<String, String> reqBody) { // 북플리 생성
        ResponseDTO res = new ResponseDTO();

        int insertRow = myPageMapper.createBpl(reqBody);

        if (insertRow > 0) {
            res.setResCode(200);
            res.setResMsg("북플리생성 성공");
        } else {
            res.setResCode(300);
            res.setResMsg("북플리생성 실패");
        }

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO deleteBpl(Map<String, Object> reqBody) { // 북플리 삭제
        ResponseDTO res = new ResponseDTO(); 

        int updateRow = myPageMapper.deleteBpl(reqBody);

        if (updateRow > 0) {
            res.setResCode(200);
            res.setResMsg("북플리 삭제 성공");
        } else {
            res.setResCode(300);
            res.setResMsg("북플리 삭제 실패");
        }

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO deleteBook(Map<String, Object> reqBody) { // 북플리 상세보기 도서 삭제
        ResponseDTO res = new ResponseDTO();

        // 상세 북플리에서 논리 삭제
        int updateRow = myPageMapper.deleteBook(reqBody);

        if (updateRow > 0) {
            int updateRow2 = myPageMapper.updateBookCnt(reqBody);

            if (updateRow2 > 0) {
                res.setResCode(200);
                res.setResMsg("북플리 삭제 성공");
            } else {
                res.setResCode(300);
                res.setResMsg("북플리 삭제 실패");
            }
        } else {
            res.setResCode(300);
            res.setResMsg("북플리 삭제 실패");
        }

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO updateBpl(Map<String, String> reqBody) { // 북플리 수정
        ResponseDTO res = new ResponseDTO();

        int updateRow = myPageMapper.updateBpl(reqBody);

        if (updateRow > 0) {
            res.setResCode(200);
            res.setResMsg("북플리 수정 성공");
        } else {
            res.setResCode(300);
            res.setResMsg("북플리 수정 실패");
        }

        return res;
    }

    @Override
    @Transactional
    public ResponseDTO addBpl(Map<String, Object> reqBody) { // 북플리 상세보기 도서 추가
        ResponseDTO res = new ResponseDTO();

        String bpl_no = (String) reqBody.get("bpl_no");
        @SuppressWarnings("unchecked")
        List<String> book_nos = (List<String>) reqBody.get("book_no");

        for (String book_no : book_nos) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("bpl_no", bpl_no);
            parameter.put("book_no", book_no);

            Map<String, String> bookExist = myPageMapper.getBookExist(parameter);  // bpl_detail에 있는지 확인

            // 있음
            if (bookExist != null) {
                int updateRow = myPageMapper.addBpl_update(parameter);

                if (updateRow <= 0) {
                    res.setResCode(300);
                    res.setResMsg("북플리 책 담기 실패");
                } else {
                    int updateRow2 = myPageMapper.updateBookCnt2(bpl_no);

                    if (updateRow2 <= 0) {
                        res.setResCode(300);
                        res.setResMsg("북플리 책 담기 실패");
                    }
                }
            // 없음
            } else { 
                int insertRow = myPageMapper.addBpl(parameter);

                if (insertRow <= 0) {
                    res.setResCode(300);
                    res.setResMsg("북플리 책 담기 실패");
                } else {
                    int updateRow = myPageMapper.updateBookCnt2(bpl_no);

                    if (updateRow <= 0) {
                        res.setResCode(300);
                        res.setResMsg("북플리 책 담기 실패");
                    }
                }

            }

        }
        res.setResCode(200);
        res.setResMsg("북플리 책 담기 실패");
        return res;
    }
}
