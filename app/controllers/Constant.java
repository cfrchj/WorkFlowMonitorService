package controllers;

import models.*;
import utils.IdUtil;

import java.util.List;
import java.util.Timer;

public class Constant {
    //ID生成
    public static IdUtil idUtil = new IdUtil((int)(Math.random()*10));
    public static List<User> users = null;
    public static List<Flow> flows = null;
    public static List<Task> tasks = null;
    public static List<Monitor> monitors = null;
    public static List<Alarm> alarms = null;
    public static List<MHistory> mHistories = null;
    public static List<AHistory> alarmHistories = null;
    public static Boolean notInited = true;
    public static Timer timer = null;

    public static void InitMemList(){
        if(notInited){
            users = User.finder.all();
            flows = Flow.finder.all();
            tasks = Task.finder.all();
            monitors = Monitor.finder.all();
            alarms = Alarm.finder.all();
            mHistories = MHistory.finder.all();
            alarmHistories = AHistory.finder.all();
            notInited = false;
        }

    }

    public static User findUserbyName(String username){

        for(User user:users){
            if(user.getUser_name().equals(username)){
                return user;
            }
        }
        return null;
    }

}
