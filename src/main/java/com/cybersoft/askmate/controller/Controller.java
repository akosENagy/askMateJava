package com.cybersoft.askmate.controller;

import com.cybersoft.askmate.dao.DataManager;
import com.cybersoft.askmate.model.Answer;
import com.cybersoft.askmate.model.Question;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.Query;
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

    public static String submitAnswer(Request req, Response res) {
        Answer answer = createAnswerFromRequest(req);
        DataManager.getInstance().persist(answer);
        res.redirect("/");
        return null;
    }

    public static Answer createAnswerFromRequest(Request req) {
        Answer answer =  new Answer(req.queryParams("new-answer-title"), req.queryParams("new-answer-content"));
        Query query = DataManager.getInstance().getEntityManager().createNamedQuery("Question.getById");
        query.setParameter("id", Integer.valueOf(req.pathInfo().substring(1)));
        Question question = (Question) query.getSingleResult();
        answer.setQuestion(question);
        return answer;
    }

    private static String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }

    public static Question createQuestionFromRequest(Request req) {
        return new Question(req.queryParams("new-question-title"), req.queryParams("new-question-content"));
    }
}
