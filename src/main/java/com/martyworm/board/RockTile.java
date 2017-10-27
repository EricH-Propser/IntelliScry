package com.martyworm.board;

import com.martyworm.gfx.Assets;

public class RockTile extends Tile{

    public RockTile(int id) {
        super(id, Assets.rockTile2);

    }

    @Override
    public boolean isSolid(){
        return true;
    }

}