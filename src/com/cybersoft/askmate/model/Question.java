package com.cybersoft.askmate.model;

import com.cybersoft.askmate.dao.QuestionDao;

import java.util.ArrayList;
import java.util.List;

public class Question extends ForumElement {

    private static int idCounter = 1;
    private List<Answer> answerList = new ArrayList<>();

    public Question(String title, String content) {
        super(title, content);
        this.id = idCounter++;
        QuestionDao.getInstance().add(this);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void addAnswer(Answer answer) {
        this.answerList.add(answer);
    }
}
