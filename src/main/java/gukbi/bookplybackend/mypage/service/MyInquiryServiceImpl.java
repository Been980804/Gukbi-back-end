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
public class MyInquiryServiceImpl implements MyInquiryService{
    
    private final MyPageMapper myPageMapper;

    @Override
    @Transactional
    public ResponseDTO getMyInquiryCnt(String mem_no) {
        ResponseDTO res = new ResponseDTO();

        int totalCnt = myPageMapper.getMyInquiryCnt(mem_no);

        if(totalCnt >= 0){
            res.setResCode(200);
            res.setResMsg("개별문의 게시글 수 조회");
            res.setData("totalCnt", totalCnt);
          }else{
            res.setResCode(300);
            res.setResMsg("개별문의 게시글 수 조회 실패");
          }
          return res;
    }
    @Override
    @Transactional
    public ResponseDTO getMyInquiry(Map<String, Object> pageMap) { // 문의내역 조회
        ResponseDTO res = new ResponseDTO();
        
        List<Map<String, String>> inquiryList = myPageMapper.getMyInquiry(pageMap);

        if(null != inquiryList){
            res.setResCode(200);
            res.setResMsg("개별문의내역 조회 성공");
            res.setData("inquiryList", inquiryList);
        }else{
            res.setResCode(300);
            res.setResMsg("개별문의내역 조회 실패");
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseDTO deleteInquiry(Map<String, String> reqBody) { // 문의내역 삭제
        ResponseDTO res = new ResponseDTO();

        int updateRow = myPageMapper.deleteInquiry(reqBody);

        if(updateRow > 0) {
            res.setResCode(200);
            res.setResMsg("개별문의내역 삭제 성공");
        } else{
            res.setResCode(300);
            res.setResMsg("개별문의내역 삭제 실패");
        }

        return res;
    }
    
    @Override
    @Transactional
    public ResponseDTO updateVisibility(Map<String, Integer> reqBody) { // 공개여부 수정
        ResponseDTO res = new ResponseDTO(); 
        int updateRow = myPageMapper.updateVisibilityQna(reqBody);

        if(updateRow > 0){
            res.setResCode(200);
            res.setResMsg("공개여부 수정 성공");
        } else{            
            res.setResCode(300);
            res.setResMsg("공개여부 수정 실패");
        }

        return res;
    }
}
