package intellij.board;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import intellij.Handler.Handler;
import intellij.board.Tile;
import intellij.game.Game;

// Class just initializes the board but doesn't really hold any value of it's own
public class Board {


    private Handler handler;

    public Board(Handler handler){
        this.handler = handler;

    }


    public static ArrayList<Tile> initBoard(ArrayList<Tile> tiles){
        //takes game's arraylist

        String board = "";

//      For .jar creation purposes
      //String board = "222222222222222222222200000000222000011112200200000222000000012200200000222000000012200200000000000000012200200000222000000002200222000220000000002200000000000000000002200000000000000000002200000000022000222002200000000222000002002210000000000000002002210000000222000002002210000000222000002002211110000222000000002222222222222222222222";

        //adds loadBoard's string "2222211100..." to a local string in this method
        board += loadBoard();

        //takes an array list argument and add's all the numbers in the string to it as tiles
        for (int i = 0; i < board.length(); i++){
            char c = board.charAt(i);
            if(Character.isDigit(board.charAt(i))){
                if(c == '2'){
                    tiles.add(new RockTile(i));
                }else if(c == '0'){
                    tiles.add(new GrassTile(i));
                }else if(c == '1'){
                    tiles.add(new DirtTile(i));
                }
            }
        }

        int newRow = 40;
        int tileNum = 0;

        //officially sets the x and y position of each tile in order, and assigns a unique ID from 0 - 335
        for(int x = 0; x < 336; x++){
            if(x % 21 == 0){
                newRow += 40;
                tileNum = 0;
            }
            tiles.get(x).setPosition(350 + (tileNum * Tile.TILEWIDTH), 45 + newRow + Tile.TILEHEIGHT);
            tileNum++;
        }


        return tiles;

    }

    //loads the board from my res/worlds folder and draws tiles based on the 0s, 1, and 2,s
    public static String loadBoard(){

        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader("res/worlds/world3.txt"));
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








}




