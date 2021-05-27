package com.example.android.Mapper;


import com.example.android.Dto.Board;
import com.example.android.Dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    ArrayList<Board> getMainNotifyList(); // 메인페이지 뉴스 리스트

    ArrayList<Board> getMyBoardList(String u_id); // 활동내역 (내 게시글 리스트)

    int boardListCnt(char cat_cd); //총 게시글 수

    ArrayList<Board> getAllBoardList(); // 게시물 전체 리스트

    ArrayList<Board> getBoardList(Criteria criteria); // 게시글 리스트 (페이지네이션, 검색)

    ArrayList<Board> getBoardListSearchTitle(Criteria criteria); // 제목 검색

    ArrayList<Board> getBoardListSearchWriter(Criteria criteria); // 작성자 검색

    Board getBoard(int b_no); // 글 조회

    void insertBoard(Board board); // 글 작성

    void updateBoard(Board board); // 글 수정

    void deleteBoard(int b_no); // 글 삭제

    void increaseVisit(int b_no); // 글 조회수 증가
}
