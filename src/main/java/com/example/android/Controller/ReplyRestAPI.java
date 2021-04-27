package com.example.android.Controller;

import com.example.android.Dto.Board;
import com.example.android.Dto.Reply;
import com.example.android.Service.ReplyService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class ReplyRestAPI {

    @Autowired(required = false)
    private ReplyService replyService;

    // 내 댓글 목록
    @GetMapping("/{u_id}/replylist")
    public List<Reply> myReplylist(@PathVariable String u_id) {
        log.info("[GET ReplyAPI (/{u_id}/replylist)] USER_ID: " + u_id);
        return replyService.getMyReplyList(u_id);
    }

    // 댓글 작성
    @PostMapping("/reply")
    public void insertReply(@RequestBody requestReply requestReply) {
        log.info("[POST ReplyAPI (/reply)] REQUEST_REPLY : " + requestReply);

        Board board = new Board();
        board.setCat_cd(requestReply.getCat_cd());
        board.setB_dtt(requestReply.getB_dtt());

        Reply reply = new Reply();
        reply.setR_content(requestReply.getR_content());
        reply.setR_writer(requestReply.getR_writer());

        replyService.insertReply(board, reply);
    }

    // 댓글 수정
    @PatchMapping("/reply/update")
    public void updateReply(@RequestBody Reply reply) {
        log.info("[PATCH ReplyAPI (/reply)] REPLY : " + reply);
        replyService.updateReply(reply);
    }

    // 댓글 삭제
    @DeleteMapping("/reply/remove")
    public void deleteReply(@RequestBody Reply reply) {
        log.info("[DELETE ReplyAPI (/reply)] R_DTT : " + reply.getR_dtt());
        replyService.deleteByRdtt(reply.getR_dtt());
    }
}

@Data
class requestReply {
    private char cat_cd;
    private LocalDateTime b_dtt;
    private String r_content;
    private String r_writer;
}