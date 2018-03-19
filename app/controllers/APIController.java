package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Monitor;
import models.Task;
import models.User;
import models.Flow;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.IdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class APIController extends Controller {

    //RESTFUL API 接口


    //ID生成
    IdUtil idUtil = new IdUtil((int)(Math.random()*10));

    /**
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
                String userid = idUtil.nextId();
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                User user = new User(userid,username,usermail,userphone,userpasswd,update_time);
                user.save();                                                                //插入数据到数据库
                result.put("status","success");
                result.put("userid", userid);
                return ok(result);
            }
        }

    }

    /**

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

    /**
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
            String userid = json.findPath("user_id").textValue();
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

    /**



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
                String flow_id = idUtil.nextId();
                String flow_tasks = flow_id;
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                Flow flow = new Flow(flow_id,flow_name,flow_creator,"Not monitored",flow_tasks,null,update_time);
                flow.save();                                                                               //插入数据到数据库
                result.put("status","success");
                result.put("flow_id", flow_id);
                return ok(result);
            }
        }

    }

    /**

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
                result.put("message","Missing parameter flow_id");
                return ok(result);
            } else {
                Flow flow = Flow.finder.byId(flow_id);
                if(flow == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                }
                if(flow_name!=null){
                    flow.setFlow_name(flow_name);
                }
                if(flow_creator!=null){
                    flow.setFlow_creator(flow_creator);
                }
                String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                flow.setUpdate_time(update_time);
                flow.update();
                result.put("status","success");
                return ok(result);
            }
        }

    }

    /**

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
                result.put("message","Missing parameter flow_id");
                return ok(result);
            } else {
                Flow flow = Flow.finder.byId(flow_id);
                if(flow == null) {
                    result.put("status", "error");
                    result.put("message", "User not found");
                    return ok(result);
                }
                flow.delete();
                result.put("status","success");
                return ok(result);
            }
        }
    }

    /**
     {
     "flow_id":"********",
     "user_id":"",
     "tasks":[
     {
     "task_name":"",
     "task_tpye":"",
     "task_app_id":""
     },
     {
     "task_name":"",
     "task_tpye":"",
     "task_app_id":""
     },
     {
     "task_name":"",
     "task_tpye":"",
     "task_app_id":""
     }
     ]
     }
     */
    public Result task_create() throws JSONException {
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            //get json data
            String flow_id = json.findPath("flow_id").textValue();
            String user_id = json.findPath("user_id").textValue();
            String tasks = json.findPath("tasks").textValue();

            if(flow_id == null||user_id==null||tasks == null) {
                result.put("status","error");
                result.put("message","Missing parameter flow_id");
                return ok(result);
            } else {
                JSONArray arr = null;
                try {
                    arr = new JSONArray(tasks);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //get task_id
                ArrayList<String> taskids = new ArrayList<>();
                for(int i = 0; i < arr.length(); i++) {
                    taskids.add(idUtil.nextId());
                }

                Flow flow = Flow.finder.byId(flow_id);
                if(flow ==null){
                    result.put("status","error");
                    result.put("message","Flow Not Found");
                    return ok(result);
                }
                if(flow.getFlow_first_id() == null) {
                    flow.setFlow_first_id(taskids.get(0));
                    flow.update();
                }else{
                    //找到最末尾的task 并修改他的newxt task id
                    Task task = Task.finder.byId(flow.getFlow_first_id());
                    while(task.getNext_task_id()!=null){
                        task = Task.finder.byId(task.getNext_task_id());
                    }
                    task.setNext_task_id(taskids.get(0));
                    task.update();
                }
                //List<Task> list = new ArrayList<Task>();
                for(int i = 0; i < arr.length(); i++){
                    String task_id = taskids.get(i);
                    String task_name = arr.getJSONObject(i).getString("task_name");
                    String task_type = arr.getJSONObject(i).getString("task_type");
                    String task_app_id = arr.getJSONObject(i).getString("task_app_id");
                    String next_task_id = null;
                    String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    if((i+1)!=arr.length()){
                        next_task_id =taskids.get(i+1);
                    }
                    Task task = new Task(task_id,flow_id,user_id,task_name,task_app_id,task_type,next_task_id,update_time);
                    task.save();
                }
                result.put("status","success");
                return ok(result);
            }
        }

    }

    /**

     */
    public Result task_edit(){

        return TODO;

    }

    /**
     {"taskid":""}
     */
    public Result task_delete(){
        return TODO;

    }

    /**
     {"tasks":[{"task_id",""},{"task_id",""},{"task_id",""}]}
     */
    public Result monitor_create() throws JSONException {
        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        if(json == null) {
            result.put("status","error");
            result.put("message","Expecting Json data");
            return ok(result);
        } else {
            //get json data
            String tasks = json.findPath("tasks").textValue();

            if(tasks == null) {
                result.put("status","error");
                result.put("message","Missing parameter flow_id");
                return ok(result);
            } else {
                JSONArray arr = new JSONArray(tasks);

                for(int i = 0; i < arr.length(); i++){
                    String task_id = arr.getJSONObject(i).getString("task_id");
                    String update_time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    Monitor monitor = new Monitor(idUtil.nextId(),task_id,update_time);
                    monitor.save();
                }
                result.put("status","success");
                return ok(result);
            }
        }

    }

    /**

     */
    public Result monitor_edit(){
        return TODO;

    }

    /**

     */
    public Result monitor_delete(){
        return TODO;

    }


}
