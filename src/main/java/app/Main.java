package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import app.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(SessionConfig.sessionConfig()));
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070); //The port to connect to the site

        // Routing to the html index
        //path = navn, filepath = html som den skal render
        //frontpage render
        app.get("/", ctx ->  ctx.render("index.html"));

        //method call for rendering user registerer (Controllers/UserController)
        UserController userController = new UserController();
        //Method addRoutes gets app, so UserController has access to javalin
        userController.addRoutes(app);

    }
}