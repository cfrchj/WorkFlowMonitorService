package controllers;

import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.index;

public class HomeController extends Controller {


    public Result index() {

        return  ok(index.render());
    }
/*

    public Result register() {
        Form<User> userForm = Form.form(User.class);
        return ok(views.html.register.render(userForm));
    }


    public Result postRegister() {
        Form<Registration> userForm =
                Form.form(Registration.class).bindFromRequest();
        User user = new User(userForm.get().email, userForm.get().password);
        user.save();
        return ok("registered");
    }


    public static class Login {
        @Email
        public String email;
        @Required
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result login() {
        Form<Login> userForm = Form.form(Login.class);
        return ok(views.html.login.render(userForm));
    }

    public static Result postLogin() {
        Form<Login> userForm = Form.form(Login.class).bindFromRequest();
        if (userForm.hasErrors()) {
            return badRequest("Wrong user/password");
        } else {
            return ok("Valid user");
        }
    }*/

}
