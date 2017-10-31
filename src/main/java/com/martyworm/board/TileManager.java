package com.martyworm.board;

import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;
import com.martyworm.entities.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class TileManager {

    private Handler handler;
    private List<Tile> tiles;
    private ArrayList<Tile> semiHiTiles;


    public TileManager(Handler handler, List<Tile> boardTiles){
        this.handler = handler;
        semiHiTiles = new ArrayList<>();
        this.tiles = boardTiles;
    }

    public void tick(){
        for(Tile t : tiles){
            t.tick();
            getInput(t);
        }
    }

    public void render(Graphics g){

        //game calls this render which in turn renders each tile
        for(Tile t : tiles){
            t.render(g);
        }
    }

    private void getInput(Tile t){
        entityBooleanInput(t);
        mouseBooleanInput(t);

        //While a tile is occupied it shouldnt be semiHighlighted or selected
        if(t.isOccupied()){
            t.setSelected(false);
            t.setSemiHighlited(false);
        }
        if(semiHiTiles.isEmpty()){
            t.setSemiHighlited(false);
        }
    }


    public Tile getSelectedTile(){
        //need to make sure not null when calling
        for(Tile t : tiles){
            if(t.isSelected() && !t.isOccupied()){
                return t;
            }
        }
        return null;
    }

    private void entityBooleanInput(Tile t){
        //For all the entities in the game
        for(Entity e : handler.getBattle().getEntityManager().getEntities()){
            if(e.isSelected()){
                updateSemiHiTiles(t);

            }
            //if tile's hitbox contains an entity hitbox then set tile to occupied
            if(t.getHitBox().contains(e.getHitBox())){
                t.setOccupied(true);
            }

            //default is to set occupied to false
            else{
                t.setOccupied(false);
            }

        }
    }

    private void mouseBooleanInput(Tile t){
        if(handler.getController().getHitBox().intersects(t.getHitBox())){//Hovering check could be questionable
            t.setHovering(true);
            if(handler.getController().isLeftPressed()){//if mouse left clicked
                for(Tile y : tiles){
                    y.setSelected(false);
                }
                t.setSelected(true);
            }
        }else{
            t.setHovering(false);
        }
    }


    private void updateSemiHiTiles(Tile t){
        if(t.isSemiHighlited() && !semiHiTiles.contains(t)){
            semiHiTiles.add(t);
        }

        else if(!t.isSemiHighlited() && semiHiTiles.contains(t)){
            semiHiTiles.remove(t);
        }

    }


    //Getters & Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Tile> getSemiHiTiles() {
        return semiHiTiles;
    }

}
