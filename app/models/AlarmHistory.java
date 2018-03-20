package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AlarmHistory extends Model{
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

    public static Finder<String, AlarmHistory> finder = new Finder<>(AlarmHistory.class);
}
