package models;


import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Flow extends Model{
    @Id
    private String flow_id;
    private String flow_name;
    private String flow_creator;
    private String flow_status;
    private String flow_first_id;
    private String update_time;

    public static Finder<String,Flow> finder = new Finder<>(Flow.class);

    public Flow(String flow_id, String flow_name, String flow_creator, String flow_status, String flow_first_id, String update_time) {
        this.flow_id = flow_id;
        this.flow_name = flow_name;
        this.flow_creator = flow_creator;
        this.flow_status = flow_status;
        this.flow_first_id = flow_first_id;
        this.update_time = update_time;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }

    public String getFlow_creator() {
        return flow_creator;
    }

    public void setFlow_creator(String flow_creator) {
        this.flow_creator = flow_creator;
    }

    public String getFlow_status() {
        return flow_status;
    }

    public void setFlow_status(String flow_status) {
        this.flow_status = flow_status;
    }

    public String getFlow_first_id() {
        return flow_first_id;
    }

    public void setFlow_first_id(String flow_first_id) {
        this.flow_first_id = flow_first_id;
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
        if (!(o instanceof Flow)) return false;
        Flow flow = (Flow) o;
        return Objects.equals(getFlow_id(), flow.getFlow_id()) &&
                Objects.equals(getFlow_name(), flow.getFlow_name()) &&
                Objects.equals(getFlow_creator(), flow.getFlow_creator()) &&
                Objects.equals(getFlow_status(), flow.getFlow_status()) &&
                Objects.equals(getFlow_first_id(), flow.getFlow_first_id()) &&
                Objects.equals(getUpdate_time(), flow.getUpdate_time());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFlow_id(), getFlow_name(), getFlow_creator(), getFlow_status(), getFlow_first_id(), getUpdate_time());
    }
}
