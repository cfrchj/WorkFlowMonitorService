package controllers;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import utils.IdUtil;
import views.html.create;
import views.html.index;
import views.html.login;


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
        Form<User> userForm = formFactory.form(User.class);
        return ok(create.render(userForm));
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
        Form<User> userForm = formFactory.form(User.class);
        return ok(login.render(userForm));

    }

    public Result postLogin() {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        User user =  User.finder.byId(userForm.get().getUser_id());
        if (user == null) {
            return ok("wrong 1 ");
        }
        return  ok(userForm.get().getUser_password());
    }
}