package com.martyworm.cards;

import com.martyworm.Handler.Handler;
import com.martyworm.board.Tile;

import java.awt.image.BufferedImage;

public class MinionCard extends Card {

    protected int mana, movements, spells, health;


    public MinionCard(Handler handler, BufferedImage[] images, int id, int playerNumber){

        super(handler, images, id, playerNumber);
    }

    @Override
    public void onClick(){
    }

    @Override
    protected void cast(Tile t){}

    @Override
    protected void turnTilesRed(int playerId){
        if(playerId == 1) {
            if (!handler.getBattle().getTile(232).isOccupied()) {
                handler.getBattle().getTile(232).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(253).isOccupied()) {
                handler.getBattle().getTile(253).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(274).isOccupied()) {
                handler.getBattle().getTile(274).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(295).isOccupied()) {
                handler.getBattle().getTile(295).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(296).isOccupied()) {
                handler.getBattle().getTile(296).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(297).isOccupied()) {
                handler.getBattle().getTile(297).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(298).isOccupied()) {
                handler.getBattle().getTile(298).setRedHighlight(true);
            }
        }else if(playerId == 2){
            if (!handler.getBattle().getTile(37).isOccupied()) {
                handler.getBattle().getTile(37).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(38).isOccupied()) {
                handler.getBattle().getTile(38).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(39).isOccupied()) {
                handler.getBattle().getTile(39).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(40).isOccupied()) {
                handler.getBattle().getTile(40).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(61).isOccupied()) {
                handler.getBattle().getTile(61).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(82).isOccupied()) {
                handler.getBattle().getTile(82).setRedHighlight(true);
            }
            if (!handler.getBattle().getTile(103).isOccupied()) {
                handler.getBattle().getTile(103).setRedHighlight(true);
            }
        }
    }

    @Override
    protected void turnRedTilesOff(int playerId){
        if(playerId == 1) {
            handler.getBattle().getTile(232).setRedHighlight(false);
            handler.getBattle().getTile(253).setRedHighlight(false);
            handler.getBattle().getTile(274).setRedHighlight(false);
            handler.getBattle().getTile(295).setRedHighlight(false);
            handler.getBattle().getTile(296).setRedHighlight(false);
            handler.getBattle().getTile(297).setRedHighlight(false);
            handler.getBattle().getTile(298).setRedHighlight(false);
        }
        else if(playerId == 2){
            handler.getBattle().getTile(37).setRedHighlight(false);
            handler.getBattle().getTile(38).setRedHighlight(false);
            handler.getBattle().getTile(39).setRedHighlight(false);
            handler.getBattle().getTile(40).setRedHighlight(false);
            handler.getBattle().getTile(61).setRedHighlight(false);
            handler.getBattle().getTile(82).setRedHighlight(false);
            handler.getBattle().getTile(103).setRedHighlight(false);
        }
    }
}
