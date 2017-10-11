package intellij.board;

import java.awt.image.BufferedImage;

import intellij.gfx.Assets;
import intellij.game.*;

public class RockTile extends Tile{

    public RockTile(int id) {
        super(id, Assets.rockTile2);

    }

    @Override
    public boolean isSolid(){
        return true;
    }

}