package com.cybersoft.askmate.controller;

import com.cybersoft.askmate.dao.DataManager;
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
        List<Question> questions = DataManager.getInstance().getEntityManager().createNamedQuery("Question.getAll").getResultList();
        params.put("questions", questions);
        return renderTemplate(params, "index");
    }

    public static String submitQuestion(Request req, Response res) {
        Question question = createQuestionFromRequest(req);
        DataManager.getInstance().persist(question);
        res.redirect("/");
        return null;
    }

    private static String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }

    public static Question createQuestionFromRequest(Request req) {
        return new Question(req.queryParams("new-question-title"), req.queryParams("new-question-content"));
    }
}
