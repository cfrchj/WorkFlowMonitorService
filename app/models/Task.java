package models;

public class Task {

    private String taskid;
    private String userid;
    private String appid;
    private String tasktype;

    public Task(){}

    public Task(String taskid, String userid, String appid, String tasktype) {
        this.taskid = taskid;
        this.userid = userid;
        this.appid = appid;
        this.tasktype = tasktype;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }
}
