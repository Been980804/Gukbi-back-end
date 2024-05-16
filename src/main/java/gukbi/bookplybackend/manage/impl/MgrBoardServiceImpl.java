package gukbi.bookplybackend.manage.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.manage.dao.MgrMapper;
import gukbi.bookplybackend.manage.service.MgrBoardService;

@Service
public class MgrBoardServiceImpl implements MgrBoardService {

  @Autowired
  MgrMapper manageMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Override
  @Transactional // 총 공지사항 개수 가져오기
  public ResponseDTO getNotiCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int notiCount = manageMapper.getNotiCount(sqlData);

    if (notiCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 공지사항 개수 조회");
      res.setData("notiCount", notiCount);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 공지사항목록 가져오기
  public ResponseDTO getNotiList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> notiList = manageMapper.getNotiList(pageData);

    if (!notiList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("공지사항 리스트 조회");
      res.setData("notiList", notiList);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 공지사항 상세정보 가져오기
  public ResponseDTO getNotiInfo(String notiNo) {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> notiInfo = manageMapper.getNotiInfo(notiNo);

    if (notiInfo != null) {
      res.setResCode(200);
      res.setResMsg("공지사항 상세정보 조회");
      res.setData("notiInfo", notiInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 상세정보 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 공지사항 정보 삭제
  public ResponseDTO notiDelete(String notiNo) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.notiDelete(notiNo);

    if (result == 1) {
      res.setResCode(200);
      res.setResMsg("공지사항 정보 삭제");
      res.setData("noti", result);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 정보 삭제에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 총 문의사항 개수 가져오기
  public ResponseDTO getInquiryCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int inquiryCount = manageMapper.getInquiryCount(sqlData);

    if (inquiryCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 공지사항 개수 조회");
      res.setData("inquiryCount", inquiryCount);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 문의사항목록 가져오기
  public ResponseDTO getInquiryList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> inquiryList = manageMapper.getInquiryList(pageData);

    if (!inquiryList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("공지사항 리스트 조회");
      res.setData("inquiryList", inquiryList);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 문의사항 상세정보 가져오기
  public ResponseDTO getInquiryInfo(String inquiryNo) {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> inquiryInfo = manageMapper.getInquiryInfo(inquiryNo);

    if (inquiryInfo != null) {
      res.setResCode(200);
      res.setResMsg("문의사항 상세정보 조회");
      res.setData("inquiryInfo", inquiryInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("문의사항 상세정보 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 문의사항 답장 등록
  public ResponseDTO registAnswer(Map<String, Object> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.registAnswer(sqlData);

    if (result == 1) {
      res.setResCode(200);
      res.setResMsg("문의사항 답장 등록");
      res.setData("answer", result);
    } else {
      res.setResCode(300);
      res.setResMsg("문의사항 답장 등록에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 총 도서거래 개수 가져오기
  public ResponseDTO getTradeCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int tradeCount = manageMapper.getTradeCount(sqlData);

    if (tradeCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 공지사항 개수 조회");
      res.setData("tradeCount", tradeCount);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 도서거래목록 가져오기
  public ResponseDTO getTradeList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> tradeList = manageMapper.getTradeList(pageData);

    if (!tradeList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("공지사항 리스트 조회");
      res.setData("tradeList", tradeList);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 도서거래 상세정보 가져오기
  public ResponseDTO getTradeInfo(String tradeNo) {
    ResponseDTO res = new ResponseDTO();
    Map<String, Object> tradeInfo = manageMapper.getTradeInfo(tradeNo);

    if (tradeInfo != null) {
      res.setResCode(200);
      res.setResMsg("도서 상세정보 조회");
      res.setData("tradeInfo", tradeInfo);
    } else {
      res.setResCode(300);
      res.setResMsg("도서 상세정보 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 총 희망도서 개수 가져오기
  public ResponseDTO getApplCount(Map<String, String> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int applCount = manageMapper.getApplCount(sqlData);

    if (applCount >= 0) {
      res.setResCode(200);
      res.setResMsg("총 공지사항 개수 조회");
      res.setData("applCount", applCount);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 개수 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 현재 페이지 희망도서목록 가져오기
  public ResponseDTO getApplList(Map<String, Object> pageData) {
    ResponseDTO res = new ResponseDTO();
    List<Map<String, Object>> applList = manageMapper.getApplList(pageData);

    if (!applList.isEmpty()) {
      res.setResCode(200);
      res.setResMsg("공지사항 리스트 조회");
      res.setData("applList", applList);
    } else {
      res.setResCode(300);
      res.setResMsg("공지사항 리스트 조회에 실패했습니다.");
    }

    return res;
  }

  @Override
  @Transactional // 희망도서 신청상태 변경
  public ResponseDTO setStatus(Map<String, Object> sqlData) {
    ResponseDTO res = new ResponseDTO();
    int result = manageMapper.setStatus(sqlData);

    if (result == 1) {
      res.setResCode(200);
      res.setResMsg("희망도서 신청상태 변경");
      res.setData("status", result);
    } else {
      res.setResCode(300);
      res.setResMsg("희망도서 신청상태 변경에 실패했습니다.");
    }

    return res;
  }
}
