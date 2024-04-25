package gukbi.bookplybackend.madang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.service.BookTradeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
@RequiredArgsConstructor
@RequestMapping("/madang/bookTrade")
public class BookTradeListController {
    
    public static final int showCnt = 10;

    @Autowired
    BookTradeListService bookTradeService;

    // 도서거래ㄴㄴ 총 게시글 개수 조회
    @GetMapping(value="/bookTradeCnt")
    public ResponseDTO getBookTradeCnt(@RequestParam Map<String,Object> reqBody) {             
        ResponseDTO res = bookTradeService.getBookTradeCnt(reqBody);
        
        return res;
    }

    // 도서거래 게시글 조회
    @GetMapping(value="/bookTradeList/{nowPage}")
    public ResponseDTO getBookTradeList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String,Object> reqBody) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("showCnt", showCnt);
        pageMap.put("nowPage", (nowPage - 1) * 10);
        pageMap.put("column", reqBody.get("column"));
        pageMap.put("search", reqBody.get("search"));

        ResponseDTO res = bookTradeService.getBookTradeList(pageMap);
        
        return res;
    }
    
    // 도서거래 상세정보 조회
    @GetMapping(value="/tradeDetail/{tradeNo}")
    public ResponseDTO getTradeDetail(@PathVariable("tradeNo") String tradeNo) {
        ResponseDTO res = bookTradeService.getTradeDetail(tradeNo);
        
        return res;
    }
    
    // 도서거래 수정
    @PutMapping(value="/tradeModi")
    public ResponseDTO putMethodName(@RequestBody Map<String,Object> reqBody) {
        ResponseDTO res = bookTradeService.tradeModi(reqBody);
      
        return res;
    }

    // 도서거래 등록
    @PostMapping(value="/tradeReg")
    public ResponseDTO tradeReg(@RequestBody Map<String, Object> reqBody) {
        ResponseDTO res = bookTradeService.tradeReg(reqBody);
        
        return res;
    }
    
}
