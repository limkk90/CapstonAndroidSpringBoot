package com.example.android.Mapper;

import com.example.android.Dto.Question;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface QuestionMapper {
    ArrayList<Question> getQuestionList(); // 문의 리스트

    Question getQuestion(LocalDateTime q_dtt); // 문의 조회

    ArrayList<Question> getMyQuestionList(String u_id); // 내 문의 리스트

    void insertQuestion(Question question); // 문의하기

    void updateQuestion(Question question); // 문의수정
}
