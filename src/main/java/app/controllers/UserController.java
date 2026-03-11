package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static app.Main.connectionPool;


public class UserController {

    public void addRoutes(Javalin app, ConnectionPool connectionPool){
        //They can have the same name, because one is post and other is get

        // is the link to register a user
        app.get("/registrerbruger",ctx -> ctx.render("registrerbruger.html"));
        //post is when formed is submitted
        app.post("/registrerbruger", ctx -> registrerBruger(ctx, connectionPool));
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

}
