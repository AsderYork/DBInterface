package com.dbInterface.DataStructures;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Asder on 17.12.2016.
 */
public class DataUser {

    public final SimpleStringProperty login;
    public final SimpleStringProperty hashpwd;
    public final SimpleStringProperty salt;
    public final SimpleStringProperty id;

    public DataUser(String login, String hashpwd, String salt, String id) {
        this.login =  new SimpleStringProperty(login);
        this.hashpwd = new SimpleStringProperty( hashpwd);
        this.salt = new SimpleStringProperty(salt);
        this.id = new SimpleStringProperty(id);
    }

    public String getLogin() {
        return login.get();
    }
    public void setlogin(String nlogin) {
        login.set(nlogin);
    }

    public String getHashpwd() {
        return hashpwd.get();
    }
    public void setHashpwd(String nhashpwd) {
        hashpwd.set(nhashpwd);
    }

    public String getSalt() {
        return salt.get();
    }
    public void setSalt(String nsalt) {
        salt.set(nsalt);
    }
    public String getId() {
        return id.get();
    }
    public void setId(String nid) {
        id.set(nid);
    }

}
