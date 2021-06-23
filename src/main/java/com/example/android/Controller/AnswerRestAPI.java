package com.example.android.Controller;


import com.example.android.Dto.Answer;
import com.example.android.Service.AnswerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class AnswerRestAPI {

    @Autowired(required = false)
    private AnswerService answerService;

    // 답변 조회
    @GetMapping("/answer")
    public Answer getAnswer(@RequestBody Answer answer) {
        log.info("[GET : AnswerAPI (/answer)] Q_CD : " + answer.getQ_cd());
        return answerService.getAnswer(answer.getQ_cd());
    }

    // 답변 등록
    @PostMapping("/answer")
    public void insertAnswer(@RequestBody Answer answer) {
        log.info("[POST : AnswerAPI (/answer)] Answer : " + answer);
        answerService.insertAnswer(answer);
    }

    // 답변 수정
    @PutMapping("/answer")
    public void updateAnswer(@RequestBody Answer answer) {
        log.info("[PUT : AnswerAPI (/answer)] Answer : " + answer);
        answerService.updateAnswer(answer);
    }

    // 답변 삭제
    @DeleteMapping("/answer")
    public void removeAnswer(@RequestBody Answer answer) {
        log.info("[DELETE : AnswerAPI (/answer)] Q_CD : " + answer.getQ_cd());
        answerService.removeAnswer(answer.getQ_cd());
    }
}
