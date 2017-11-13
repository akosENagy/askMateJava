package com.cybersoft.askmate.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@NamedQuery(
        name = "Question.getAll",
        query = "SELECT q FROM Question q"
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
}
