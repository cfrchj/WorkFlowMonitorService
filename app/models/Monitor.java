package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monitor extends Model {

    @Id
    private String monitor_id;
    private String task_id;
    private String create_time;

    public Monitor(String monitor_id, String task_id, String create_time) {
        this.monitor_id = monitor_id;
        this.task_id = task_id;
        this.create_time = create_time;
    }

    public static Finder<String,Monitor> finder = new Finder<>(Monitor.class);

    public String getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(String monitor_id) {
        this.monitor_id = monitor_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
