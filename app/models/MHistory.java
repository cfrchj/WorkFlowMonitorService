package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class MHistory extends Model{
    @Id
    private String monitor_id;
    private String task_id;
    private String create_time;
    private String finish_time;

    public MHistory(String monitor_id, String task_id, String create_time, String finish_time) {
        this.monitor_id = monitor_id;
        this.task_id = task_id;
        this.create_time = create_time;
        this.finish_time = finish_time;
    }

    public static Finder<String,MHistory> finder = new Finder<>(MHistory.class);

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

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MHistory)) return false;
        MHistory mHistory = (MHistory) o;
        return Objects.equals(getMonitor_id(), mHistory.getMonitor_id()) &&
                Objects.equals(getTask_id(), mHistory.getTask_id()) &&
                Objects.equals(getCreate_time(), mHistory.getCreate_time()) &&
                Objects.equals(getFinish_time(), mHistory.getFinish_time());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMonitor_id(), getTask_id(), getCreate_time(), getFinish_time());
    }
}
