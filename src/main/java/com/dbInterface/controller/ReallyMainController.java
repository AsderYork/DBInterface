package com.dbInterface.controller;

import com.dbInterface.DataStructures.*;
import com.dbInterface.System.DataBaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Created by Asder on 17.12.2016.
 */
public class ReallyMainController implements Initializable {

    @FXML
   private TableColumn UserTableID;
    @FXML
    private TableColumn UserTableHashpwd;
    @FXML
    private TableColumn UserTableSalt;
    @FXML
    private TableColumn UserTableLogin;
    @FXML
    private TableView UserTable;
    @FXML
    private Button DeleteUserButton;
    @FXML
    private Button AddUserButton;
    @FXML
    private Button ReplaceUserButton;
    @FXML
    private Button UpdateUserTableButton;
    @FXML
    private TextField UserSaltField;
    @FXML
    private TextField UserLoginField;
    @FXML
    private TextField UserPwdField;

    @FXML
    private TextField RoleNameField;
    @FXML
    private TableColumn RolesIDCol;
    @FXML
    private TableColumn RolesNameCol;
    @FXML
    private TableView RolesTable;
    @FXML
    private Button RoleDeleteButton;
    @FXML
    private Button RolesUpdateButton;
    @FXML
    private Button RoleAddButton;
    @FXML
    private Button RoleRenameButton;

    @FXML
    private TableColumn RulesUserIDCol;
    @FXML
    private TableColumn RulesRoleIDCol;
    @FXML
    private TableColumn RulesUserCol;
    @FXML
    private TableColumn RulesRoleCol;
    @FXML
    private TableView RulesTable;
    @FXML
    private Button RulesUpdateButton;
    @FXML
    private Button RulesRemoveButton;
    @FXML
    private Button RulesAddRule;
    @FXML
    private TextField RulesUserField;
    @FXML
    private TextField RulesRoleField;

    @FXML
    private TableColumn AuthUsernameCol;
    @FXML
    private TableColumn AuthTimeCol;
    @FXML
    private TableView AuthTable;
    @FXML
    private Button AuthUpdateButton;


    @FXML
    private TableColumn LogActionCol;
    @FXML
    private TableColumn LogTargetCol;
    @FXML
    private TableColumn LogUserCol;
    @FXML
    private TableColumn LogDescCol;
    @FXML
    private TableColumn LogDateCol;
    @FXML
    private TableView LogTable;
    @FXML
    private Button LogSelectButton;
    @FXML
    private TextField LogActionField;
    @FXML
    private TextField LogUserField;
    @FXML
    private DatePicker LogBeginDate;
    @FXML
    private DatePicker LogEndDate;


    @FXML
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        UserTableID.setCellValueFactory(
                new PropertyValueFactory<DataUser, String>("Id"));

        UserTableHashpwd.setCellValueFactory(
                new PropertyValueFactory<DataUser, String>("Hashpwd"));

        UserTableSalt.setCellValueFactory(
                new PropertyValueFactory<DataUser, String>("Salt"));

        UserTableLogin.setCellValueFactory(
                new PropertyValueFactory<DataUser, String>("Login"));

        UserTable.setItems(DataBaseController.GetUsersTable());



        RolesIDCol.setCellValueFactory(
                new PropertyValueFactory<DataRoles, String>("Id"));

        RolesNameCol.setCellValueFactory(
                new PropertyValueFactory<DataRoles, String>("Name"));

        RolesTable.setItems(DataBaseController.GetRolesTable());



        RulesUserIDCol.setCellValueFactory(
                new PropertyValueFactory<DataRule, String>("UserID"));
        RulesRoleIDCol.setCellValueFactory(
                new PropertyValueFactory<DataRule, String>("Name"));
        RulesUserCol.setCellValueFactory(
                new PropertyValueFactory<DataRule, String>("User"));
        RulesRoleCol.setCellValueFactory(
                new PropertyValueFactory<DataRule, String>("Role"));

        RulesTable.setItems(DataBaseController.GetRulesTable());

        AuthUsernameCol.setCellValueFactory(
                new PropertyValueFactory<DataAuth, String>("Username"));
        AuthTimeCol.setCellValueFactory(
                new PropertyValueFactory<DataAuth, String>("Date"));

        AuthTable.setItems(DataBaseController.GetAuthTable());



        LogActionCol.setCellValueFactory(
                new PropertyValueFactory<DataLog, String>("Action"));
        LogTargetCol.setCellValueFactory(
                new PropertyValueFactory<DataLog, String>("Target"));
        LogUserCol.setCellValueFactory(
                new PropertyValueFactory<DataLog, String>("User"));
        LogDescCol.setCellValueFactory(
                new PropertyValueFactory<DataLog, String>("Desc"));
        LogDateCol.setCellValueFactory(
                new PropertyValueFactory<DataLog, String>("Date"));

