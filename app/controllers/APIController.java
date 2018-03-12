package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import models.WorkFlow;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class APIController extends Controller {

    //RESTFUL API 接口


    //ID生成
    IdUtil idUtil = new IdUtil((int)(Math.random()*10));

    /*
        Send:
        {
            "user_name": "jing",
            "user_mail": "jing@mail.com",
            "user_phone": "123456789",
            "user_passwd": "abc"
        }
        Recieve:

        {
            "status": "success",
            "userid": "5321564******"
        }

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
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                User user = new User(userid,username,usermail,userphone,userpasswd,update_time);
                user.save();                                                                //插入数据到数据库
                result.put("status","success");
                result.put("userid", userid);
                return ok(result);
            }
        }

    }

    /*

        Send:
        {
            "user_id":"5321564******"，
            "user_name": "jing",                可选
            "user_mail": "jing@mail.com",       可选
            "user_phone": "123456789",          可选
            "user_passwd": "abc"                可选
        }
        Recieve:

        {
            "status": "success"
        }
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
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                user.setUpdate_time(update_time);
                user.update();
                result.put("status","success");
                return ok(result);
            }
        }
    }

    /*
         Send:
        {
            "user_id":"5321564******"
        }
        Recieve:

        {
            "status": "success"
        }
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
                    return ok(result);
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

        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            //处理异常
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        }else {
            String flow_name = json.findPath("flow_name").textValue();
            String flow_creator = json.findPath("user_id").textValue();

            if(flow_name == null||flow_creator == null) {
                result.put("status","error");
                result.put("message","Missing parameter");
                return ok(result);
            } else {
                String flow_id = String.valueOf(idUtil.nextId());
                String flow_tasks = flow_id;
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                WorkFlow workFlow = new WorkFlow(flow_id,flow_name,flow_creator,"Not monitored",flow_tasks,null,update_time);
                workFlow.save();                                                                               //插入数据到数据库
                result.put("status","success");
                result.put("flow_id", flow_id);
                return ok(result);
            }
        }

    }

    /*

     */
    public Result flow_edit(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            //处理异常
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String flow_id = json.findPath("flow_id").textValue();
            String flow_name = json.findPath("flow_name").textValue();
            String flow_creator = json.findPath("user_id").textValue();

            if(flow_id == null) {
                result.put("status","error");
                result.put("message","Missing parameter user_id");
                return ok(result);
            } else {
                WorkFlow workFlow = WorkFlow.finder.byId(flow_id);
                if(workFlow == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                }
                if(flow_name!=null){
                    workFlow.setFlow_name(flow_name);
                }
                if(flow_creator!=null){
                    workFlow.setFlow_creator(flow_creator);
                }
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                workFlow.setUpdate_time(update_time);
                workFlow.update();
                result.put("status","success");
                return ok(result);
            }
        }

    }

    /*

     */
    public Result flow_delete(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();
        if(json == null) {
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String flow_id = json.findPath("flow_id").textValue();
            if(flow_id == null) {
                result.put("status","error");
                result.put("message","Missing parameter user_id");
                return ok(result);
            } else {
                WorkFlow workFlow = WorkFlow.finder.byId(flow_id);
                if(workFlow == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                    return ok(result);
                }
                workFlow.delete();
                result.put("status","success");
                return ok(result);
            }
        }
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
