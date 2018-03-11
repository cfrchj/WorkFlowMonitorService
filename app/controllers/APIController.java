package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.IdUtil;

import java.sql.SQLException;

public class APIController extends Controller {

    //随机数生成器
    IdUtil idUtil = new IdUtil((int)(Math.random()*10));

    public Result user_create(){
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();
        if(json == null) {
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            String username = json.findPath("username").textValue();
            String usermail = json.findPath("usermail").textValue();
            String userphone = json.findPath("userphone").textValue();
            String userpasswd = json.findPath("userpasswd").textValue();
            if(username == null||usermail == null||userphone == null||userpasswd == null) {
                result.put("status","error");
                result.put("message","Missing parameter");
                return ok(result);
            } else {
                String userid = String.valueOf(idUtil.nextId());
                User user = new User(userid,username,usermail,userphone,userpasswd);
                try {
                    addUser(user);
                    result.put("status","success");
                    result.put("userid", userid);

                    return ok(result);
                } catch (SQLException e) {
                    result.put("status","error");
                    result.put("message",e.getMessage());

                }
                return null;
            }
        }

    }


    public Result user_edit(){

        return TODO;
    }


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
                result.put("message","Missing parameter");
                return ok(result);
            } else {
                try {
                    delUser(userid);
                    result.put("status","success");
                    return ok(result);
                } catch (SQLException e) {
                    result.put("status","error");
                    result.put("message",e.getMessage());

                }
                return null;
            }
        }

    }


    public Result user_search(){
        return TODO;
    }


    public Result flow_create(){

        return TODO;

    }


    public Result flow_edit(){
        return TODO;

    }


    public Result flow_delete(){
        return TODO;

    }

    public Result flow_search(){
        return TODO;

    }


    public Result task_create(){

        return TODO;

    }


    public Result task_edit(){
        return TODO;

    }


    public Result task_delete(){
        return TODO;

    }

    public Result task_search(){
        return TODO;

    }

}
