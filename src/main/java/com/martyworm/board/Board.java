package com.martyworm.board;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.martyworm.Handler.Handler;
import com.martyworm.board.exceptions.LoadingException;
import com.martyworm.board.tiles.DirtTile;
import com.martyworm.board.tiles.GrassTile;
import com.martyworm.board.tiles.RockTile;
import com.martyworm.board.tiles.Tile;
import com.martyworm.board.tiles.TileType;


// Class just initializes the board but doesn't really hold any value of it's own
public class Board {

    public static final int ROW_HEIGHT = 40;
    public static final int DEFAULT_X_OFFSET = 350;
    public static final int DEFAULT_Y_OFFSET = 45;
    public static final int MAX_TILES_IN_ROW = 21;

    private Handler handler;

    public Board(Handler handler){
        this.handler = handler;

    }


    public static List<Tile> loadTiles(String board) throws LoadingException {

        List<Tile> tiles = new ArrayList<>();

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

            switch(tt) {
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
    public static String loadBoardFile(String resourceLocation) throws LoadingException {

        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;

        try{
            URL url = ClassLoader.getSystemClassLoader().getResource(resourceLocation);
            if(url == null){
                throw new FileNotFoundException();
            }
            //Not sure why this needs to be the full path?
            String filePath = url.getFile();
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                builder.append(line);
            }
        }catch(IOException e){
            throw new LoadingException("Unable to load resource file: " + resourceLocation, e);
        } finally{
            if(br != null){
                try {
                    br.close();
                } catch(IOException e){
                    throw new LoadingException("Unable to close buffered reader: " + resourceLocation, e);
                }
            }
        }

        return  builder.toString().replaceAll("\\s", "");
    }








}




