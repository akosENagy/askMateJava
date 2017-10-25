package com.cybersoft.askmate.controller;

import com.cybersoft.askmate.dao.QuestionDao;
import com.cybersoft.askmate.model.Question;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    public static ModelAndView renderQuestions(Request req, Response res){
        Map params = new HashMap<>();
        List<Question> questions = QuestionDao.getInstance().getQuestions();
        params.put("questions", questions);
        return new ModelAndView(params, "index");
    }
}
