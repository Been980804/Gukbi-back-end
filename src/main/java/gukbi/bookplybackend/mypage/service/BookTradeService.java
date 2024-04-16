package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface BookTradeService {

    ResponseDTO getTradeList(String mem_no);
    ResponseDTO deleteTrade(Map<String, Object> reqBody);
}
