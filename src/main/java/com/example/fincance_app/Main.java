package com.example.fincance_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //if not internet, give alert
    //lista rozwijana kategorii, zarzadzanie kategoriami uzytkownika + kategoria inne
    //rozwijane kategorie, dla kazdego uzytkownika w bazie dac kopie wszystkich kategorii lub
    //jjest 10 wpisow i powstaje tabela wszystkie kategorie raz i tabela z usunietymi i uzyj not in
    //wykresy oraz raportowanie z wyborem kategorii oraz zakres dat: biezacy miesiac, zakres dat oraz jakiego kategorie
    //itext wypluj pdf z raportem
    //sumowanie kategorii
    //wykres kołowy, o kategoriach
    // tabela kategorie: dla uzytkownika
    // delete expanse, kategorie
    //zarzadzanie kategoriami nowe okno
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Finance");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}