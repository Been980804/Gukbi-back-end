package gukbi.bookplybackend.madang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.service.InquiryListService;
import lombok.RequiredArgsConstructor;




@RestController
@RequiredArgsConstructor
@RequestMapping("/madang/inquiry")
public class InquiryListController {
    
    public static final int showCnt = 10;

    @Autowired
    InquiryListService inquiryService;

    @GetMapping(value="/getFaq")
    public ResponseDTO getFaq() {
        ResponseDTO res = inquiryService.getFaq();

        return res;
    }
    
    @GetMapping(value="/getQnaCnt")
    public ResponseDTO getQnaCnt(@RequestParam Map<String, Object> reqBody) {
        ResponseDTO res = inquiryService.getQnaCnt(reqBody);
        
        return res;
    }
    
    @GetMapping(value="/getQna/{nowPage}")
    public ResponseDTO getQna(@PathVariable("nowPage") int nowPage,@RequestParam Map<String, Object> reqBody) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("showCnt", showCnt);
        pageMap.put("nowPage", (nowPage - 1) * 10);
        pageMap.put("column", reqBody.get("column"));
        pageMap.put("search", reqBody.get("search"));

        ResponseDTO res = inquiryService.getQna(pageMap);
        
        return res;
    }
    
}
