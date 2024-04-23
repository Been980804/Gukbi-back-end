package gukbi.bookplybackend.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.BookPlyService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/bookPly")
public class BookPlyController {
    
    private static final int showCnt = 3;

    @Autowired
    private final BookPlyService bookPlyService;

    // 북플리 총 개수 조회
    @GetMapping(value="/bookPlyCnt/{mem_no}")
    public ResponseDTO getMethodName(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = bookPlyService.getBookPlyCnt(mem_no);

        return res;
    }
    

    // 북플리 목록 조회
    @GetMapping(value="/bookPlyList/{nowPage}")
    public ResponseDTO getBookPlyList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String,String> reqBody){
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("showCnt", showCnt);
        pageMap.put("nowPage", (nowPage - 1) * 3);
        pageMap.put("mem_no", reqBody.get("mem_no"));

        ResponseDTO res = bookPlyService.getBookPlyList(pageMap);
        return res;
    }

    // 공개여부 수정
    @PutMapping(value="/updateVisibility")
    public ResponseDTO updateVisibility(@RequestBody Map<String,String> reqBody) {
        ResponseDTO res = bookPlyService.updateVisibility(reqBody);
        
        return res;
    }

    // 북플리 상세보기 조회
    @GetMapping(value="/getDetailBookList/{bpl_no}")
    public ResponseDTO getDetailBookList(@PathVariable("bpl_no") String bpl_no, @RequestParam("mem_no") String mem_no){
        ResponseDTO res = bookPlyService.getDetailBookList(bpl_no, mem_no);

        return res;
    }

    // 북플리 좋아요
    @PostMapping(value="/updateLike")
    public ResponseDTO updateLike(@RequestBody Map<String, String> reqBody){
        ResponseDTO res = bookPlyService.updateLike(reqBody);

        return res;
    }

    // 북플리 생성
    @PostMapping(value="/createBpl")
    public ResponseDTO createBpl(@RequestBody Map<String, String> reqBody){
        ResponseDTO res = bookPlyService.createBpl(reqBody);


        return res;
    }

    // 북플리 일괄 삭제
    @PostMapping(value="/deleteBpl")
    public ResponseDTO deleteBpl(@RequestBody Map<String, Object> reqBody) {
        ResponseDTO res = bookPlyService.deleteBpl(reqBody);

        return res;
    }
    
    // 북플리내 책 일괄 삭제
    @PostMapping(value="/deleteBook")
    public ResponseDTO deleteBook(@RequestBody Map<String, Object> reqBody) {
        ResponseDTO res = bookPlyService.deleteBook(reqBody);
        
        return res;
    }
    
    // 북플리 정보 수정
    @PostMapping(value="/updateBpl")
    public ResponseDTO updateBpl(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res = bookPlyService.updateBpl(reqBody);
        
        return res;
    }
    
    // 북플리에 책 담기
    @PostMapping(value="/addBpl")
    public ResponseDTO addBpl(@RequestBody Map<String,Object> reqBody) {
        ResponseDTO res = bookPlyService.addBpl(reqBody);
        
        return res;
    }
    
}
