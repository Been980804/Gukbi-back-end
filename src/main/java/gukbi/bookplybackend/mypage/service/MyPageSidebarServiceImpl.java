package gukbi.bookplybackend.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.dao.MyPageMapper;

@Service
public class MyPageSidebarServiceImpl implements MyPageSidebarService{
    
    @Autowired
    MyPageMapper myPageMapper;

    @Override
    @Transactional
    public ResponseDTO getLargeMenu(int level) { // 대메뉴 가져오기
        ResponseDTO res = new ResponseDTO();

        List<Map<String,Object>> menuList = myPageMapper.getLargeMenu(level);

        if(null != menuList){
            res.setResCode(200);
            res.setResMsg("대메뉴 조회");
            res.setData("menuList", menuList);
        }else{
            res.setResCode(300);
            res.setResMsg("대메뉴 조회 실패");
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseDTO getSmallMenu() {
        ResponseDTO res = new ResponseDTO();
        
        List<Map<String,Object>> menuList = myPageMapper.getSmallMenu();

        if(null != menuList){
            res.setResCode(200);
            res.setResMsg("소메뉴 조회");
            res.setData("menuList", menuList);
        }else{
            res.setResCode(300);
            res.setResMsg("소메뉴 조회 실패");
        }

        return res;
    }
}
