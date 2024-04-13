package gukbi.bookplybackend.manage.service;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface MgrSidebarService {

  // 대메뉴 가져오기
  ResponseDTO getLargeMenu(int level);

  // 소메뉴 가져오기
  ResponseDTO getSmallMenu(String menuNo);
}
