package com.example.fincance_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class signUpController extends DBConnect {
    @FXML
    private TextField r_tf_username;
    @FXML
    private PasswordField r_tf_password;
    @FXML
    private PasswordField confirm_f_tf_password;
    @FXML
    private Button changeToLoginButton;

    private static final int MAX_LENGTH = 14;
    private static final int MIN_LENGTH = 8;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void registrationDB(ActionEvent event) throws SQLException, IOException {

        if (r_tf_username.getText().length() < MIN_LENGTH || r_tf_username.getText().length() > MAX_LENGTH) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Username");
            alert.setHeaderText(null);
            alert.setContentText("Your username must contain at least 8 characters (and up to 16 characters)!");

            alert.showAndWait();

        } else if (r_tf_password.getText().length() < MIN_LENGTH || r_tf_password.getText().length() > MAX_LENGTH) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password");
            alert.setHeaderText(null);
            alert.setContentText("Your password must contain at least 8 characters (and up to 16 characters)!");

            alert.showAndWait();

        } else if (r_tf_password.getText().equals(confirm_f_tf_password.getText()) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");

            alert.showAndWait();

        } else {

            DBConnect conn = new DBConnect();
            Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);
            PreparedStatement pst = connection.prepareStatement("insert into javafx_users.users (username,password) values (?,?)");

            pst.setString(1, r_tf_username.getText());
            pst.setString(2, r_tf_password.getText());

            pst.executeUpdate();

            pst.close();
            connection.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Complete!");
            alert.setHeaderText(null);
            alert.setContentText("Congratulation! You are now registered");

            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    public void goToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}