package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class GuestHandler implements IClientHandler {
    public HostManager HM;
    Service service=new Service();
    Boolean stop=false;
    public MyHostServer host;

    public GuestHandler(int serverPort) {
        this.HM=new HostManager(serverPort);
    }
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        Scanner in = new Scanner(inFromclient); // remove the letter
         while(in.hasNext()){
             System.out.println("line 28\n");
             if (in.hasNextLine()) {
                 System.out.println("line 29\n");
                 try {
                     System.out.println("line-server\n");//TODO
                     String line = in.nextLine();
                     System.out.println("server " + line);
                     //System.out.println(line);//TODO
                     String[] lineAsList = line.split("-");
                     String playerName = lineAsList[0];
                     String key = lineAsList[1];
                     if (key.equals("joinToGame")) {
                         if (HM.addPlayerToGame(lineAsList[0])) {
                             HM.setPlayerScore(0,playerName);//TODO:ask Shira
                             HM.setPlayerTiles("",playerName);
                             //TODO:save to DB
                             //host.notifyAll(playerName + "-" + "message- YOU JOIN GAME:) PLEASE WAIT THE GAME STARTED BY HOST- ");
                             System.out.println("server " + playerName + "-" + "joined to game");
                         } else {
                             //host.notifyAll(playerName + "-" + "message-you not joind to game- ");


                         }
                     }
//                     if (key.equals("startGame")) {
//                         System.out.println("gameStarted\n"); //TODO shira
//                         HM.current_player = HM.playersList.get(HM.index);
//                         System.out.println("HM.current_player "+ HM.playersList.get(HM.index)); //TODO shira
//                         for (int i = 0; i < HM.playersList.size(); i++) {
//                             ArrayList<Tile> tiles = HM.initTileArray();
//                             String tielsString = "";
//                             for (int j = 0; j < tiles.size(); j++) {
//                                 //tielsString += service.TileToString(tiles.get(j)) + "/";
//                                 tielsString += service.TileToString(tiles.get(j));
//                             }
//                             host.notifyAll(HM.playersList.get(i) + "-initTiles-" + tielsString + "-null");
//                         }
//                         host.notifyAll("board-" + HM.getBoardGame());
//                     }
                     if (playerName.equals(HM.current_player)) {
                         if (key.equals("tryToPlace")) {
                             System.out.println("send word func server \n");//TODO PRINTFORTEST
                             String wordString = service.getWordString(lineAsList[2]);
                             Word word = service.stringToWord(lineAsList[2]);
                             int score = HM.tryPlaceWord(word);
                             HM.setPlayerScore(score,playerName);
                             String fillTiles = "null";
                             if (score > 0) {
                                //int count = wordString.length();
                                fillTiles = HM.fillTilesArray( wordString.length());
                                fillTiles += "/" + wordString;
                                HM.setPlayerTiles(fillTiles,playerName);
                             }
                             host.notifyAll(playerName + "-tryToPlace-" + String.valueOf(score) + "-" + fillTiles);

                             System.out.println("send to everyone the board");
                             System.out.println("total score - "+HM.scoreMap.get(playerName)+"-null");
                             host.notifyAll(playerName + "-totalScore-"+ HM.scoreMap.get(playerName)+"-null");
                             host.notifyAll("board-" + HM.getBoardGame());
                             HM.nextPlayer();
                             host.notifyAll("turn-" + "TURN OF:"+HM.current_player);
                             //host.notifyAll("message-" + "The current turn is of " + HM.current_player);
                         }
                         if (key.equals("getTileFromBag")) {
                             Tile t = HM.getRand();
                             String s = service.TileToString(t);
                             host.notifyAll(playerName + "-getTileFromBag-" + s + "- ");
                         }
                     } else if (!key.equals("startGame") && !key.equals("joinToGame"))
                         host.notifyAll(playerName + "-" + "message-NOT YOUR TURN, PLEASE WAIT TO YOUR TURN- ");

                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             }
        }
    }
public void tryToPlaceForView(){

}
    @Override
  public void close()  {
        //in.close();
        //out.close();
    }
}



