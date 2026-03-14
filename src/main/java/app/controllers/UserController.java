package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class UserController {

    public void addRoutes(Javalin app, ConnectionPool connectionPool){
        //Get er en href / et link
        //post is when formed is submitted, it handles

        app.get("/registrerbruger",ctx -> ctx.render("registrerbruger.html"));
        app.post("/registrerbruger", ctx -> registrerBruger(ctx, connectionPool));

        app.post("/post", ctx -> ctx.render("post.html"));
        app.get("/post", ctx -> ctx.render("post.html"));

        app.get("/login", ctx -> ctx.render("login.html"));
        app.post("/login", ctx -> login(ctx, connectionPool)); // <- denne mangler

        app.get("/logout", ctx -> logout(ctx));
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            // test data - simulerer kald til DB via mapper
            /* List<Post> posts = List.of(
                    new Post(1, "Min første post", "xxx","cat.jpg"),
                    new Post(2, "Hej fra Javalin", "XXX","coffee.jpg"),
                    new Post(3, "Syntax er svær", "xxx", "code.jpg")
            );
            ctx.attribute("postList", posts);
            ctx.render("post.html");*/
            // Better solution:
            ctx.redirect("/posts");
        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("login.html");
        }
    }

    public void registrerBruger(Context ctx, ConnectionPool connectionPool){
        //gets data from form
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        //Creates user using usermapper class
        try {
            UserMapper.createuser(username, password, connectionPool);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        //renders frontpage when form is filled
        ctx.render("index.html");

        //prints username data in console (in this case intelliJ)
        System.out.println(username + " " + password);
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }


    /*
    private void loginBruger(Context ctx, ConnectionPool connectionPool) {
        // valider mod databasen her
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
    }

     */

}
