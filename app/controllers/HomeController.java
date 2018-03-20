package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import utils.IdUtil;
import views.html.create;
import views.html.index;
import views.html.login;
import views.html.test;


import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;
    IdUtil idUtil = new IdUtil((int) (Math.random() * 10));

    public Result index() {
        return ok(index.render());
    }

    public Result register() {
        return ok(create.render());
    }

    public Result postRegister() {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        User user = userForm.get();
        String userid = String.valueOf(idUtil.nextId());
        String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        user.setUser_id(userid);
        user.setUpdate_time(update_time);
        user.save();
        return redirect(routes.HomeController.index());
    }

    public  Result login() {
        return ok(login.render());
    }

    public Result postLogin() {
        DynamicForm userForm = formFactory.form().bindFromRequest();
        String user_mail = userForm.get("usermail");
        String user_passwd = userForm.get("password");
        return  ok(test.render(user_mail,user_passwd));
    }
}