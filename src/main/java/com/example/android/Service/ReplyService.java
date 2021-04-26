package com.example.android.Service;


import com.example.android.Dto.Board;
import com.example.android.Dto.Reply;
import com.example.android.Mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReplyService {

    @Autowired(required = false)
    private ReplyMapper replyMapper;

    private String getBno(char cat_cd, LocalDateTime b_dtt) {
        return cat_cd + b_dtt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    // 게시물 댓글 리스트
    public List<Reply> getReplyList(char cat_cd, LocalDateTime b_dtt) {
        String b_no = getBno(cat_cd, b_dtt);
        return replyMapper.getReplyList(b_no);
    }

    // 내 댓글 리스트
    public List<Reply> getMyReplyList(String u_id) {
        return replyMapper.getMyReplyList(u_id);
    }
    // 댓글 작성
    public void insertReply(Board board, Reply reply) {
        reply.setB_no(getBno(board.getCat_cd(), board.getB_dtt()));
        replyMapper.insertReply(reply);
    }

    // 댓글 수정
    public void updateReply(Reply reply) {
        replyMapper.updateReply(reply);
    }

    // 댓글 삭제
    public void deleteReply(LocalDateTime r_dtt) {
       replyMapper.deleteReply(r_dtt);
    }
}
