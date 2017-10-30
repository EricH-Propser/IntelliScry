package intellij.cards.individualCards;

import intellij.Handler.Handler;
import intellij.board.Tile;
import intellij.cards.MinionCard;
import intellij.entities.Entity;
import intellij.entities.minion.Minion;
import intellij.entities.minion.RedDragon;
import intellij.gfx.Assets;
import intellij.ui.ClickListener;

import java.awt.event.MouseEvent;

public class CardSkeleton extends MinionCard {

    private ClickListener clicker;


    public CardSkeleton(Handler handler, int id){
        super(handler, Assets.card_Skeleton, id);

        this.setManaCost(2);

        this.mana = 2;
        this.movements = 4;
        this.spells = 1;
        this.health = 20;
    }

    @Override
    public void cast(Tile t){
        Entity m = handler.getBattle().getEntityManager().addMinion(new RedDragon(handler, 1));
        m.setXPos(t.getxPos());
        m.setYPos(t.getyPos());
    }

    @Override
    public void onClick(){
        casted = true;
    }


    @Override
    public void turnTilesRed(){
        if(!handler.getBattle().getTile(232).isOccupied()){
            handler.getBattle().getTile(232).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(253).isOccupied()) {
            handler.getBattle().getTile(253).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(274).isOccupied()) {
            handler.getBattle().getTile(274).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(295).isOccupied()) {
            handler.getBattle().getTile(295).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(296).isOccupied()) {
            handler.getBattle().getTile(296).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(297).isOccupied()) {
            handler.getBattle().getTile(297).setRedHighlight(true);
        }
        if(!handler.getBattle().getTile(298).isOccupied()) {
            handler.getBattle().getTile(298).setRedHighlight(true);
        }
    }

    @Override
    public void turnRedTilesOff(){
        handler.getBattle().getTile(232).setRedHighlight(false);
        handler.getBattle().getTile(253).setRedHighlight(false);
        handler.getBattle().getTile(274).setRedHighlight(false);
        handler.getBattle().getTile(295).setRedHighlight(false);
        handler.getBattle().getTile(296).setRedHighlight(false);
        handler.getBattle().getTile(297).setRedHighlight(false);
        handler.getBattle().getTile(298).setRedHighlight(false);

    }
}
