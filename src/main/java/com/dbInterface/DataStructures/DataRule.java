package com.dbInterface.DataStructures;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Asder on 18.12.2016.
 */
public class DataRule {
    public final SimpleStringProperty UserID;
    public final SimpleStringProperty RoleID;
    public final SimpleStringProperty User;
    public final SimpleStringProperty Role;

    public DataRule(String UserID, String RoleID, String User, String Role) {
        this.UserID =  new SimpleStringProperty(UserID);
        this.RoleID = new SimpleStringProperty( RoleID);
        this.User = new SimpleStringProperty(User);
        this.Role = new SimpleStringProperty(Role);
    }

    public String getUserID() {
        return UserID.get();
    }
    public void setUserID(String nlogin) {
        UserID.set(nlogin);
    }

    public String getRoleID() {
        return RoleID.get();
    }
    public void setRoleID(String nhashpwd) {
        RoleID.set(nhashpwd);
    }

    public String getUser() {
        return User.get();
    }
    public void setUser(String nsalt) {
        User.set(nsalt);
    }
    public String getRole() {
        return Role.get();
    }
    public void setRole(String nid) {
        Role.set(nid);
    }

}
