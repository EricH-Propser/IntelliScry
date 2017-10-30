package com.martyworm.Battle;



import com.martyworm.Handler.Handler;
import com.martyworm.Player.Player;
import com.martyworm.board.Board;
import com.martyworm.board.Tile;
import com.martyworm.board.TileManager;
import com.martyworm.cards.individualCards.CardRedDragon;
import com.martyworm.entities.Entity;
import com.martyworm.entities.EntityManager;
import com.martyworm.gfx.Assets;
import com.martyworm.ui.ClickListener;
import com.martyworm.ui.UIImageButton;
import com.martyworm.ui.UIManager;

import java.awt.Graphics;
import java.util.ArrayList;

public class Battle {

    private Handler handler;
    private int width, height;

    //Entities
    private EntityManager entityManager;
    private ArrayList<Entity> entities;

    //Board
    private Board board;
    private ArrayList<Tile> tiles;
    private TileManager tileManager;

    //Players
    private Player player1;
    private Player player2;

    //UIManager
    private UIManager uiManager;

    public Battle(Handler handler){
        this.handler = handler;

        this.board = new Board(handler);

        uiManager = new UIManager(handler);

        tileManager = new TileManager(handler);
        entityManager = new EntityManager(handler);
        player1 = new Player(handler, 1);

        this.tiles = tileManager.getTiles();
        this.entities = entityManager.getEntities();


        //initialize board
        Board.initBoard(this.tiles);

        uiManager.addObject(new UIImageButton(1340, 840, 128, 64, Assets.endTurnButton, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("clicked end turn");

            }
        }));


        //Will be refactored eventually
//        entityManager.addEntity(new RedDragon(handler, 1));
//        entityManager.addEntity(new RedDragon(handler, 2));
//        entityManager.addEntity(new RedDragon(handler, 3));




//        for(Entity e : entities) {
//            if (e.getId() == 1) {
//                e.setXPos(getTile(253).getxPos());
//                e.setYPos(getTile(253).getyPos());
//
//            }
//            if (e.getId() == 2) {
//                e.setXPos(getTile(185).getxPos());
//                e.setYPos(getTile(185).getyPos());
//            }
//            if (e.getId() == 3) {
//                e.setXPos(getTile(52).getxPos());
//                e.setYPos(getTile(52).getyPos());
//            }
//        }

        for(int i = 0; i < 25; i++){
            //player1.getCardManager().addCardToDeck(new CardSkeleton(handler, i));
            player1.getCardManager().addCardToDeck(new CardRedDragon(handler, i));
        }
        player1.getCardManager().shuffle();
        player1.getCardManager().deal(3);


    }


        public void tick(){

            tileManager.tick();
            uiManager.tick();
            player1.tick();
            entityManager.tick();

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

        }

//        private void takeTurn(Player player){
//            if(player.isTurn()){
//                player.takeTurn();
//            }
//        }


    public Tile getTile(int tileId){
        //Need to make sure not null when calling this
        if (tileId < 0 || tileId >= tiles.size()) {
            return null;
        }else{
            return tiles.get(tileId);

        }

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

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
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
}
