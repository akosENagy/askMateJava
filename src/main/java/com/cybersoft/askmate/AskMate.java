package com.cybersoft.askmate;

import com.cybersoft.askmate.controller.Controller;

import static spark.Spark.*;


public class AskMate {

    private Controller controller;

    public static void main(String[] args) {
        AskMate app = new AskMate();
        app.start();
    }

    private void start() {
        // server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        this.controller = new Controller();

        // routes
        get("/", controller::renderQuestions);
        get("/register", controller::renderRegsiterForm);
        get("/questions/:id", controller::renderSingleQuestion);
        post("/submit-new-question", controller::submitQuestion);
        post("/questions/:id/answer", controller::submitAnswer);
    }
}
