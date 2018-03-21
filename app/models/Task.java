package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task extends Model{

    @Id
    private String task_id;
    private String flow_id;
    private String user_id;                 //user who create this task
    private String task_name;
    private String yarn_id;                 //yarn application id
    private String task_type;               //spark  spark-streaming
    private String next_task_id;            //to link flow's task sequence
    private String update_time;

    public Task(String task_id, String flow_id, String user_id, String task_name, String yarn_id, String task_type, String next_task_id, String update_time) {
        this.task_id = task_id;
        this.flow_id = flow_id;
        this.user_id = user_id;
        this.task_name = task_name;
        this.yarn_id = yarn_id;
        this.task_type = task_type;
        this.next_task_id = next_task_id;
        this.update_time = update_time;
    }

    public static Finder<String,Task> finder = new Finder<>(Task.class);

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getYarn_id() {
        return yarn_id;
    }

    public void setYarn_id(String yarn_id) {
        this.yarn_id = yarn_id;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getNext_task_id() {
        return next_task_id;
    }

    public void setNext_task_id(String next_task_id) {
        this.next_task_id = next_task_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getTask_id(), task.getTask_id()) &&
                Objects.equals(getFlow_id(), task.getFlow_id()) &&
                Objects.equals(getUser_id(), task.getUser_id()) &&
                Objects.equals(getTask_name(), task.getTask_name()) &&
                Objects.equals(getYarn_id(), task.getYarn_id()) &&
                Objects.equals(getTask_type(), task.getTask_type()) &&
                Objects.equals(getNext_task_id(), task.getNext_task_id()) &&
                Objects.equals(getUpdate_time(), task.getUpdate_time());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTask_id(), getFlow_id(), getUser_id(), getTask_name(), getYarn_id(), getTask_type(), getNext_task_id(), getUpdate_time());
    }
}
