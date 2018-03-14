package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MHistory extends Model{
    @Id
    private String monitor_id;
    private String task_id;
    private String create_time;
    private String finish_time;

    public static Finder<String,Monitor> finder = new Finder<>(Monitor.class);
}
