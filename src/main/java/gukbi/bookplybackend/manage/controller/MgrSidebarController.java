package gukbi.bookplybackend.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.service.MgrSidebarService;

@RestController
@RequestMapping("/sidebar")
public class MgrSidebarController {

  @Autowired
  MgrSidebarService sidebarService;

  // 대메뉴 가져오기
  @GetMapping("/largeMenu/{level}")
  public ResponseDTO getLargeMenu(@PathVariable(value = "level") int level) {
    ResponseDTO res = sidebarService.getLargeMenu(level);
    return res;
  }

  // 소메뉴 가져오기
  @GetMapping("/smallMenu/{menuNo}")
  public ResponseDTO getSmallMenu(@PathVariable(value = "menuNo") String menuNo) {
    ResponseDTO res = sidebarService.getSmallMenu(menuNo);
    return res;
  }
}
