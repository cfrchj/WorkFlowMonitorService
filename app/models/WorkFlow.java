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

}
