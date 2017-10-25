import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.cybersoft.askmate.controller.Controller;
import com.cybersoft.askmate.model.Question;
import com.cybersoft.askmate.dao.QuestionDao;
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
    }

    private static void populateQuestionDao() {
        QuestionDao qdao = QuestionDao.getInstance();
        new Question("Why?", "SERIOUSLY WHY?");
        new Question("Orly?", "O R L Y ?");
    }
}
