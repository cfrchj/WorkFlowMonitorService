package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Monitor {

    @Id
    private String monitor_id;
    private String task_id;
    private String create_time;


    public static Finder<String,Monitor> finder = new Finder<>(Monitor.class);


}
