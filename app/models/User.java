package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User extends Model{
    @Id
    private String user_id;
    private String user_name;
    private String user_mail;
    private String user_phone;
    private String user_password;
    private String update_time;

    public User(String user_id, String user_name, String user_mail, String user_phone, String user_password,String update_time) {
        String passwdHash = BCrypt.hashpw(user_password,BCrypt.gensalt());
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_phone = user_phone;
        this.user_password = passwdHash;
        this.update_time = update_time;
    }

    public static Finder<String,User> finder = new Finder<>(User.class);

    // Authentification
    public static User authenticate(String user_id, String password) {
        User user =  User.finder.byId(user_id);
        if (user == null) {
            return user;
        } else if (BCrypt.checkpw(password, user.getUser_password())) {
            return user;
        } else {
            return null;
        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        String passwdHash = BCrypt.hashpw(user_password,BCrypt.gensalt());
        this.user_password = passwdHash;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUser_id(), user.getUser_id()) &&
                Objects.equals(getUser_name(), user.getUser_name()) &&
                Objects.equals(getUser_mail(), user.getUser_mail()) &&
                Objects.equals(getUser_phone(), user.getUser_phone()) &&
                Objects.equals(getUser_password(), user.getUser_password()) &&
                Objects.equals(getUpdate_time(), user.getUpdate_time());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser_id(), getUser_name(), getUser_mail(), getUser_phone(), getUser_password(), getUpdate_time());
    }
}
