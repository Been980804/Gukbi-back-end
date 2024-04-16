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
import gukbi.bookplybackend.mypage.service.InquiryService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/inquiry")
public class InquiryController {
    
    @Autowired
    private final InquiryService inquiryService;

    // 개별문의내역 리스트 조회
    @GetMapping(value="/getMyInquiry/{mem_no}")
    public ResponseDTO getMyInquiry(@PathVariable("mem_no") String mem_no){
        ResponseDTO res = inquiryService.getMyInquiry(mem_no);

        return res;
    }

    // 개별문의내역 삭제
    @PostMapping(value="/deleteInquiry")
    public ResponseDTO deleteInquiry(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res = inquiryService.deleteInquiry(reqBody);
        
        return res;
    }
    
}
