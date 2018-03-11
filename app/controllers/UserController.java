package controllers;


import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;


public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    /* create a web view to show user form     */
    public Result Create(){
        Form<User> userForm = formFactory.form(User.class);

        return TODO;
        //return ok(create.render(userForm));

    }
}