        LogTable.setItems(DataBaseController.GetLogTable(null, null,null,null));


    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

    }

    @FXML
    private void handleUserUpdateButtonAction(ActionEvent event) throws IOException {
        UserTable.setItems(DataBaseController.GetUsersTable());
    }

    @FXML
    private void handleUserAddButtonAction(ActionEvent event) throws IOException {
        String Salt = UserSaltField.getText();
        String Login = UserLoginField.getText();
        String Pwd = UserPwdField.getText();
        UserLoginField.setText("");
        UserPwdField.setText("");

        DataBaseController.addUser(Login, Salt, Pwd);
        UserTable.setItems(DataBaseController.GetUsersTable());
    }

    @FXML
    private void handleUserDeleteButtonAction(ActionEvent event) throws IOException {
        String Salt = UserSaltField.getText();
        String Login = UserLoginField.getText();
        String Pwd = UserPwdField.getText();
        UserLoginField.setText("");
        UserPwdField.setText("");

        DataUser ud = (DataUser) UserTable.getSelectionModel().getSelectedItem();


        DataBaseController.deleteUser(ud.getLogin(), ud.getHashpwd());
        UserTable.setItems(DataBaseController.GetUsersTable());
    }

    @FXML
    private void handleUserAlterButtonAction(ActionEvent event) throws IOException {
        String Salt = UserSaltField.getText();
        String Login = UserLoginField.getText();
        String Pwd = UserPwdField.getText();
        UserLoginField.setText("");
        UserPwdField.setText("");

        DataUser ud = (DataUser) UserTable.getSelectionModel().getSelectedItem();


        DataBaseController.alterUser(ud.getLogin(), ud.getHashpwd(), Salt, Pwd);
        UserTable.setItems(DataBaseController.GetUsersTable());
    }

    @FXML
    private void handleRoleUpdateButtonAction(ActionEvent event) throws IOException {

       RolesTable.setItems(DataBaseController.GetRolesTable());
    }

    @FXML
    private void handleRoleAddButtonAction(ActionEvent event) throws IOException {
        String name = RoleNameField.getText();

        DataBaseController.addRole(name);
        RolesTable.setItems(DataBaseController.GetRolesTable());
    }

    @FXML
    private void handleRoleDeleteButtonAction(ActionEvent event) throws IOException {

        DataRoles ur = (DataRoles) RolesTable.getSelectionModel().getSelectedItem();

        DataBaseController.deleteRole(ur.getName());
        RolesTable.setItems(DataBaseController.GetRolesTable());
    }

    @FXML
    private void handleRoleAlterButtonAction(ActionEvent event) throws IOException {
        String name = RoleNameField.getText();
        RoleNameField.setText("");

        DataRoles ur = (DataRoles) RolesTable.getSelectionModel().getSelectedItem();

        DataBaseController.alterRole(ur.getName(),name);
        RolesTable.setItems(DataBaseController.GetRolesTable());
    }


    @FXML
    private void handleRuleUpdateButtonAction(ActionEvent event) throws IOException {

        RulesTable.setItems(DataBaseController.GetRulesTable());
    }

    @FXML
    private void handleRuleAddButtonAction(ActionEvent event) throws IOException {
        String user = RulesUserField.getText();
        String role = RulesRoleField.getText();
        RulesUserField.setText("");
        RulesRoleField.setText("");

        DataBaseController.addRule(user, role);
        RulesTable.setItems(DataBaseController.GetRulesTable());
    }

    @FXML
    private void handleRuleDeleteButtonAction(ActionEvent event) throws IOException {

        DataRule dr = (DataRule) RulesTable.getSelectionModel().getSelectedItem();

        DataBaseController.deleteRule(dr.getUser(), dr.getRole());
        RulesTable.setItems(DataBaseController.GetRulesTable());
    }


    @FXML
    private void handleAuthUpdateButtonAction(ActionEvent event) throws IOException {

        AuthTable.setItems(DataBaseController.GetAuthTable());
    }

    @FXML
    private void handleLogUpdateButtonAction(ActionEvent event) throws IOException {
        String action = LogActionField.getText();
        String username = LogUserField.getText();
        LocalDate ltStart = LogBeginDate.getValue();
        LocalDate ltEnd = LogEndDate.getValue();

        String TimeStart = null;
        String TimeEnd = null;
        if(ltStart != null){TimeStart = ltStart.toString();}
        if(ltEnd != null){TimeEnd = ltEnd.toString();}


        LogTable.setItems(DataBaseController.GetLogTable(action,username,TimeStart,TimeEnd));
    }

}
