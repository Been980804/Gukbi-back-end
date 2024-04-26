package gukbi.bookplybackend.madang.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.service.NotificationListService;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/madang/notiList")
public class NotificationListController {
    
    public static final int showCnt = 10;

    @Autowired
    NotificationListService notiService;

    // 공지사항 게시글 수 조회
    @GetMapping(value="/notiListCnt")
    public ResponseDTO notiListCnt(@RequestParam Map<String, Object> reqBody) {
        ResponseDTO res = notiService.notiListCnt(reqBody);

        return res;
    }

    // 공지사항 조회
    @GetMapping(value="/notiList/{nowPage}")
    public ResponseDTO notiList(@PathVariable("nowPage") int nowPage, @RequestParam Map<String, Object> reqBody) {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("showCnt", showCnt);
        pageMap.put("nowPage", (nowPage - 1) * 10);
        pageMap.put("column", reqBody.get("column"));
        pageMap.put("search", reqBody.get("search"));

        ResponseDTO res = notiService.notiList(pageMap);

        return res;
    }
    
       // 공지사항 조회수 증가
       @PutMapping(value="/updateNotiViews/{noti_no}")
       public ResponseDTO updateNotiViews(@PathVariable("noti_no") String noti_no) {
           ResponseDTO res = notiService.updateNotiViews(noti_no);
           
           return res;
       }
   
       // 공지사항 상세보기
       @GetMapping(value="/notiDetail/{notiNo}")
       public ResponseDTO notiDetail(@PathVariable("notiNo") String notiNo) {
           ResponseDTO res = notiService.notiDetail(notiNo);
   
           return res;
       }
}
