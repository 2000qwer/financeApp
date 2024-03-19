package com.example.fincance_app;

import com.example.fincance_app.AppContext;
import com.example.fincance_app.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Controller_add_expanse {
    @FXML
    private TextField id_category;
    @FXML
    private TextField id_name;
    @FXML
    private TextField id_price;
    @FXML
    private TextField id_date;
    @FXML
    private Button id_add;
    @FXML
    private Button id_back;

    private Stage stage;
    private Scene scene;
    private Parent root;

    AppContext appContext = AppContext.getInstance();

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loggedIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void add(ActionEvent event) throws IOException, SQLException {
        DBConnect conn = new DBConnect();
        Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);
        String username = appContext.getUsername();
        PreparedStatement insertion = connection.prepareStatement("insert into javafx_users.expenses (username,kategoria,nazwa,kwota,data_wydatku) values (?,?,?,?,default) ;");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        insertion.setString(1, username);
        insertion.setString(2, id_category.getText());
        insertion.setString(3, id_name.getText());
        insertion.setInt(4, Integer.parseInt(id_price.getText()));
        //insertion.setDate(5, new java.sql.Date(2013-09-04 13:30:00));

        insertion.executeUpdate();
        insertion.close();
        connection.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Expanse added succesfully");
        alert.showAndWait();
    }

}