package com.dbInterface.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.dbInterface.System.DataBaseController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML //  fx:Role="myButton"
    private Button connectButton; // Value injected by FXMLLoader

    @FXML
    private TextField serverField;


    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text ResultText;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert connectButton != null : "fx:Role=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        String ErrStr = DataBaseController.Connect(serverField.getText(),loginField.getText(),passwordField.getText());
        ResultText.setText(ErrStr);

        Stage stage;
        Parent root;



        if(ErrStr.equals("Success"))    {

            String fxmlFile = "/fxml/MainWidnow.fxml";
            FXMLLoader loader = new FXMLLoader();
            root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
            stage=(Stage) ResultText.getScene().getWindow();

            stage.setTitle("DB Interface");
            stage.setScene(new Scene(root));
            stage.show();

        }
    }

}
