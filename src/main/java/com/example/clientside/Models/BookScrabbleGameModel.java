package com.example.clientside.Models;

import java.util.ArrayList;

public class BookScrabbleGameModel {
    ArrayList<PlayerModel> gamePlayers;
    BoardModel board_game = new BoardModel();

    public BookScrabbleGameModel(ArrayList<PlayerModel> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    //TODO:
    // loop on all players and get from each player- tiles list, score, name and then the player communicate with the "gameServer"
    // if this is the host turn - communicate with my gameServer
    // if this is the guest- communicate with the host which commiunicte with the gameServer in the background
    public void start() {
    }

}