package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static List<User> users = null;
    public static List<Flow> flows = null;
    public static List<Task> tasks = null;
    public static List<Monitor> monitors = null;
    public static List<Alarm> alarms = null;
    public static List<MHistory> mHistories = null;
    public static List<AlarmHistory> alarmHistories = null;
    public static Boolean notInited = true;

    public static void InitMemList(){
        if(notInited){
            users = User.finder.all();
            flows = Flow.finder.all();
            tasks = Task.finder.all();
            monitors = Monitor.finder.all();
            alarms = Alarm.finder.all();
            mHistories = MHistory.finder.all();
            alarmHistories = AlarmHistory.finder.all();
            notInited = false;
        }

    }

    public static String findPasswordbyName(String username){

        for(User user:users){
            if(user.getUser_name().equals(username)){
                return user.getUser_password();
            }
        }
        return null;
    }

}
