package com.example.retrovideogamesystemca2;

public class GamesSystem {
    private static GamesSystem instance;

    public GamesSystem(){

    }
    public static GamesSystem getInstance(){
        if(instance == null){
            instance = new GamesSystem();
        }
        return instance;
    }

    public static void setInstance(GamesSystem system) {
        GamesSystem.instance = system;
    }
}
