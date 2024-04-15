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
import gukbi.bookplybackend.mypage.service.RentService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/rent")
public class RentController {

    @Autowired
    private final RentService rentService;

    // 현재 대여중인 도서 리스트 조회
    @GetMapping(value = "/rentList/{mem_no}")
    public ResponseDTO rentList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = rentService.getRentList(mem_no);

        return res;
    }

    // 대여했던 도서 리스트 조회
    @GetMapping(value = "/rentedList/{mem_no}")
    public ResponseDTO rentedList(@PathVariable("mem_no") String mem_no) {
        ResponseDTO res = rentService.getRentedList(mem_no);

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
    // @PutMapping("/changeState")
    // public ResponseDTO changeState(@RequestBody Map<String,String> reqBody) {
    //     ResponseDTO res = rentService.changeState(reqBody);

    //     return res;
    // }

    // 도서 예약 취소
    @PostMapping(value="/cancelReserveBook")
    public ResponseDTO cancelReserveBook(@RequestBody Map<String, String> reqBody) {
        ResponseDTO res =rentService.cancelReserveBook(reqBody);
        
        return res;
    }
    
}
