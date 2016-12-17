package com.dbInterface.DataStructures;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Asder on 18.12.2016.
 */
public class DataAuth {

    public final SimpleStringProperty username;
    public final SimpleStringProperty date;

    public DataAuth(String username, String date) {
        this.username =  new SimpleStringProperty(username);
        this.date = new SimpleStringProperty(date);
    }

    public String getUsername() {
        return username.get();
    }
    public void setUsername(String nlogin) {
        username.set(nlogin);
    }

    public String getDate() {
        return date.get();
    }
    public void setDate(String nlogin) {
        date.set(nlogin);
    }

}
