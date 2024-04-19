package gukbi.bookplybackend.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.service.MyPageSidebarService;




@RestController
@RequestMapping("/myPageSidebar")
public class MyPageSidebarController {
    
    @Autowired
    MyPageSidebarService myPageSidebarService;

    // 대메뉴 가져오기
    @GetMapping(value="/largeMenu/{level}")
    public ResponseDTO getLargeMenu(@PathVariable("level") int level) {
        ResponseDTO res = myPageSidebarService.getLargeMenu(level);

        return res;
    }
    
    // 소메뉴 가져오기
    @GetMapping(value="/smallMenu")
    public ResponseDTO getSmallMenu() {
        ResponseDTO res = myPageSidebarService.getSmallMenu();

        return res;
    }
    
}
