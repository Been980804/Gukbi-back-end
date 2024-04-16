package gukbi.bookplybackend.mypage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.BookApplService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/bookAppl")
public class BookApplController {
    
    @Autowired
    private final BookApplService bookApplService;

    // 희망도서 리스트 조회
    @GetMapping(value="/hopeBookList/{mem_no}")
    public ResponseDTO hopeBookList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = bookApplService.getHopeBookList(mem_no);

        return res;
    }        

    // 희망도서 게시글 삭제
    @PostMapping(value="/cancelHopeBook")
    public ResponseDTO cancelHopeBook(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res = bookApplService.cancelHopeBook(reqBody);
        
        return res;
    }
    
}
