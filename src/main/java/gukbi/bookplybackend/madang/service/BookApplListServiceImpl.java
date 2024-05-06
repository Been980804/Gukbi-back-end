package gukbi.bookplybackend.madang.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.dao.MadangMapper;

@Service
public class BookApplListServiceImpl implements BookApplListService {

    @Autowired
    MadangMapper madangMapper;

    @Override
    @Transactional
    public ResponseDTO hopeBookListCnt(Map<String, Object> reqBody) {
        ResponseDTO res = new ResponseDTO();

        int totalCnt = madangMapper.hopeBookListCnt(reqBody);

        if (totalCnt > 0) {
            res.setResCode(200);
            res.setResMsg("도서신청 게시글 개수 조회 성공");
            res.setData("totalCnt", totalCnt);
        } else {
            res.setResCode(300);
            res.setResMsg("도서신청 게시글 개수 조회 실패");
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseDTO hopeBookList(Map<String, Object> pageMap) {
        ResponseDTO res = new ResponseDTO();

        List<Map<String,Object>> hopeBookList = madangMapper.hopeBookList(pageMap);

        if (hopeBookList != null) {
            res.setResCode(200);
            res.setResMsg("도서신청 게시글 조회 성공");
            res.setData("hopeBookList", hopeBookList);
        } else {
            res.setResCode(300);
            res.setResMsg("도서신청 게시글 조회 실패");
        }
        return res;
    }
}
