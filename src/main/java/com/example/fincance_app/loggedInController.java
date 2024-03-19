package com.example.fincance_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class loggedInController extends LoginController implements Initializable   {

    @FXML
    private Button logoutButton;
    @FXML
    private TableView<user> id_expenses;
    @FXML
    private TableColumn<user, String> id_category;
    @FXML
    private TableColumn<user, String> id_name;
    @FXML
    private TableColumn<user, Integer> id_price;
    @FXML
    private TableColumn<user, String> id_date;

    private AppContext appContext = AppContext.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void Logout(ActionEvent event) throws IOException {
        try {

            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_expense_window(ActionEvent event) throws IOException {
        try {

            root = FXMLLoader.load(getClass().getResource("add_ex.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Connection conn;
    private ObservableList<user> list;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            populateTabelView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void populateTabelView() throws IOException, SQLException {
        list = FXCollections.observableArrayList();

        DBConnect conn = new DBConnect();

        Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);


        Statement show_ex = connection.createStatement();

        LoginController log = new LoginController();

        String login = log.getUsernameStatic();

        String username = appContext.getUsername();

        ResultSet set = show_ex.executeQuery("select   kategoria,nazwa,kwota,data_wydatku   from javafx_users.users u natural join javafx_users.expenses e where e.username ='"
                +username
                +"';");

        while (set.next()) {

            user users = new user();

            users.setCategory(set.getString("kategoria"));
            users.setName(set.getString("nazwa"));
            users.setPrice(set.getInt("kwota"));
            users.setDate(set.getString("data_wydatku"));

            list.add(users);

        }

        id_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        id_expenses.setItems(list);

        connection.close();
        show_ex.close();

    }

    public void maxExpanse(ActionEvent event) throws SQLException {

        list = FXCollections.observableArrayList();

        DBConnect conn = new DBConnect();

        Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);


        Statement show_ex = connection.createStatement();

        LoginController log = new LoginController();

        String login = log.getUsernameStatic();

        String username = appContext.getUsername();

        ResultSet set = show_ex.executeQuery("select  ex.kategoria, ex.nazwa,ex.kwota, ex.data_wydatku from " +
                "javafx_users.expenses ex inner join javafx_users.users u  on ex.username = u.username \n" +
                "where u.username ='" + username
                +
                "'order by ex.kwota desc limit 1;");

        while (set.next()) {

            user users = new user();

            users.setCategory(set.getString("kategoria"));
            users.setName(set.getString("nazwa"));
            users.setPrice(set.getInt("kwota"));
            users.setDate(set.getString("data_wydatku"));

            list.add(users);

        }

        id_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        id_expenses.setItems(list);

        connection.close();
        show_ex.close();

    }

    public void showAll(ActionEvent event) throws SQLException, IOException {
        populateTabelView();
    }

    public void showMin(ActionEvent event) throws SQLException {
        list = FXCollections.observableArrayList();

        DBConnect conn = new DBConnect();

        Connection connection = DriverManager.getConnection(conn.url, conn.user, conn.password);


        Statement show_ex = connection.createStatement();

        LoginController log = new LoginController();

        String login = log.getUsernameStatic();

        String username = appContext.getUsername();

        ResultSet set = show_ex.executeQuery("select  ex.kategoria, ex.nazwa,ex.kwota, ex.data_wydatku from " +
                "javafx_users.expenses ex inner join javafx_users.users u  on ex.username = u.username \n" +
                "where u.username ='" + username
                +
                "'order by ex.kwota limit 1;");

        while (set.next()) {

            user users = new user();

            users.setCategory(set.getString("kategoria"));
            users.setName(set.getString("nazwa"));
            users.setPrice(set.getInt("kwota"));
            users.setDate(set.getString("data_wydatku"));

            list.add(users);

        }

        id_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        id_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        id_expenses.setItems(list);

        connection.close();
        show_ex.close();
    }
}