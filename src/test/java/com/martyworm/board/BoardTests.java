package com.martyworm.board;

import com.martyworm.board.exceptions.LoadingException;
import com.martyworm.board.tiles.DirtTile;
import com.martyworm.board.tiles.GrassTile;
import com.martyworm.board.tiles.RockTile;
import com.martyworm.board.tiles.Tile;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoardTests {

    @Test
    public void shouldLoadAllTiles() {
        String test = "012";
        try {
            List<Tile> tiles = Board.loadTiles(test);

            assertTrue(tiles.get(0) instanceof GrassTile);
            assertTrue(tiles.get(1) instanceof DirtTile);
            assertTrue(tiles.get(2) instanceof RockTile);

        } catch (LoadingException le) {
            Assert.fail(le.getMessage());
        }
    }

    @Test(expected = LoadingException.class)
    public void shouldThrowExceptionOnUnsupportedCharacter() throws LoadingException {
        String test = "01A";
        List<Tile> tiles = Board.loadTiles(test);
    }

    @Test
    public void shouldLoadExistingFile() {
        try {
            String boardData = Board.loadBoardFile("test_world.txt");

            assertNotNull(boardData);
            Assert.assertEquals(boardData.length(), 4);

        } catch (LoadingException le) {
            Assert.fail("exception loading test world board");
        }
    }

    @Test(expected = LoadingException.class)
    public void shouldThrowExceptionWithMissingFile() throws LoadingException {
        String boardData = Board.loadBoardFile("NotARealWorld.txt");
    }
}
