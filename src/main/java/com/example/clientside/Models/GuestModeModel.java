package com.example.clientside.Models;

public class GuestModeModel extends PlayerModel{
    public GuestModeModel(int serverPort){
        super();
        this.serverPort=serverPort;
        connectServer();

    }

}
