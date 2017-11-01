package com.martyworm.board;

import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;
import com.martyworm.entities.Entity;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
            booleansUpdate(t);
        }
    }

    public void render(Graphics g){
        for(Tile t : tiles){
            t.render(g);
        }
    }

    private void booleansUpdate(Tile t){
        entityBooleanInput(t);
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

    public void onMouseMove(MouseEvent e){
        for(Tile t : tiles){
            t.onMouseMove(e);
        }
    }
    public void onLeftMouseRelease(MouseEvent e) {
        for (Tile t : tiles) {
            for (Tile y : tiles) {
                if (y.isHovering()) {
                    t.setSelected(false);
                }
            }
            if (t.isHovering()) {
                t.setSelected(true);
            }
            if (!hoveringOnTile()) {
                t.setSelected(false);
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
    private boolean hoveringOnTile(){
        for(Tile t : tiles){
            if(handler.getController().getHitBox().intersects(t.getHitBox())) {
                return true;
            }
        }
        return false;
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
