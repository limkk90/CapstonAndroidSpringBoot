package com.example.android.Controller;

import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import com.example.android.Dto.Reply;
import com.example.android.Service.BoardService;
import com.example.android.Service.Pagination;
import com.example.android.Service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    // 글 리스트 페이지네이션, 검색
    @GetMapping("/board/list")
    public Map<String, Object> getBoardList(@RequestBody Criteria criteria,
                                            @RequestBody HashMap<String, Integer> page){
        log.info("[GET BoardAPI (/board/list)] CRITERIA : " + criteria);
        log.info("[GET BoardAPI (/board/list)] PAGE : " + page.get("page"));

        List<Board> board = null;
        Map<String, Object> result = new HashMap<String, Object>();
        Pagination pagination = new Pagination(boardService.boardListCnt(), page.get("page"), 10);

        criteria.setPage(pagination.getPage());
        if(criteria.getSer() == 'T'){
            board = boardService.getBoardListSearchTitle(criteria);
        }
        if(criteria.getSer() == 'W'){
            board = boardService.getBoardListSearchWriter(criteria);
        }
        if(criteria.getSer() == 0) {
            board = boardService.getBoardList(criteria);
        }

        result.put("pagination", pagination);
        result.put("boardList", board);

        return result;
    }

    // 글 조회
    @Transactional
    @GetMapping("/board/read")
    public Map<String, Object> getBoard(@RequestBody Board board, Criteria criteria){
        log.info("[GET BoardAPI (/board)] CRITERIA : " + criteria);
        log.info("[GET BoardAPI (/board)] B_DTT : " + board.getB_dtt());

        increaseVisit(board.getB_dtt());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", criteria);

        Board boardList = boardService.getBoard(board.getB_dtt());
        result.put("boardList", boardList);

        List<Reply> reply = replyService.getReplyList(boardList.getCat_cd(), boardList.getB_dtt());
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
        // 파일 존재하는지 체크 후 pk변경 필요
        boardService.updateBoard(board);
    }

    // 글 삭제
    @DeleteMapping("/remove")
    public void remove(@RequestBody Board board){
        log.info("[DELETE BoardAPI (/board)] BOARD : " + board);
        boardService.deleteBoard(board.getB_dtt());
    }

    // 글 조회수 증가
    private void increaseVisit(LocalDateTime B_dtt) {
        boardService.increaseVisit(B_dtt);
    }
}