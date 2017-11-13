package com.cybersoft.askmate.model;

public class Answer extends ForumElement {

    private static int idCounter = 1;
    private Question question;

    public Answer(String title, String content, Question question) {
        super(title, content);
        this.id = idCounter++;
        this.question = question;
        this.question.addAnswer(this);
    }

    public Question getQuestion() {
        return question;
    }
}
