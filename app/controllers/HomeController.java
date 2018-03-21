package controllers;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import utils.IdUtil;
import views.html.*;


import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

import static controllers.Constant.InitMemList;
import static controllers.Constant.findPasswordbyName;
import static controllers.Constant.users;

public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;
    public Result index() {
        InitMemList();
        String name = session("username");
        return ok(mainpage.render("景鹏坤"));
        /*
        if(name!= null){
            return ok(index.render());
        }else{
            return ok(mainpage.render(name));
        }*/
    }

    public Result register() {
        return ok(create.render());
    }

    public Result postRegister() {
        DynamicForm userForm = formFactory.form().bindFromRequest();
        String username = userForm.get("username");
        String usermail = userForm.get("usermail");
        String userphone = userForm.get("userphone");
        String userpasswd = userForm.get("password");
        String userid = String.valueOf(Constant.idUtil.nextId());
        String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        User user = new User(userid,username,usermail,userphone,userpasswd,update_time);
        user.save();
        users.add(user);
        return redirect(routes.HomeController.index());
    }

    public  Result login() {
        return ok(login.render());
    }

    public Result postLogin() {
        DynamicForm userForm = formFactory.form().bindFromRequest();
        String user_name = userForm.get("username");
        String user_passwd = userForm.get("password");
        //return ok(test.render(user_name,user_passwd));
        InitMemList();
        String password = findPasswordbyName(user_name);
        if(password == null){
            return TODO;
        }else if(BCrypt.checkpw(user_passwd,password)){
            session().clear();
            session("username",userForm.get("username"));
            return redirect("/");
        }

        return  TODO;
    }
}