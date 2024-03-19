package com.example.fincance_app;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class user {
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty  price = new SimpleIntegerProperty();
    private final StringProperty  date  = new SimpleStringProperty();

    public String getCategory(){
        return category.get();

    }

    public void setCategory(String value){
        category.set(value);

    }

    public StringProperty categoryProperty(){
        return category;
    }

    public String getName(){
        return name.get();

    }

    public void setName(String value){
        name.set(value);

    }

    public StringProperty nameProperty(){
        return name;
    }

    public int getPrice(){
        return price.get();

    }

    public void setPrice(int value){
        price.set(value);

    }

    public IntegerProperty priceProperty(){
        return price;
    }

    public String getDate(){
        return date.get();

    }

    public void setDate(String value){
        date.set(value);

    }

    public StringProperty dateProperty(){
        return date;
    }

}