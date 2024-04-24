package gukbi.bookplybackend.madang.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.dao.MadangMapper;

@Service
public class BookPlyListServiceImpl implements BookPlyListService{
    
    @Autowired
    MadangMapper madangMapper;

    @Override
    @Transactional
    public ResponseDTO getBookPlyListCnt(Map<String, String> reqBody) { // 북플리 총 개수 조회
        ResponseDTO res = new ResponseDTO();

        int totalCnt = madangMapper.getBookPlyListCnt(reqBody);

        if(totalCnt > 0){
            res.setResCode(200);
            res.setResMsg("북플리 목록 개수 조회 성공");
            res.setData("totalCnt", totalCnt);
        } else{
            res.setResCode(300);
            res.setResMsg("북플리 목록 개수 조회 실패");
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseDTO getBookPlyList(Map<String, Object> pageMap) { // 북플리 조회
        ResponseDTO res = new ResponseDTO();

        List<Map<String, Object>> bookPlyList = madangMapper.getBookPlyList(pageMap);

        if(bookPlyList != null){
            res.setResCode(200);
            res.setResMsg("북플리 조회 성공");
            res.setData("bookPlyList", bookPlyList);
        }else{
            res.setResCode(300);
            res.setResMsg("북플리 조회 실패");
        }
        return res;
    }
}
