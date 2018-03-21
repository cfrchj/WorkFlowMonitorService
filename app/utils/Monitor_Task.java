package utils;

import controllers.Constant;
import models.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Monitor_Task extends TimerTask {

    @Override
    public void run() {
        Constant.InitMemList();
        YarnUtils.init();
        for(Monitor monitor:Constant.monitors){
            Task task = Task.finder.byId(monitor.getTask_id());

            String status = YarnUtils.getApplicationStatus(task.getYarn_id());

            if(status.equals("FAILED") || status.equals("KILLED")){
                Flow  flow = Flow.finder.byId(task.getFlow_id());
                User task_creator = User.finder.byId(task.getUser_id());
                User flow_creator = User.finder.byId(flow.getFlow_creator());
                Alarm alarm = new Alarm(Constant.idUtil.nextId(),flow.getFlow_id(),flow_creator.getUser_name(),
                        flow.getFlow_name(),task.getTask_id(),task.getTask_name(),task_creator.getUser_name(),
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),status);
                alarm.save();
                Constant.alarms.add(alarm);
                MHistory mHistory = new MHistory(monitor.getMonitor_id(),monitor.getTask_id(),
                        monitor.getCreate_time(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                mHistory.save();
                Constant.mHistories.add(mHistory);
                monitor.delete();
            }

            Constant.monitors.remove(monitor);
        }
        YarnUtils.close();
    }
}

