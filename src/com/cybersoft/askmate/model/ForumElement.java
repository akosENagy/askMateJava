package com.cybersoft.askmate.model;

import java.sql.Timestamp;

public abstract class ForumElement {

    protected int id;
    private String title;
    private String content;
    private Timestamp timeOfPosting;

    public ForumElement(String title, String content) {
        this.title = title;
        this.content = content;
        this.timeOfPosting = new Timestamp(System.currentTimeMillis());
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
}
