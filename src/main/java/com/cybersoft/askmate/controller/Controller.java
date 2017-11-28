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

    private Map params = new HashMap();

    // Rendering functions
    public String renderQuestions(Request req, Response res){
        Map params = new HashMap<>();
        List<Question> questions = DataManager.getInstance().getEntityManager().createNamedQuery("Question.getAll").getResultList();
        params.put("questions", questions);
        return renderTemplate(params, "index");
    }

    public String renderSingleQuestion(Request req, Response res) {
        Map params = new HashMap<>();
        Query query = DataManager.getInstance().getEntityManager().createNamedQuery("Question.getById");
        String path = req.pathInfo();
        path = path.split("/")[path.split("/").length - 1];
        int questionId = Integer.valueOf(path);
        query.setParameter("id", questionId);
        Question question = (Question) query.getSingleResult();
        params.put("question", question);
        return renderTemplate(params, "details");
    }

    public String renderRegsiterForm(Request request, Response response) {
        return renderTemplate(new HashMap(), "register");
    }

    //Utilities

    public String submitQuestion(Request req, Response res) {
        Question question = createQuestionFromRequest(req);
        DataManager.getInstance().persist(question);
        res.redirect("/");
        return null;
    }

    public String submitAnswer(Request req, Response res) {
        Answer answer =  new Answer(req.queryParams("new-answer-title"), req.queryParams("new-answer-content"));
        Query query = DataManager.getInstance().getEntityManager().createNamedQuery("Question.getById");
        String path = req.pathInfo();
        path = path.split("/")[path.split("/").length - 2];
        int questionId = Integer.valueOf(path);
        query.setParameter("id", questionId);
        Question question = (Question) query.getSingleResult();
        answer.setQuestion(question);
        DataManager.getInstance().persist(answer);

        res.redirect("/questions/" + questionId);
        return null;
    }

    private String renderTemplate(Map model, String template) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, template));
    }

    private Question createQuestionFromRequest(Request req) {
        return new Question(req.queryParams("new-question-title"), req.queryParams("new-question-content"));
    }
}
