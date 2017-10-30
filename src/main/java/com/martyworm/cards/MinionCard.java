package com.martyworm.cards;

import com.martyworm.Handler.Handler;
import com.martyworm.board.Tile;

import java.awt.image.BufferedImage;

public class MinionCard extends Card {

    protected int mana, movements, spells, health;


    public MinionCard(Handler handler, BufferedImage[] images, int id){
        super(handler, images, id);
    }

    @Override
    public void onClick(){

    }

    @Override
    protected void cast(Tile t){}

    @Override
    public void turnTilesRed(){}

    @Override
    public void turnRedTilesOff(){}
}
