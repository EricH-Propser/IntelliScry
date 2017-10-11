package intellij.board;

import java.awt.image.BufferedImage;

import intellij.gfx.Assets;
import intellij.game.*;

// Couldnt decide if I needed to make the background actually a tile or not -- seems I probably don't but this class
// is here anyway as a result
public class BackgroundTile extends Tile{


    public BackgroundTile(int id) {
        super(id, Assets.rockTile2);

    }



}
