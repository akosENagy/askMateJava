package com.cybersoft.askmate.controller;

import com.cybersoft.askmate.Authentication.Authenticator;
import com.cybersoft.askmate.dao.DataManager;
import com.cybersoft.askmate.model.Answer;
import com.cybersoft.askmate.model.Question;
import com.cybersoft.askmate.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Controller {

    private DataManager dataManager;
    private Authenticator authenticator;

    private Map params = new HashMap();

    public Controller(DataManager dataManager, Authenticator authenticator) {
        this.dataManager = dataManager;
        this.authenticator = authenticator;
    }

    // Rendering functions
    public String renderQuestions(Request req, Response res){
        Map params = new HashMap<>();
        List<Question> questions = dataManager.getEntityManager().createNamedQuery("Question.getAll").getResultList();
        params.put("questions", questions);
        return renderTemplate(params, "index");
    }

    public String renderSingleQuestion(Request req, Response res) {
        Map params = new HashMap<>();
        Query query = dataManager.getEntityManager().createNamedQuery("Question.getById");
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

    public String registerUser(Request request, Response response) {
        User user = createUserFromRequest(request);

        if (user != null) {
            params.put("user", user);

            //TODO re-registering user. (ConstraintViolationException)
            dataManager.persist(user);
            return renderTemplate(params, "index");
        }

        return renderTemplate(params, "register");

    }

    private User createUserFromRequest(Request request) {
        String username = request.queryParams("username");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String passwordHash;
        try {
            passwordHash = authenticator.createHash(password);
            User user = new User(username, email, passwordHash);
            return user;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String submitQuestion(Request req, Response res) {
        Question question = createQuestionFromRequest(req);
        dataManager.persist(question);
        res.redirect("/");
        return null;
    }

    public String submitAnswer(Request req, Response res) {
        Answer answer =  new Answer(req.queryParams("new-answer-title"), req.queryParams("new-answer-content"));
        Query query = dataManager.getEntityManager().createNamedQuery("Question.getById");
        String path = req.pathInfo();
        path = path.split("/")[path.split("/").length - 2];
        int questionId = Integer.valueOf(path);
        query.setParameter("id", questionId);
        Question question = (Question) query.getSingleResult();
        answer.setQuestion(question);
        dataManager.persist(answer);

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
