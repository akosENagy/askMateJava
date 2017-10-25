package com.cybersoft.askmate.dao;

import com.cybersoft.askmate.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDao {

    private static QuestionDao instance;
    public static QuestionDao getInstance() {
        if (instance == null) {
            instance = new QuestionDao();
        }
        return instance;
    }

    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void add(Question question) {
        this.questions.add(question);
    }

    public void remove(Question question) {
        this.questions.remove(question);
    }

    public Map<Integer, Question> getQuestionsMap() {
        Map<Integer, Question> questionMap = new HashMap<>();
        for (Question question : questions) {
            questionMap.put(question.getId(), question);
        }
        return questionMap;
    }
}
