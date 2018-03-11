package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model{

    @Id
    private String user_id;
    private String user_name;
    private String user_mail;
    private String user_phone;
    private String user_password;
    private String update_time;

    public User(String user_id, String user_name, String user_mail, String user_phone, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_phone = user_phone;
        this.user_password = user_password;
    }

    public static Finder<String,User> finder = new Finder<>(User.class);

}
