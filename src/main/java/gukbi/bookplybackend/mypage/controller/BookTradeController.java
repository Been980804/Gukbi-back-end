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
import gukbi.bookplybackend.mypage.service.BookTradeService;
import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/trade")
public class BookTradeController {
    
    @Autowired
    private final BookTradeService bookTradeService;
    
    // 거래내역 조회
    @GetMapping(value="/getTradeList/{mem_no}")
    public ResponseDTO getTradeList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = bookTradeService.getTradeList(mem_no);

        return res;
    }
    
    // 거래내역 삭제
    @PostMapping("/deleteTrade")
    public ResponseDTO deleteTrade(@RequestBody Map<String, Object> reqBody) {
        ResponseDTO res = bookTradeService.deleteTrade(reqBody);
        
        return res;
    }
    
}
