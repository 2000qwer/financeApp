package com.example.fincance_app;

public final class AppContext {

    private static AppContext INSTANCE;
    private String username = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private AppContext() {

    }

    public static AppContext getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AppContext();
        }

        return INSTANCE;
    }

}