package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alarm extends Model{
    @Id
    private String alarm_id;


}
