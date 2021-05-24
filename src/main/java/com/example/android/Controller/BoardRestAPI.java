package com.example.android.Controller;


import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import com.example.android.Dto.Reply;
import com.example.android.Service.BoardService;
import com.example.android.Service.NaverApiService;
import com.example.android.Service.ReplyService;
import com.example.android.Service.UserService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class BoardRestAPI {
    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

//    @Autowired
//    private FileService fileService;

    @Autowired
    private NaverApiService naverApiService;



    // 메인페이지 뉴스목록 5개
    @GetMapping("/main/news")
    public String news() {
        log.info("[GET BoardAPI (/main/news)]");
        return naverApiService.getNaverNews();
    }

    // 메인페이지 공지사항목록 5개
    @GetMapping("/main/notify")
    public List<Board> MainNotifyList() {
        log.info("[GET BoardAPI (/main/notify)]");
        return boardService.getMainNotifyList();
    }

    // 내 게시글 리스트
    @GetMapping("/{u_id}/boardlist")
    public List<Board> myBoardList(@PathVariable String u_id) {
        log.info("[GET BoardAPI (/{u_id}/boardlist)] USER_ID: " + u_id);
        return boardService.getMyBoardList(u_id);
    }

    // 게시글 전체 리스트
    @GetMapping("/allboardlist")
    public ArrayList<Board> getBoardList() {
        return boardService.getAllBoardList();
    }

    // 글 리스트 페이지네이션, 검색
    @Transactional
    @GetMapping("/boardlist")
    public ArrayList<Board> getBoardList(Criteria criteria){

        ArrayList<Board> board = null;

        switch (criteria.getSer()) {
            case'T':
                board = boardService.getBoardListSearchTitle(criteria);
                break;
            case'W':
                board = boardService.getBoardListSearchWriter(criteria);
                break;
            default:
                board = boardService.getBoardList(criteria);
        }

        return board;
    }

    // 글 조회
    @Transactional
    @PostMapping("/board/{b_no}")
    public Map<String, Object> getBoard(@PathVariable("b_no") int b_no){
        log.info("[GET BoardAPI (/board/{b_no})] B_NO : " + b_no);

        boardService.increaseVisit(b_no);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("board", boardService.getBoard(b_no));
        result.put("replyList", replyService.getReplyList(b_no));

        return result;
    }


    // 글 작성
    @PostMapping("/board/make")
    public String registerPost(@RequestBody Board board){
        log.info("[POST BoardAPI (/board)] BOARD : " + board);

        boardService.register(board);
//        fileService.uploadFile(board.getFile(), board.getB_no());
        return "성공";
    }

    // 글 수정
    @PutMapping("/board/update")
    public void modify(@RequestBody Board board){
        log.info("[PUT BoardAPI (/board)] BOARD : " + board);

        boardService.updateBoard(board);
    }

    // 글 삭제
    @Transactional
    @PostMapping("/board/remove")
    public void remove(@RequestBody Board board){
        log.info("[DELETE BoardAPI (/board)] BOARD : " + board);

//        replyService.deleteByBno(board.getB_no());
//        fileService.deleteFile(board.getB_no());
        boardService.deleteBoard(board.getB_no());
    }

}