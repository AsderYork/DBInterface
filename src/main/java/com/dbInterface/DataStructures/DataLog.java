package com.dbInterface.DataStructures;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Asder on 18.12.2016.
 */
public class DataLog {

    public final SimpleStringProperty Action;
    public final SimpleStringProperty Target;
    public final SimpleStringProperty User;
    public final SimpleStringProperty Desc;
    public final SimpleStringProperty Date;

    public DataLog(String login, String hashpwd, String salt, String id, String Date) {
        this.Action =  new SimpleStringProperty(login);
        this.Target = new SimpleStringProperty( hashpwd);
        this.User = new SimpleStringProperty(salt);
        this.Desc = new SimpleStringProperty(id);
        this.Date = new SimpleStringProperty(Date);
    }

    public String getAction() {
        return Action.get();
    }
    public void setlogin(String nlogin) {
        Action.set(nlogin);
    }

    public String getTarget() {
        return Target.get();
    }
    public void setTarget(String nhashpwd) {
        Target.set(nhashpwd);
    }

    public String getUser() {
        return User.get();
    }
    public void setUser(String nsalt) {
        User.set(nsalt);
    }
    public String getDesc() {
        return Desc.get();
    }
    public void setDesc(String nid) {
        Desc.set(nid);
    }


    public String getDate() {
        return Date.get();
    }
    public void setDate(String nlogin) {
        Date.set(nlogin);
    }

}
