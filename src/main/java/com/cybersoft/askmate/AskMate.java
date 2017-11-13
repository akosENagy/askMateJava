package com.cybersoft.askmate;

import com.cybersoft.askmate.controller.Controller;

import static spark.Spark.*;


public class AskMate {

    public static void main(String[] args) {

        // server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);


        // routes
        get("/", Controller::renderQuestions);
        get("/questions/:id", Controller::renderSingleQuestion);
        post("/submit-new-question", Controller::submitQuestion);
        post("/questions/:id/answer", Controller::submitAnswer);
    }
}
