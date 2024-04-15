package gukbi.bookplybackend.mypage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface UserInfoService {
    
    //아이디 중복 체크
    ResponseDTO getIdDuplicate(String mem_id);
    // 현재비밀번호 조회
    ResponseDTO getCurrentPwd(String mem_no);
    // 비밀번호 수정
    ResponseDTO pwdUpdate(Map<String, String> reqBody);
    // 회원탈퇴
    ResponseDTO accountDelete(Map<String,String> reqBody);
    // 회원정보 수정
    ResponseDTO updateUserInfo(Map<String, String> reqBody);
}
