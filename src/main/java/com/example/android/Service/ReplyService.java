package com.example.android.Service;


import com.example.android.Dto.Board;
import com.example.android.Dto.Reply;
import com.example.android.Mapper.ReplyMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ReplyService {

    @Autowired(required = false)
    private ReplyMapper replyMapper;

    private List<Reply> setDT(List<Reply> list) {
        list.forEach(reply -> {
            reply.setDate(reply.getR_dtt());
            reply.setTime(reply.getR_dtt());
        });

        return list;
    }

    // 게시물 댓글 리스트
    public List<Reply> getReplyList(int b_no) {
        return setDT(replyMapper.getReplyList(b_no));
    }

    // 내 댓글 리스트
    public List<Reply> getMyReplyList(String u_id) {

        return setDT(replyMapper.getMyReplyList(u_id));
    }

    // 댓글 작성
    public void insertReply(Reply reply) {
        replyMapper.insertReply(reply);
    }

    // 댓글 수정
    public void updateReply(Reply reply) {
        replyMapper.updateReply(reply);
    }

    // 댓글 삭제
    public void deleteByRdtt(LocalDateTime r_dtt) {
        replyMapper.deleteByRdtt(r_dtt);
    }

    public void deleteByBno(int b_no) {
        replyMapper.deleteByBno(b_no);
    }

}