package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AHistory extends Model{
    @Id
    private String alarm_id;
    private String flow_id;
    private String flow_creator;
    private String flow_name;
    private String task_id;
    private String task_name;
    private String task_creator;
    private String update_time;
    private String error_message;

    public static Finder<String, AHistory> finder = new Finder<>(AHistory.class);

    public AHistory(String alarm_id, String flow_id, String flow_creator, String flow_name, String task_id, String task_name, String task_creator, String update_time, String error_message) {
        this.alarm_id = alarm_id;
        this.flow_id = flow_id;
        this.flow_creator = flow_creator;
        this.flow_name = flow_name;
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_creator = task_creator;
        this.update_time = update_time;
        this.error_message = error_message;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getFlow_creator() {
        return flow_creator;
    }

    public void setFlow_creator(String flow_creator) {
        this.flow_creator = flow_creator;
    }

    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_creator() {
        return task_creator;
    }

    public void setTask_creator(String task_creator) {
        this.task_creator = task_creator;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AHistory)) return false;
        AHistory that = (AHistory) o;
        return Objects.equals(getAlarm_id(), that.getAlarm_id()) &&
                Objects.equals(getFlow_id(), that.getFlow_id()) &&
                Objects.equals(getFlow_creator(), that.getFlow_creator()) &&
                Objects.equals(getFlow_name(), that.getFlow_name()) &&
                Objects.equals(getTask_id(), that.getTask_id()) &&
                Objects.equals(getTask_name(), that.getTask_name()) &&
                Objects.equals(getTask_creator(), that.getTask_creator()) &&
                Objects.equals(getUpdate_time(), that.getUpdate_time()) &&
                Objects.equals(getError_message(), that.getError_message());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAlarm_id(), getFlow_id(), getFlow_creator(), getFlow_name(), getTask_id(), getTask_name(), getTask_creator(), getUpdate_time(), getError_message());
    }
}
