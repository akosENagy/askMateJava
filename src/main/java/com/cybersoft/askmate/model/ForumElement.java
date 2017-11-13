package com.cybersoft.askmate.model;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public abstract class ForumElement {

    protected int id;
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp timestamp;

    public ForumElement(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ForumElement() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
