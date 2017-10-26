import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.cybersoft.askmate.controller.Controller;
import com.cybersoft.askmate.dao.QuestionDao;
import com.cybersoft.askmate.model.Question;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // data business
        populateQuestionDao();

        // routes
        get("/", (req, res) -> {
            return new ThymeleafTemplateEngine().render(Controller.renderQuestions(req, res));
        });

        post("/submit-new-question", (req, res) -> {
            QuestionDao questionDao = QuestionDao.getInstance();
            questionDao.createQuestionFromRequest(req);
            res.redirect("/");
            return null;
        });
    }

    private static void populateQuestionDao() {
        new Question("Why is it that a snorlax is blocking my way?", "I really can't get past this fat fuck. I know i need a pokéflute but where do I find one?");
        new Question("Orly?", "O R L Y ?");
    }
}
