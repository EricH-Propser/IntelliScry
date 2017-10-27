package com.martyworm.board;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.martyworm.entities.Entity;
import com.martyworm.game.Game;

public class TileManager {

    private Game game;
    private Tile tile;
    private List<Tile> tiles;
    private List<Tile> semiHiTiles;

    //Making sure only one tile can be selected
    private Tile selectedTile;

    private Comparator<Tile> renderSorter = new Comparator<Tile>(){
        @Override
        public int compare(Tile a, Tile b){
            if(a.getxPos() + a.getHitBox().height < b.getyPos() + b.getHitBox().height){
                return -1;
            }
            else return 1;
        }
    };


    public TileManager(Game game){
        this.game = game;
        semiHiTiles = new ArrayList<Tile>();
        tiles = game.getTiles();
    }

    public void tick(){
        //game calls this tick which in turn ticks each tile
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
        for(Entity e : game.getEntityManager().getEntities()){
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
        if(game.getController().getHitBox().intersects(t.getHitBox())){//Hovering check could be questionable
            t.setHovering(true);
            if(game.getController().isLeftPressed()){//if mouse left clicked
                for(Tile y : tiles){
                    y.setSelected(false);
                }
                t.setSelected(true);
            }
        }else{
            t.setHovering(false);
        }
    }


    public void updateSemiHiTiles(Tile t){
        if(t.isSemiHighlited() && !semiHiTiles.contains(t)){
            semiHiTiles.add(t);
        }

        else if(!t.isSemiHighlited() && semiHiTiles.contains(t)){
            semiHiTiles.remove(t);
        }

    }


    //Getters & Setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Tile getTile() {
        return tile;
    }

    public void setMinion(Tile tile) {
        this.tile = tile;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setEntities(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getSemiHiTiles() {
        return semiHiTiles;
    }

    public void setSemiHiTiles(ArrayList<Tile> semiHiTiles) {
        this.semiHiTiles = semiHiTiles;
    }
}
