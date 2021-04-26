package com.example.android.Service;


import com.example.android.Dto.Board;
import com.example.android.Dto.Reply;
import com.example.android.Mapper.ReplyMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Log4j2
public class ReplyService {

    @Autowired(required = false)
    private ReplyMapper replyMapper;

    private String getBno(char cat_cd, LocalDateTime b_dtt) {
        return cat_cd + b_dtt.format(DateTimeFormatter.ofPattern("yyMMddHHmmss.SSSSSS"));
    }

    private List<Reply> setDT(List<Reply> list) {
        list.forEach(reply -> {
            reply.setDate(reply.getR_dtt());
            reply.setTime(reply.getR_dtt());
        });

        return list;
    }

    // 게시물 댓글 리스트
    public List<Reply> getReplyList(char cat_cd, LocalDateTime b_dtt) {
        String b_no = getBno(cat_cd, b_dtt);

        return setDT(replyMapper.getReplyList(b_no));
    }

    // 내 댓글 리스트
    public List<Reply> getMyReplyList(String u_id) {

        return setDT(replyMapper.getMyReplyList(u_id));
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
    public void deleteByRdtt(LocalDateTime r_dtt) {
       replyMapper.deleteByRdtt(r_dtt);
    }

    public void deleteByBno(char cat_cd, LocalDateTime b_dtt) {
        String bno = getBno(cat_cd, b_dtt);
        replyMapper.deleteByBno(bno);
    }
}