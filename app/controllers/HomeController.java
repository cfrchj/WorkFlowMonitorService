package controllers;

import models.Flow;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.*;
import views.html.*;


import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

import static controllers.Constant.findUserbyName;
import static controllers.Constant.users;

public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;

    //登录注册界面
    public Result index() {
        Constant.InitMemList();
        String userid = session("userid");
        if(userid == null){
            return ok(index.render());
        }
        User u = User.finder.byId(userid);
        if(u == null){
            return redirect(routes.HomeController.login());
        }else{
            return redirect(routes.HomeController.mainPage());
        }

    }

    //登录后的主页
    public Result mainPage(){

        String userid = session("userid");
        if(userid == null){
            return redirect(routes.HomeController.login());
        }
        User u = User.finder.byId(userid);

        if(u == null){
            return redirect(routes.HomeController.login());
        }else{
            return ok(mainpage.render(u.getUser_name()));
        }
    }

    //注册
    public Result register() {
        return ok(create.render());
    }

    //处理注册
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
        Constant.InitMemList();
        users.add(user);
        return redirect(routes.HomeController.login());
    }

    //登录
    public  Result login() {
        Constant.InitMemList();
        return ok(login.render());
    }

    //处理登录
    public Result postLogin() {
        DynamicForm userForm = formFactory.form().bindFromRequest();
        String name = userForm.get("username");
        String password = userForm.get("password");
        //return ok(test.render(user_name,user_passwd));
        User user = findUserbyName(name);
        if(user == null){
            return TODO;
        }else if(BCrypt.checkpw(password,user.getUser_password())){
            session().clear();
            session("userid",user.getUser_id());
            return redirect("/MainPage");
        }

        return  TODO;
    }

    public Result logout(){
        session().clear();
        return redirect("/");
    }
    //用户主页
    public Result userPage(){
        String userid = session("userid");
        User user = User.finder.byId(userid);
        //return ok(userpage.render(user));
        return TODO;
    }

    //工作流页面
    public Result workFlow(){

        return TODO;
    }
    public Result createFlow(){
        String userid = session("userid");
        User u = User.finder.byId(userid);
        return ok(flow_create.render(u.getUser_name()));
    }
    public Result postCreateFlow(){
        String userid = session("userid");

        DynamicForm userForm = formFactory.form().bindFromRequest();
        String flow_name = userForm.get("flow_name");
        String flow_id = String.valueOf(Constant.idUtil.nextId());
        String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Flow flow = new Flow(flow_id,flow_name,userid,"Not monitored",null,update_time);
        flow.save();
        Constant.InitMemList();
        Constant.flows.add(flow);
        return redirect(routes.HomeController.workFlow());
    }

    //业务监控页面
    public Result taskMonitor(){
        return TODO;
    }

    //告警信息页面
    public Result alarmInfo(){
        return TODO;
    }
}