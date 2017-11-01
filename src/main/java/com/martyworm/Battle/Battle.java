package com.martyworm.Battle;



import com.martyworm.Handler.Handler;
import com.martyworm.Player.Player;
import com.martyworm.board.Board;
import com.martyworm.board.exceptions.LoadingException;
import com.martyworm.board.tiles.Tile;
import com.martyworm.board.TileManager;
import com.martyworm.cards.individualCards.CardRedDragon;
import com.martyworm.cards.individualCards.CardSkeleton;
import com.martyworm.entities.Entity;
import com.martyworm.entities.EntityManager;
import com.martyworm.gfx.Assets;
import com.martyworm.ui.ClickListener;
import com.martyworm.ui.UIImageButton;
import com.martyworm.ui.UIManager;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Battle {

    private Handler handler;
    private int width, height;

    //Entities
    private EntityManager entityManager;
    private ArrayList<Entity> entities;

    //Board
    private Board board;
    //private List<Tile> tiles;
    private TileManager tileManager;

    //Players
    private Player player1;
    private Player player2;

    //UIManager
    private UIManager uiManager;

    public Battle(Handler handler) throws LoadingException{
        this.handler = handler;

        this.board = new Board(handler);

        uiManager = new UIManager(handler);


        entityManager = new EntityManager(handler);
        player1 = new Player(handler, 1);
        player2 = new Player(handler, 2);

        this.entities = entityManager.getEntities();


        //initialize board
        String boardData = Board.loadBoardFile("worlds/world3.txt");
        List<Tile> tiles = Board.loadTiles(boardData);
        tileManager = new TileManager(handler, tiles);
        handler.getController().setTileManager(tileManager);

        uiManager.addObject(new UIImageButton(1340, 840, 128, 64, Assets.endTurnButton, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("clicked end turn");
                passTurn();
                System.out.println("player 1's turn? " + player1.isTurn() + " .. player 2's turn? " + player2.isTurn());
            }
        }));


        for(int i = 0; i < 25; i++){
            player1.getCardManager().addCardToDeck(new CardRedDragon(handler, 1, 1));
            player1.getCardManager().addCardToDeck(new CardSkeleton(handler, 9, 1));
            player2.getCardManager().addCardToDeck(new CardRedDragon(handler, 1, 2));
            player2.getCardManager().addCardToDeck(new CardSkeleton(handler, 9, 2));
        }
        player1.getCardManager().shuffle();
        player1.getCardManager().deal(7);
        player2.getCardManager().shuffle();
        player2.getCardManager().deal(7);
        player1.setTurn(true);


    }


    public void tick(){
        uiManager.tick();
        tileManager.tick();
        entityManager.tick();
        player1.tick();
        player2.tick();

    }

    public void render(Graphics g){

        //Render Background Image
        g.drawImage(Assets.backgroundImage, 0, 0, null);
        g.drawImage(Assets.guiOverlay, 0, 0, null);

        //UI
        uiManager.render(g);
        //Tiles
        tileManager.render(g);
        //Entities
        entityManager.render(g);
        //Players & Their decks
        player1.render(g);
        player2.render(g);

    }


    public Tile getTile(int tileId){
        //Need to make sure not null when calling this
        if (tileId < 0 || tileId >= tileManager.getTiles().size()) {
            return null;
        }

        return tileManager.getTiles().get(tileId);
    }

    private void passTurn(){
        player1.setTurn(!player1.isTurn());
        player2.setTurn(!player2.isTurn());
    }

    public Player getCurrentPlayer(){
        if(player1.isTurn()) return player1;
        else return player2;
    }

    public Tile getSelectedTile(){
        if(tileManager.getSelectedTile() != null) {
            return tileManager.getSelectedTile();
        }
        return null;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public Player getPlayer1() {
        return player1;
    }
}
