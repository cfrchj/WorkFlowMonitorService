package models;


import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class WorkFlow extends Model{
    @Id
    private String flow_id;
    private String flow_name;
    private String flow_creator;
    private String flow_status;
    private String flow_tasks;
    private String flow_first_id;
    private String update_time;

    public WorkFlow(String flow_id, String flow_name, String flow_creator, String flow_status, String flow_tasks, String flow_first_id, String update_time) {
        this.flow_id = flow_id;
        this.flow_name = flow_name;
        this.flow_creator = flow_creator;
        this.flow_status = flow_status;
        this.flow_tasks = flow_tasks;
        this.flow_first_id = flow_first_id;
        this.update_time = update_time;
    }

    public static Finder<String,WorkFlow> finder = new Finder<>(WorkFlow.class);

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    public String getFlow_creator() {
        return flow_creator;
    }

    public void setFlow_creator(String flow_creator) {
        this.flow_creator = flow_creator;
    }

    public String getFlow_status() {
        return flow_status;
    }

    public void setFlow_status(String flow_status) {
        this.flow_status = flow_status;
    }

    public String getFlow_tasks() {
        return flow_tasks;
    }

    public void setFlow_tasks(String flow_tasks) {
        this.flow_tasks = flow_tasks;
    }

    public String getFlow_first_id() {
        return flow_first_id;
    }

    public void setFlow_first_id(String flow_first_id) {
        this.flow_first_id = flow_first_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public static Finder<String, WorkFlow> getFinder() {
        return finder;
    }

    public static void setFinder(Finder<String, WorkFlow> finder) {
        WorkFlow.finder = finder;
    }
}
