package com.example.android.Service;


import com.example.android.Dto.Answer;
import com.example.android.Mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    // 답변 조회
    public Answer getAnswer(String q_cd) {
        return answerMapper.getAnswer(q_cd);
    }

    // 답변 등록
    public void insertAnswer(Answer answer) {
        LocalDateTime localDateTime = LocalDateTime.now();
        answer.setQ_cd(localDateTime.toString());
        answerMapper.insertAnswer(answer);
    }

    // 답변 수정
    public void updateAnswer(Answer answer) {
        answerMapper.updateAnswer(answer);
    }

    // 답변 삭제
    public void removeAnswer(String q_cd) {
        answerMapper.removeAnswer(q_cd);
    }
}
