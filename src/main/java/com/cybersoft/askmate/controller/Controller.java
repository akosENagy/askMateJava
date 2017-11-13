package com.cybersoft.askmate.controller;

import com.cybersoft.askmate.dao.QuestionDao;
import com.cybersoft.askmate.model.Question;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    public static String renderQuestions(Request req, Response res){
        Map params = new HashMap<>();
        List<Question> questions = QuestionDao.getInstance().getQuestions();
        params.put("questions", questions);
        return renderTemplate(params, "index");
    }

    public static String submitQuestion(Request req, Response res) {
        QuestionDao questionDao = QuestionDao.getInstance();
        questionDao.createQuestionFromRequest(req);
        res.redirect("/");
        return null;
    }

    private static String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }
}
