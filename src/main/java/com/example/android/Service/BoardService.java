package com.example.android.Service;

import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import com.example.android.Mapper.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class BoardService {

    @Autowired(required = false)
    private BoardMapper boardMapper;

    // 메인페이지 공지사항 리스트
    public List<Board> getMainNotifyList() {
        return boardMapper.getMainNotifyList();
    }

    // 활동내역 (게시글 리스트)
    public List<Board> getMyBoardList(String u_id) {
        return boardMapper.getMyBoardList(u_id);
    }

    // 글 총 개수
    public int boardListCnt(){
        return boardMapper.boardListCnt();
    }

    // 게시물 리스트 페이지네이션, 검색
    public List<Board> getBoardList(Criteria criteria) {
        List<Board> list = boardMapper.getBoardList(criteria);

        list.forEach(board -> {
            board.setDate(board.getB_dtt());
            board.setTime(board.getB_dtt());
        });

        return list;
    }

    // 제목 검색
    public List<Board> getBoardListSearchTitle(Criteria criteria) {
        return boardMapper.getBoardListSearchTitle(criteria);
    }

    // 작성자 검색
    public List<Board> getBoardListSearchWriter(Criteria criteria) {
        return boardMapper.getBoardListSearchWriter(criteria);
    }

    // 글 조회
    public Board getBoard(LocalDateTime b_dtt) {
        Board board = boardMapper.getBoard(b_dtt);

        board.setDate(board.getB_dtt());
        board.setTime(board.getB_dtt());

        return board;
    }

    // 글 작성
    public void register(Board board) {
        boardMapper.insertBoard(board);
    }

    // 글 수정
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    // 글 삭제
    public void deleteBoard(LocalDateTime b_dtt) {
        boardMapper.deleteBoard(b_dtt);
    }

    // 글 조회수 증가
    public void increaseVisit(LocalDateTime b_dtt) {
        boardMapper.increaseVisit(b_dtt);
    }
}
