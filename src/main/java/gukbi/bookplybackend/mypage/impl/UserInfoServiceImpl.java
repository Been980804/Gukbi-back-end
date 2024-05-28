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
  private String clientId;

  @Value("${spring.security.oauth2.client.registration.google.client-secret}")
  private String clientSecret;

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

  @Override //google Login 실행
  public ResponseDTO googleLogin() {
    ResponseDTO res = new ResponseDTO();
    String baseUrl = "https://accounts.google.com/o/oauth2/v2/auth";
    String redirectUrl = "http://localhost:3000/mypage/userInfo/googleLogin/callback";

    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
      .queryParam("client_id", clientId)
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
      .queryParam("client_id", clientId)
      .queryParam("client_secret", clientSecret)
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

  //   "access_token": "ya29.a0AXooCgu8GPJV-WmbijrzbfSl80OzTaT4EX7GDgxt2030PWKtBQYQq5ouaX3f0vYAMi_N3kTzyOCr0_bA4zRKFiJfEOCj9x5_-fsJ0wv0kxtLAoSqfngcCDB_Fie0cPyE2M2a2dDDzYfYfBtyveqZmuAt9XTXZahDyAaCgYKAUwSARMSFQHGX2MigMxax1JSHYwXPYh8oNAyLQ0169",
  // "expires_in": 3598,
  // "scope": "openid https://www.googleapis.com/auth/userinfo.email",
  // "token_type": "Bearer",
  // "id_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY3MTk2NzgzNTFhNWZhZWRjMmU3MDI3NGJiZWE2MmRhMmE4YzRhMTIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxMDIzNTczNDE5ODI5LTh2dTM0cWZua3BkNnJxcnU2bXVpYzZncmJoaWVpdDkzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTAyMzU3MzQxOTgyOS04dnUzNHFmbmtwZDZycXJ1Nm11aWM2Z3JiaGllaXQ5My5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwMTQ1MzM3ODM2NTIxMDI0OTMyNSIsImVtYWlsIjoiZGxzZ2h0aHNAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJub3VVdmVidEdRc21fcEp3dVNDX09BIiwiaWF0IjoxNzE2ODgwMjgxLCJleHAiOjE3MTY4ODM4ODF9.YbkVuetA5yMQxE_n6hX_dom_qNvUq6yYBsQRmjKActcMfgc8HQS9zG8PYrRgjojutWavl8gjkSU7x8teiju0RCALZL9FMQCeRePkpAVgQYW0GAhgJHQUC1DMTf7HtlXsneX1Dg3EJRSYGqYAidRzmk_ZT9WTihCk0aC2YEbAJF-leRp4PW1av06r278MTN8L9I3zoKfBG8XzSJ7_3N_ALAGyBgnhsoteWxV2ilQqW4KFC45mJFItbgVqAJV_DTnubErHWA6w6bezIq1bQ0KIB-H5BuSJtSNBbzWVZ81K7ZA1OaE0FNOFG5A6dgjZX2J2Plk-lcjoDa0omeeltqA2UA"

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
}
