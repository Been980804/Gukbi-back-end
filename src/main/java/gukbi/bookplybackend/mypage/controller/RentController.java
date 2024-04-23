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
import gukbi.bookplybackend.mypage.service.RentService;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/rent")
public class RentController {

    private static final int showCnt = 10;

    @Autowired
    private final RentService rentService;

    // 현재 대여중인 도서 리스트 조회
    @GetMapping(value = "/rentList/{mem_no}")
    public ResponseDTO rentList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = rentService.getRentList(mem_no);

        return res;
    }

    // 대여했던 총 도서 수
    @GetMapping(value="/rentedCnt/{mem_no}")
    public ResponseDTO getRentedCnt(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = rentService.getRentedCnt(mem_no);

        return res;
    }
    

    // 대여했던 도서 리스트 조회
    @GetMapping(value = "/rentedList/{nowPage}")
    public ResponseDTO rentedList(@PathVariable("nowPage") int nowPage , @RequestParam Map<String,String> reqBody) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("showCnt", showCnt);
        pageMap.put("nowPage", (nowPage - 1) * 10);
        pageMap.put("mem_no", reqBody.get("mem_no"));
       
        ResponseDTO res = rentService.getRentedList(pageMap);
        return res;
    }

    // 현재 예약중인 도서 리스트 조회
    @GetMapping(value = "/reserveList/{mem_no}")
    public ResponseDTO reserveList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = rentService.getReserveList(mem_no);

        return res;
    }

    // 리뷰작성
    @PostMapping(value = "/review")
    public ResponseDTO review(@RequestBody Map<String, Object> reqBody) {
        ResponseDTO res = rentService.review(reqBody);

        return res;
    }

    // 도서 반납
    @PostMapping(value = "/returnBook")
    public ResponseDTO returnBook(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res = rentService.returnBook(reqBody);

        return res;
    }

    // 대여 상태 변경
    @PutMapping("/changeRentState/{rent_no}")
    public ResponseDTO changeRentState(@PathVariable("rent_no") String rent_no) {
        ResponseDTO res = rentService.changeRentState(rent_no);

        return res;
    }

    // 도서 예약 취소
    @PostMapping(value="/cancelReserveBook")
    public ResponseDTO cancelReserveBook(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res =rentService.cancelReserveBook(reqBody);
        
        return res;
    }
    
}
