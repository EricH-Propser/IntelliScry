package com.martyworm.board.tiles;

import com.martyworm.gfx.Assets;

public class RockTile extends Tile{

    public RockTile(int id, int xPosition, int yPosition) {
        super(id, Assets.rockTile2, xPosition, yPosition);

    }

    @Override
    public boolean isSolid(){
        return true;
    }

}