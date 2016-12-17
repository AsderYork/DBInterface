package com.dbInterface.DataStructures;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Asder on 17.12.2016.
 */
public class DataRoles {

    public final SimpleStringProperty id;
    public final SimpleStringProperty name;

    public DataRoles(String id, String name) {
        this.id =  new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public String getId() {
        return id.get();
    }
    public void setId(String nlogin) {
        id.set(nlogin);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String nlogin) {
        name.set(nlogin);
    }

}
