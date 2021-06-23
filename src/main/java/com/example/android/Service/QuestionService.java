package com.example.android.Service;


import com.example.android.Dto.Question;
import com.example.android.Mapper.AnswerMapper;
import com.example.android.Mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private AnswerMapper answerMapper;

    // 문의 리스트
    public ArrayList<Question> getQuestionList() {
        ArrayList<Question> result = questionMapper.getQuestionList();

        result.forEach(question -> {
            question.setDate(question.getQ_dtt());
            question.setTime(question.getQ_dtt());
            question.setQ_cd(question.getQ_dtt());
        });

        return result;
    }

    // 문의 조회
    public Question getQuestion(LocalDateTime q_dtt) {
        Question result = questionMapper.getQuestion(q_dtt);

        result.setDate(result.getQ_dtt());
        result.setTime(result.getQ_dtt());
        result.setQ_cd(result.getQ_dtt());

        return result;
    }

    // 내 문의 리스트
    public ArrayList<Question> getMyQuestionList(String u_id) {
        ArrayList<Question> list = questionMapper.getMyQuestionList(u_id);

        list.forEach(question -> {
            question.setDate(question.getQ_dtt());
            question.setTime(question.getQ_dtt());
            question.setQ_cd(question.getQ_dtt());
        });

        return list;
    }

    // 문의하기
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    // 문의수정
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

}
