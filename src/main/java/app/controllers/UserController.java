package app.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;


public class UserController {

    public void addRoutes(Javalin app){
        //They can have the same name, because one is post and other is get

        // is the link to register a user
        app.get("/registrerbruger",ctx -> ctx.render("registrerbruger.html"));
        //post is when formed is submitted
        app.post("/registrerbruger", ctx -> registrerBruger(ctx));
    }
    public void registrerBruger(Context ctx){
        //gets data from form
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        //renders frontpage when form is filled
        ctx.render("index.html");

        //prints username data in console (in this case intelliJ)
        System.out.println(username + " " + password);
    }

}
