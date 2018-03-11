package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.IdUtil;


public class APIController extends Controller {

    //RESTFUL API 接口


    //ID生成
    IdUtil idUtil = new IdUtil((int)(Math.random()*10));

    /*

     */
    public Result user_create(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            //处理异常
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String username = json.findPath("user_name").textValue();
            String usermail = json.findPath("user_mail").textValue();
            String userphone = json.findPath("user_phone").textValue();
            String userpasswd = json.findPath("user_passwd").textValue();

            if(username == null||usermail == null||userphone == null||userpasswd == null) {
                result.put("status","error");
                result.put("message","Missing parameter");
                return ok(result);
            } else {
                String userid = String.valueOf(idUtil.nextId());
                User user = new User(userid,username,usermail,userphone,userpasswd);
                user.save();                                                                //插入数据到数据库
                result.put("status","success");
                result.put("userid", userid);
                return ok(result);
            }
        }

    }

    /*

     */
    public Result user_edit(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            //处理异常
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String userid = json.findPath("user_id").textValue();
            String username = json.findPath("user_name").textValue();
            String usermail = json.findPath("user_mail").textValue();
            String userphone = json.findPath("user_phone").textValue();
            String userpasswd = json.findPath("user_passwd").textValue();

            if(userid == null) {
                result.put("status","error");
                result.put("message","Missing parameter user_id");
                return ok(result);
            } else {
                User user = User.finder.byId(userid);
                if(user == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                }
                if(username!=null){
                    user.setUser_name(username);
                }
                if(usermail!=null){
                    user.setUser_mail(usermail);
                }
                if(userphone!=null){
                    user.setUser_phone(userphone);
                }
                if(userpasswd!=null){
                    user.setUser_password(userpasswd);
                }
                user.update();
                result.put("status","success");
                return ok(result);
            }
        }
    }

    /*

     */
    public Result user_delete(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();
        if(json == null) {
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String userid = json.findPath("userid").textValue();
            if(userid == null) {
                result.put("status","error");
                result.put("message","Missing parameter user_id");
                return ok(result);
            } else {
                User user = User.finder.byId(userid);
                if(user == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                }
                user.delete();
                result.put("status","success");
                return ok(result);
            }
        }

    }

    /*

     */
    public Result user_search(){
        return TODO;
    }

    /*

     */
    public Result flow_create(){

        return TODO;

    }

    /*

     */
    public Result flow_edit(){
        return TODO;

    }

    /*

     */
    public Result flow_delete(){
        return TODO;

    }

    /*

     */
    public Result flow_search(){
        return TODO;

    }

    /*

     */
    public Result task_create(){

        return TODO;

    }

    /*

     */
    public Result task_edit(){
        return TODO;

    }

    /*

     */
    public Result task_delete(){
        return TODO;

    }

    /*

     */
    public Result task_search(){
        return TODO;

    }

}
