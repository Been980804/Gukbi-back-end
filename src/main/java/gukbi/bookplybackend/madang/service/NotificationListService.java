package gukbi.bookplybackend.madang.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import gukbi.bookplybackend.common.dto.ResponseDTO;

@Service
public interface NotificationListService {

    // 공지사항 게시글 수 조회
    ResponseDTO notiListCnt(Map<String, Object> reqBody);

    // 공지사항 조회
    ResponseDTO notiList(Map<String, Object> pageMap);

    // 공지사항 조회수 증가
    ResponseDTO updateNotiViews(String noti_no);

    // 공지사항 상세보기
    ResponseDTO notiDetail(String notiNo);
}
