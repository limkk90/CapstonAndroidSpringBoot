package com.example.android.Mapper;

import com.example.android.Dto.Answer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerMapper {
    // 답변 조회
    Answer getAnswer(String q_cd);

    // 답변 등록
    void insertAnswer(Answer answer);

    // 답변 수정
    void updateAnswer(Answer answer);

    // 답변 삭제
    void removeAnswer(String q_cd);
}
