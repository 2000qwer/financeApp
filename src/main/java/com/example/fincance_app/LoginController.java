package com.example.fincance_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginController extends DBConnect  {

    private static String usernameStatic;

    private AppContext appContext = AppContext.getInstance();

    public static String getUsernameStatic() {
        return usernameStatic;
    }

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private HBox scenePene;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public String Login(ActionEvent event) throws IOException {
        try {

            DBConnect conn = new DBConnect();

            Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            ResultSet Username = statement1.executeQuery("select username from javafx_users.users");
            ResultSet Password = statement2.executeQuery("select password from javafx_users.users");

            while (true) {

                while (Username.next() && Password.next()) {

                    if (tf_username.getText().equals(Username.getString("username")) && tf_password.getText().equals(Password.getString("password"))) {
                        appContext.setUsername(tf_username.getText());
                        root = FXMLLoader.load(getClass().getResource("loggedIn.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        Username.close();
                        Password.close();
                        connection.close();

                        return "";

                    }

                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("login error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong password or username");

                alert.showAndWait();
                break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    public void registration(ActionEvent event) throws IOException {

            root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    public void Quit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setContentText("Do you want to Quit?");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePene.getScene().getWindow();
            stage.close();

        }

    }

}