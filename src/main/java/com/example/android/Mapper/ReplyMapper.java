package com.example.android.Mapper;

import com.example.android.Dto.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReplyMapper {
    List<Reply> getReplyList(String b_dtt); // 게시물 댓글 목록

    List<Reply> getMyReplyList(String u_id); // 내 댓글 목록

    void insertReply(Reply reply); //댓글 입력

    void updateReply(Reply reply); // 댓글 수정

    void deleteReply(LocalDateTime r_dtt); // 댓글 삭제
}
