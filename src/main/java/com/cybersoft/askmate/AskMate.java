package com.cybersoft.askmate;

import com.cybersoft.askmate.controller.Controller;
import com.cybersoft.askmate.model.Question;

import static spark.Spark.*;


public class AskMate {

    public static void main(String[] args) {

        // server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // data business
        populateQuestionDao();

        // routes
        get("/", Controller::renderQuestions);
        post("/submit-new-question", Controller::submitQuestion);
    }

    private static void populateQuestionDao() {
        new Question("Why is it that a snorlax is blocking my way?", "I really can't get past this fat fuck. I know i need a pokéflute but where do I find one?");
        new Question("Orly?", "O R L Y ?");
    }
}
