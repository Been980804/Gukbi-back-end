package gukbi.bookplybackend.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.dao.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookApplServiceImpl implements BookApplService{
    
    private final MyPageMapper myPageMapper;

    @Override
    @Transactional
    public ResponseDTO getHopeBookCnt(String mem_no) {
        ResponseDTO res = new ResponseDTO();

        int totalCnt = myPageMapper.getHopeBookCnt(mem_no);

        if(totalCnt >= 0){
            res.setResCode(200);
            res.setResMsg("희망 도서 수 조회");
            res.setData("totalCnt", totalCnt);
            System.out.println(totalCnt);
          }else{
            res.setResCode(300);
            res.setResMsg("희망 도서 수 조회 실패");
          }
          return res;
    }

    @Override
    @Transactional
    public ResponseDTO getHopeBookList(Map<String, Object> pageMap) { // 희망도서 조회
       ResponseDTO res = new ResponseDTO();

       List<Map<String, String>> hopeBookList = myPageMapper.getHopeBookList(pageMap);

       res.setResCode(200);
       res.setResMsg("희망 도서 리스트 조회");
       res.setData("hopeBookList",hopeBookList);

       return res;
    }

    @Override
    @Transactional
    public ResponseDTO cancelHopeBook(Map<String, String> reqBody) { // 희망도서 취소
        ResponseDTO res = new ResponseDTO();

        int updateRow = myPageMapper.cancelHopeBook(reqBody);

        if(updateRow > 0){
            res.setResCode(200);
            res.setResMsg("희망 도서취소 성공");
        }else{
            res.setResCode(300);
            res.setResMsg("희망 도서취소 실패");
        }

        return res;
    }
}
