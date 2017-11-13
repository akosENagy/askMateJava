package com.cybersoft.askmate.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@NamedQuery(
        name = "Question.getAll",
        query = "SELECT q FROM Question q ORDER BY timestamp DESC"
)
@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp timestamp;

//    @OneToMany
//    private List<Answer> answerList = new ArrayList<>();

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Question() {

    }

//    public List<Answer> getAnswerList() {
//        return answerList;
//    }


//    public void addAnswer(Answer answer) {
//        this.answerList.add(answer);
//    }


    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
