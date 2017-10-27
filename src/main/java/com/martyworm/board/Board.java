package com.martyworm.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.martyworm.game.Game;

import javax.swing.text.DefaultTextUI;

// Class just initializes the board but doesn't really hold any value of it's own
public class Board {

    public static final int ROW_HEIGHT = 40;
    public static final int DEFAULT_X_OFFSET = 350;
    public static final int DEFAULT_Y_OFFSET = 45;
    public static final int MAX_TILES_IN_ROW = 21;

    private Game game;

    public Board(Game game){
        this.game = game;

    }


    public static List<Tile> initBoard(String board) throws LoadingException{

        List<Tile> tiles = new ArrayList<>();


//      For .jar creation purposes
      //String board = "222222222222222222222200000000222000011112200200000222000000012200200000222000000012200200000000000000012200200000222000000002200222000220000000002200000000000000000002200000000000000000002200000000022000222002200000000222000002002210000000000000002002210000000222000002002210000000222000002002211110000222000000002222222222222222222222";

        int newRow = ROW_HEIGHT;
        int tileNum = 0;
        //takes an array list argument and add's all the numbers in the string to it as tiles
        for (int i = 0; i < board.length(); i++){
            TileType tt = TileType.fromChar(board.charAt(i));
            if(tt == null){
                throw new LoadingException("Unsupported tile type loading board");
            }
            if(i % MAX_TILES_IN_ROW == 0){
                newRow += ROW_HEIGHT;
                tileNum = 0;
            }
            int xPosition = DEFAULT_X_OFFSET + (tileNum * Tile.TILEWIDTH);
            int yPosition = DEFAULT_Y_OFFSET + newRow + Tile.TILEHEIGHT;

            switch(tt){
                case GrassTile:
                    tiles.add(new GrassTile(i, xPosition, yPosition));
                    break;
                case DirtTile:
                    tiles.add(new DirtTile(i, xPosition, yPosition));
                    break;
                case RockTile:
                    tiles.add(new RockTile(i, xPosition, yPosition));
                    break;
                default:
                    throw new LoadingException("Unsupported tile type loading board");
            }

            tileNum++;
        }

        return tiles;
    }

    //loads the board from my res/worlds folder and draws tiles based on the 0s, 1, and 2,s
    public static String loadBoard(String resourceLocation){

        StringBuilder builder = new StringBuilder();

        try{

            URL url = ClassLoader.getSystemClassLoader().getResource(resourceLocation);
            String filePath = url.getFile();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
                builder.append(line);

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        String board_undone =  builder.toString();

        String board = "";

        for (int i = 0; i < board_undone.length(); i++){
            char c = board_undone.charAt(i);
            if(Character.isDigit(board_undone.charAt(i))){
                board += c;
            }
        }
        //returns a string of numbers like "2222220001111222..." etc
        return board;
    }





    //getters setters
    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }





}




