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
            getInput(t); //this seems like a really bad idea, this class should have 1 job
        }
    }

    public void render(Graphics g){
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
        for(Entity e : handler.getBattle().getEntityManager().getEntities()){
            if(e.isSelected()){
                updateSemiHiTiles(t);

            }

            t.setOccupied(t.getHitBox().contains(e.getHitBox()));
        }
    }

    private void mouseBooleanInput(Tile t){

        t.setHovering(false);

        if(handler.getController().getHitBox().intersects(t.getHitBox())){ //this should be passed in
            t.setHovering(true);
            if(handler.getController().isLeftPressed()){
                for(Tile y : tiles){
                    y.setSelected(false); //this is called in a loop, then we loop again, seems wasteful
                }
                t.setSelected(true);
            }
        }
    }


    private void updateSemiHiTiles(Tile t){
        if(t.isSemiHighlited() && !semiHiTiles.contains(t)){ //consider a set instead of a list to remove duplicates
            semiHiTiles.add(t);
            return;
        }

        if(!t.isSemiHighlited() && semiHiTiles.contains(t)){
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
