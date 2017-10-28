package com.martyworm.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BoardTests {

    @Test
    public void shouldLoadAllTiles(){
        String test = "012";
        try {
            List<Tile> tiles = Board.initBoard(test);

           assertTrue(tiles.get(0) instanceof GrassTile);
           assertTrue(tiles.get(1) instanceof DirtTile);
           assertTrue(tiles.get(2) instanceof RockTile);

        } catch(LoadingException le){
            Assert.fail(le.getMessage());
        }
    }


    @Test(expected = LoadingException.class)
    public void shouldThrowExceptionOnUnsupportedCharacter() throws LoadingException {
        String test = "01A";
        List<Tile> tiles = Board.initBoard(test);
    }
}