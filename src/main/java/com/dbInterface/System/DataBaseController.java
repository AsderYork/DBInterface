package com.dbInterface.System;

import com.dbInterface.DataStructures.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.internal.OracleTypes;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBaseController {

   private static Connection connection = null;

    public static String Connect(String nameDB, String user, String password)
    {
        Locale.setDefault(Locale.ENGLISH);
        String ErrorString;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            ErrorString = "No Oracle JDBC driver found";
            return ErrorString;
        }
        System.out.println("Oracle JDBC Driver Registered!");

        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@"+nameDB, user, password);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            ErrorString = "Connection Failed";
            return ErrorString;
        }
        ErrorString = "Success";
        return ErrorString;
    }

    public static ObservableList<DataUser> GetUsersTable()
    {
        ObservableList<DataUser> data = FXCollections.observableArrayList();

        Statement stmt = null;
        String query = "SELECT * FROM USERS_DATA";

        Integer userID;
        String login;
        String hashPWD;
        String salt;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userID = rs.getInt("ID");
                login = rs.getString("LOGIN");
                hashPWD = rs.getString("HASHPWD");
                salt = rs.getString("SALT");


                //System.out.println("LOGIN:"+UserID);

                data.add(new DataUser(login, hashPWD, salt, userID.toString()));
            }
            return  data;

        }
        catch (SQLException e) {
            return null;
        }
    }

    public static void addUser(String login, String salt, String pwd){
        String hashedPwd;

        try {
            hashedPwd = Hasher.hashPassword(pwd,salt);
        } catch (NoSuchAlgorithmException e) {
            hashedPwd = "WRONGHASHING";
        }

        String query = "BEGIN USER_MANAGEMENT.ADD_USER(?,?,?); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, login);
            Querry.setString(2, hashedPwd);
            Querry.setString(3, salt);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("AddUserFailed");
        }
    }

    public static void deleteUser(String login, String pwd){
        String hashedPwd;



        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.DROP_USER(?,?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, login);
            Querry.setString(2, pwd);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DeleteUserFailed");
        }
    }

    public static void alterUser(String login, String oldPwd, String newSalt, String newPwd){
        String hashedPwd;

        try {
            hashedPwd = Hasher.hashPassword(newPwd,newSalt);
        } catch (NoSuchAlgorithmException e) {
            hashedPwd = "WRONGHASHING";
        }

        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.ALTER_USER_PWD_SALT(?,?,?,?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, login);
            Querry.setString(2, oldPwd);
            Querry.setString(3, hashedPwd);
            Querry.setString(4, newSalt);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("AlterUserFailed");
        }
    }

    public static ObservableList<DataRoles> GetRolesTable()
    {
        ObservableList<DataRoles> data = FXCollections.observableArrayList();

        Statement stmt = null;
        String query = "SELECT * FROM ROLES_DATA";

        Integer id;
        String name;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("ROLENAME");

                //System.out.println("LOGIN:"+UserID);

                data.add(new DataRoles(id.toString(), name));
            }
            return  data;

        }
        catch (SQLException e) {
            return null;
        }
    }

    public static void addRole(String name){


        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.ADD_ROLE(?); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, name);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("AddRoleFailed");
        }
    }

    public static void deleteRole(String name){


        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.DROP_ROLE(?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, name);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DropRoleFailed");
        }
    }

    public static void alterRole(String oldName, String newName){


        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.ALTER_ROLE(?, ?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, oldName);
            Querry.setString(2, newName);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("AlterRoleFailed");
        }
    }

    public static ObservableList<DataRule> GetRulesTable()
    {
        ObservableList<DataRule> data = FXCollections.observableArrayList();

        Statement stmt = null;
        String query = "SELECT * FROM USERS_ROLES";
        String suvqueryUser = "SELECT LOGIN FROM USERS_DATA WHERE ID=?";
        String suvqueryRole = "SELECT ROLENAME FROM ROLES_DATA WHERE ID=?";
        Integer userID;
        Integer roleID;

        String userLogin;
        String roleName;

        PreparedStatement QuerryRole=null;
        PreparedStatement QuerryUser=null;
        try {
            QuerryRole = connection.prepareStatement(suvqueryRole);
            QuerryUser = connection.prepareStatement(suvqueryUser);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed Subcuerry");
        }

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                userID = rs.getInt("USERID");
                roleID = rs.getInt("ROLEID");


                QuerryUser.setInt(1,userID);
                QuerryRole.setInt(1,roleID);

                ResultSet rsR = QuerryRole.executeQuery();
                ResultSet reU = QuerryUser.executeQuery();

                rsR.next();
                roleName = rsR.getString("ROLENAME");
                reU.next();
                userLogin = reU.getString("LOGIN");

                data.add(new DataRule(userID.toString(),roleID.toString(),userLogin, roleName));
            }
            return  data;

        }
        catch (SQLException e) {

            System.out.println("Failed maincuerry");
            return null;
        }
    }

    public static void addRule(String login, String role){


        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.SET_ROLE_TO_USER(?, ?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, login);
            Querry.setString(2, role);
            Querry.execute();

        } catch (SQLException e) {
            System.out.println("AddRuleFailed");
        }
    }


    public static void deleteRule(String login, String role){


        String query = "DECLARE Retval INT; BEGIN USER_MANAGEMENT.REMOVE_ROLE_FROM_USER(?, ?,Retval); END;";

        try {
            PreparedStatement Querry = connection.prepareStatement(query);
            Querry.setString(1, login);
            Querry.setString(2, role);
            Querry.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("RemoveRuleFailed");
        }
    }

    public static ObservableList<DataAuth> GetAuthTable()
    {
        ObservableList<DataAuth> data = FXCollections.observableArrayList();

        Statement stmt = null;
        String query = "SELECT * FROM USER_AUTHORIZATION";

        String username;
        String authDate;
        String authTime;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                username = rs.getString("USERNAME");
                authDate = rs.getDate("AUTHTIME").toString();
                authTime = rs.getTime("AUTHTIME").toString();

                data.add(new DataAuth(username, authDate+" "+authTime));
            }
            return  data;

        }
        catch (SQLException e) {

            return null;
        }
    }

    public static ObservableList<DataLog> GetLogTable(String inAction, String inUserLogin, String DateStart, String DateEnd)
    {
        ObservableList<DataLog> data = FXCollections.observableArrayList();

        Statement stmt = null;
        String query = "{ ? = call USER_MANAGEMENT.consolidated_report (?, ?, ?, ?)}";

        String action;
        String target;
        String user;
        String desc;
        String date;

        try {
            CallableStatement call = connection.prepareCall(query);
            call.registerOutParameter (1, OracleTypes.CURSOR);

            if(DateStart != null){
            java.util.Date DateS = new SimpleDateFormat("yyyy-MM-dd").parse(DateStart);

            call.setDate(2,new java.sql.Date(DateS.getTime()));
            }else{
                call.setDate(2,null);
            }

            if(DateEnd != null){
            java.util.Date DateE = new SimpleDateFormat("yyyy-MM-dd").parse(DateEnd);
                call.setDate(3,new java.sql.Date(DateE.getTime()));
            }else{
                call.setDate(3,null);
            }



            call.setString(4,inUserLogin);
            call.setString(5,inAction);

            call.execute ();
            ResultSet rset = (ResultSet)call.getObject (1);

            while (rset.next ())    {
                action = rset.getString("ACTION");
                target = rset.getString("TARGET");
                user = rset.getString("ACTIONUSER");
                desc = rset.getString("ACTIONDESC");
                date = rset.getDate("ACTIONDATE").toString()+" "+rset.getTime("ACTIONDATE").toString();

                data.add(new DataLog(action, target, user, desc, date));
            }

            return  data;

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
