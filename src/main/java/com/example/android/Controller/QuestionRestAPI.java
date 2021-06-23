package com.example.android.Controller;


import com.example.android.Dto.Question;
import com.example.android.Service.AnswerService;
import com.example.android.Service.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuestionRestAPI {
    @Autowired(required = false)
    private QuestionService questionService;

    @Autowired(required = false)
    private AnswerService answerService;

    // 문의 목록
    @GetMapping("/questions")
    public ArrayList<Question> getQuestionList() {
        log.info("[GET QuestionAPI (/questions)]");

        return questionService.getQuestionList();
    }

    // 내 문의 목록
    @GetMapping("/{u_id}/my-question")
    public ArrayList<Question> getMyQuestionList(@PathVariable String u_id) {
        log.info("[GET QuestionAPI (/{u_id}/my-question)] USER_ID: " + u_id);

        return questionService.getMyQuestionList(u_id);
    }

    // 문의 조회
    @GetMapping("/question/go/{q_dtt}")
    public Map<String, Object> getQuestion(@PathVariable("q_dtt") String q_dtt) {
        log.info("[GET QuestionAPI (/question)] Q_DTT: " + q_dtt);
        LocalDateTime t = LocalDateTime.parse(q_dtt);

        Map<String, Object> result = new HashMap<>();

        Question question = questionService.getQuestion(t);
        question.setQ_cd(t);

        result.put("question", question);
        try{
            result.put("answer", answerService.getAnswer(question.getQ_cd().toString()));
        } catch (NullPointerException e) {
            result.put("answer", null);
        }

       return result;
    }

    // 문의하기
    @PostMapping("/question")
    public void insertQuestion(@RequestBody Question question) {
        log.info("[POST QuestionAPI (/question)] QUESTION: " + question);
        questionService.insertQuestion(question);
    }

    // 문의수정
    @PatchMapping("/question")
    public void updateQuestion(@RequestBody Question question) {
        log.info("[PATCH QuestionAPI (/question)] QUESTION: " + question);
        questionService.updateQuestion(question);
    }
}