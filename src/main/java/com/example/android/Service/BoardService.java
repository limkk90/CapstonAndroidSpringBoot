package com.example.android.Service;

import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import com.example.android.Mapper.BoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BoardService {

    @Autowired(required = false)
    private BoardMapper boardMapper;

    // 메인페이지 공지사항 리스트
    public ArrayList<Board> getMainNotifyList() {
        return boardMapper.getMainNotifyList();
    }

    // 활동내역 (게시글 리스트)
    public List<Board> getMyBoardList(String u_id) {
        ArrayList<Board> list = boardMapper.getMyBoardList(u_id);

        list.forEach(board -> {
            board.setDate(board.getB_dtt());
            board.setTime(board.getB_dtt());
        });

        return list;
    }

    // 글 총 개수
    public int boardListCnt(char cat_cd){
        return boardMapper.boardListCnt(cat_cd);
    }

    // 게시물 전체 리스트
    public ArrayList<Board> getAllBoardList(){
        return boardMapper.getAllBoardList();
    };

    // 게시물 리스트 페이지네이션, 검색
    public ArrayList<Board> getBoardList(Criteria criteria) {
        ArrayList<Board> list = boardMapper.getBoardList(criteria);

        list.forEach(board -> {
            board.setDate(board.getB_dtt());
            board.setTime(board.getB_dtt());
        });

        return list;
    }

    // 제목 검색
    public ArrayList<Board> getBoardListSearchTitle(Criteria criteria) {
        return boardMapper.getBoardListSearchTitle(criteria);
    }

    // 작성자 검색
    public ArrayList<Board> getBoardListSearchWriter(Criteria criteria) {
        return boardMapper.getBoardListSearchWriter(criteria);
    }

    // 글 조회
    public Board getBoard(int b_no) {
        Board board = boardMapper.getBoard(b_no);

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
    public void deleteBoard(int b_no) {
        boardMapper.deleteBoard(b_no);
    }

    // 글 조회수 증가
    public void increaseVisit(int b_no) {
        boardMapper.increaseVisit(b_no);
    }

}
