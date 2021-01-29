package com.nowcoder.service;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public int addQuestion(Question question){
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        //敏感词过滤,HTML过滤
        question.setContent(HtmlUtils.htmlEscape(question.getTitle()));
        return questionDAO.addQuestion(question)>0?question.getId():0;
    }

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }
}
