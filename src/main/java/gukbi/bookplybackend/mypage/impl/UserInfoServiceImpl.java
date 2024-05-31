package gukbi.bookplybackend.mypage.impl;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gukbi.bookplybackend.common.dto.ResponseDTO;
import gukbi.bookplybackend.mypage.dao.MyPageMapper;
import gukbi.bookplybackend.mypage.service.UserInfoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

  private final MyPageMapper myPageMapper;

  @Autowired
  ObjectMapper objectMapper;

  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String gClientId;

  @Value("${spring.security.oauth2.client.registration.google.client-secret}")
  private String gClientSecret;

  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  private String kClientId;

  @Override
  @Transactional
  public ResponseDTO getIdDuplicate(String mem_id) { // 아이디 중복체크
    ResponseDTO res = new ResponseDTO();

    int count = myPageMapper.getIdDuplicate(mem_id);
    boolean isDuplicate = count > 0;

    res.setResCode(200);
    if (isDuplicate) {
      res.setResMsg("중복된 아이디입니다.");
    } else {
      res.setResMsg("사용 가능한 아이디입니다.");
    }

    res.setData("isDuplicate", isDuplicate);
    return res;
  }

  @Override
  @Transactional
  public ResponseDTO getCurrentPwd(String mem_no) { // 현재비밀번호 조회
    ResponseDTO res = new ResponseDTO();
    String currentPwd = myPageMapper.getCurrentPwd(mem_no);

    res.setResCode(200);
    res.setResMsg("현재 비밀번호 조회");
    res.setData("currentPwd", currentPwd);

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO pwdUpdate(Map<String, String> reqBody) { // 비밀번호 수정
    ResponseDTO res = new ResponseDTO();
    int updateRow = myPageMapper.pwdUpdate(reqBody);

    if (updateRow > 0) {
      res.setResCode(200);
      res.setResMsg("비밀번호 수정 성공");

    } else {
      res.setResCode(300);
      res.setResMsg("비밀번호 수정 실패");
    }

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO accountDelete(Map<String, String> reqBody) { // 회원 탈퇴
    ResponseDTO res = new ResponseDTO();

    // 논리적 삭제 use_flag = 'N'로 update
    int deleteRow = myPageMapper.accountDelete(reqBody);

    if (deleteRow > 0) {
      res.setResCode(200);
      res.setResMsg("회원탈퇴 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("회원탈퇴 실패");
    }

    return res;
  }

  @Override
  @Transactional
  public ResponseDTO updateUserInfo(Map<String, String> reqBody) { // 회원정보 수정
    ResponseDTO res = new ResponseDTO();
    int updateRow = myPageMapper.updateUserInfo(reqBody);

    if (updateRow > 0) {
      res.setResCode(200);
      res.setResMsg("회원정보 수정 성공");
    } else {
      res.setResCode(300);
      res.setResMsg("회원정보 수정 실패");
    }

    return res;
  }

  @Override // google Login 실행
  public ResponseDTO googleLogin() {
    ResponseDTO res = new ResponseDTO();
    String baseUrl = "https://accounts.google.com/o/oauth2/v2/auth";
    String redirectUrl = "http://localhost:3000/mypage/userInfo/googleLogin/callback";

    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
      .queryParam("client_id", gClientId)
      .queryParam("redirect_uri", redirectUrl)
      .queryParam("response_type", "code")
      .queryParam("scope", "email")
      .build(true)
      .toUri();
    
    res.setResCode(200);
    res.setResMsg("구글 로그인 토큰 조회");
    res.setData("google", uri);
    return res;
  }

  @Override
  public ResponseDTO getGoogleToken(String code) {
    ResponseDTO res = new ResponseDTO();
    String baseUrl = "https://oauth2.googleapis.com/token";
    String redirectUrl = "http://localhost:3000/mypage/userInfo/googleLogin/callback";

    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
      .queryParam("code", code)
      .queryParam("client_id", gClientId)
      .queryParam("client_secret", gClientSecret)
      .queryParam("grant_type", "authorization_code")
      .queryParam("redirect_uri", redirectUrl)
      .build(true)
      .toUri();

    String jsonString = WebClient.builder().baseUrl(baseUrl)
      .build()
      .post()
      .uri(uri)
      .retrieve()
      .bodyToMono(String.class)
      .block();

    try {
      JsonNode jsonNode = objectMapper.readTree(jsonString).get("access_token");
      String token = objectMapper.readValue(jsonNode.toString(), String.class);
    
      String result = getGoogleUserInfo(token);

      res.setResCode(200);
      res.setResMsg("도서 책소개 정보 조회");
      res.setData("result", result);
    } catch (Exception e) {
      System.out.println("getGoogleToken error: " + e.getMessage());
      res.setResCode(400);
      res.setResMsg("도서 상세정보 조회에 실패했습니다.");
    }

    return res;
  }

  public String getGoogleUserInfo(String token) {
    String baseUrl = "https://openidconnect.googleapis.com/v1/userinfo";

    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
      .queryParam("access_token", token)
      .build(true)
      .toUri();

    String jsonString = WebClient.builder().baseUrl(baseUrl)
      .build()
      .get()
      .uri(uri)
      .retrieve()
      .bodyToMono(String.class)
      .block();
    System.out.println("jsonString:::::::" + jsonString);
    return jsonString;
  }

  @Override // kakao Login 실행
  public ResponseDTO kakaoLogin() {
    ResponseDTO res = new ResponseDTO();
    String baseUrl = "https://kauth.kakao.com/oauth/authorize";
    String redirectUrl = "http://localhost:3000/mypage/userInfo/kakaoLogin/callback";

    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
      .queryParam("client_id", kClientId)
      .queryParam("redirect_uri", redirectUrl)
      .queryParam("response_type", "code")
      .build(true)
      .toUri();
    
    res.setResCode(200);
    res.setResMsg("카카오 로그인 토큰 조회");
    res.setData("kakao", uri);
    return res;
  }
}
