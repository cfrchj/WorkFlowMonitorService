package controllers;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
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
import java.util.List;

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
    /*
    public static void login(@Required String username, String password) {

        if (session.get("username") == null || session.isEmpty()) {	//未登录
            String msg;
            if (validation.hasErrors()) {	//用户名不能为空
                msg = "用户名不能为空";
                //flash.error("Oops, please enter your name!");
                index(msg);

            }

            User u = User.find("uName", username).first();
            if (u == null) {	//没有这个用户
                msg = "没有这个用户,请联系管理员。";
                //flash.error("Oops, no such user!");
                index(msg);
            }

            if(u.isZaizhi == Constant.ISZAIZHI_N){
                msg = "该用户已经注销";
                index(msg);
            }
            password = MD5Util.MD5(password);	//密码加密
            if (u.passWord.equals(password)) {	//密码正确
                //记录当前登录用户名和用户权限 权限0为管理员用户 权限1为普通用户
                session.put("username", username);
                session.put("permission", u.permission);
                session.put("user",u.name);//中文名
                session.put("role",u.role);//学生、老师

                //判断是否设置了邮箱，未设置邮箱则先设置邮箱
                if(u.EmailAdress == null || u.EmailAdress.equals("")){
                    msg = "EmailError : 你还没有设置邮箱，请先设置邮箱:)";
                    render("/Application/setEmailAdressForm.html",msg);
                }else {
                    session.put("email",u.EmailAdress);
                }

                List<Equipment> le;
                if (u.permission == Constant.PERMISSION_ADMIN) {	//管理员用户
                    le = Equipment.findAll();	//查看所有设备
                    render(le);
                } else {	//普通用户
                    //查看该用户拥有的设备以及负责的设备
                    le = Equipment.find("select p from Equipment p where p.ResponsiblePerson = ? or p.User = ? order by User desc", u.name, u.name).fetch();
                    render(le);
                }
            } else {	//密码错误
                msg = "密码错了。";
                index(msg);
            }
        } else {	//已经登陆
            isSetEmail();
            username = session.get("username");
            String name = User.getNameByuName(username);
            List<Equipment> le;
            int permission = Integer.parseInt(session.get("permission"));
            if (permission == Constant.PERMISSION_ADMIN) {	//管理员用户
                le = Equipment.findAll();
                render(le);
            } else {	//普通用户
                le = Equipment.find("select p from Equipment p where p.ResponsiblePerson = ? or p.User = ? order by User desc", name, name).fetch();
                render(le);
            }
        }
    }

*/

/*






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

    */

