package com.example.android.Controller;


import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import com.example.android.Dto.Reply;
import com.example.android.Service.BoardService;
import com.example.android.Service.Pagination;
import com.example.android.Service.ReplyService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//
//    @Autowired
//    private NaverApiService naverApiService;

//    // 메인페이지 뉴스목록 5개
//    @GetMapping("/main/news")
//    public String news(@RequestBody HashMap<String, Integer> start) {
//        log.info("[GET BoardAPI (/main/news)] START : " + start.get("start"));
//        return naverApiService.getNaverNews(start.get("start"));
//    }

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
    public ResponseEntity<?> getBoardList(Criteria criteria){
        log.info("[GET BoardAPI (/board/list)] CRITERIA : " + criteria);

        List<Board> board = null;
        Map<String, Object> result = new HashMap<String, Object>();

        Pagination pagination = new Pagination(boardService.boardListCnt(criteria.getCat_cd()), criteria.getPage(), 10);

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

        result.put("pagination", pagination);
        result.put("boardList", board);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 글 조회
    @Transactional
    @PostMapping("/gboard")
    public Map<String, Object> getBoard(@RequestBody boardInfo boardInfo){
        log.info("[POST BoardAPI (/gboard)] CRITERIA : " + boardInfo.getCriteria());
        log.info("[POST BoardAPI (/gboard)] B_DTT : " + boardInfo.getB_dtt());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", boardInfo.getCriteria());

        Board board = boardService.getBoard(boardInfo.getB_dtt());
        result.put("board", board);

        List<Reply> reply = replyService.getReplyList(board.getCat_cd(), board.getB_dtt());
        log.info(reply);
        result.put("replyList", reply);

        return result;
    }

    // 글 작성
    @PostMapping("/board/make")
    public void registerPost(@RequestBody Board board){
        log.info("[POST BoardAPI (/board)] BOARD : " + board);

        boardService.register(board);
    }

    // 글 수정
    @PutMapping("/board/update")
    public void modify(@RequestBody Board board){
        log.info("[PUT BoardAPI (/board)] BOARD : " + board);

        boardService.updateBoard(board);
    }

    // 글 삭제
    @Transactional
    @DeleteMapping("/board/remove")
    public void remove(@RequestBody Board board){
        log.info("[DELETE BoardAPI (/board)] BOARD : " + board);

        String bno = board.getCat_cd() + board.getB_dtt().format(DateTimeFormatter.ofPattern("yyMMddHHmmss.SSSSSS"));
        replyService.deleteByBno(board.getCat_cd(), board.getB_dtt());
        //        fileService.deleteFile(bno);
        boardService.deleteBoard(board.getB_dtt());
    }

}

@Data
class boardInfo{
    private Criteria criteria;
    private LocalDateTime b_dtt;
}