package gukbi.bookplybackend.madang.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.madang.dao.MadangMapper;
import gukbi.bookplybackend.madang.service.InquiryListService;

@Service
public class InquiryListServiceImpl implements InquiryListService {

  @Autowired
  MadangMapper madangMapper;

  @Override
  @Transactional
  public ResponseDTO getFaq() { // 자주하는 질문 조회
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> faqList = madangMapper.getFaq();

    if (faqList != null) {
      res.setResCode(200);
      res.setResMsg("자주하는 질문 불러오기 성공");
      res.setData("faqList", faqList);
    } else {
      res.setResCode(300);
      res.setResMsg("자주하는 질문 불러오기 실패");
    }
    return res;
  }

  @Override
  @Transactional
  public ResponseDTO getQnaCnt(Map<String, Object> reqBody) { // 문의사항 개수 조회
    ResponseDTO res = new ResponseDTO();
    int totalCnt = madangMapper.getQnaCnt(reqBody);

    if (totalCnt > 0) {
      res.setResCode(200);
      res.setResMsg("문의내역 게시글 수 조회 성공");
      res.setData("totalCnt", totalCnt);
    } else {
      res.setResCode(300);
      res.setResMsg("문의내역 게시글 수 조회 실패");
    }
    return res;
  }

  @Override
  @Transactional
  public ResponseDTO getQna(Map<String, Object> pageMap) { // 문의사항 조회
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> qnaList = madangMapper.getQna(pageMap);

    if (qnaList != null) {
      res.setResCode(200);
      res.setResMsg("문의내역 조회 성공");
      res.setData("qnaList", qnaList);
    } else {
      res.setResCode(300);
      res.setResMsg("문의내역 조회 실패");
    }
    return res;
  }

  @Override
  @Transactional
  public ResponseDTO inquiryReg(Map<String, Object> reqBody) { // 문의사항 등록
    ResponseDTO res = new ResponseDTO();
    int getMaxInquiryNo = madangMapper.getMaxInquiryNo();

    reqBody.put("qna_no", getMaxInquiryNo);

    int insertRow = madangMapper.inquiryReg(reqBody);

    if (insertRow > 0) {
      res.setResCode(200);
      res.setResMsg("문의사항 등록 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("문의사항 등록 실패");
    }

    return res;
  }
}
