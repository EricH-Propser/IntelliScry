package intellij.cards.individualCards;

import intellij.Handler.Handler;
import intellij.board.Tile;
import intellij.cards.MinionCard;
import intellij.entities.Entity;
import intellij.entities.minion.Minion;
import intellij.entities.minion.RedDragon;
import intellij.gfx.Assets;
import intellij.ui.ClickListener;
import intellij.ui.UIImageButton;

import java.awt.event.MouseEvent;

public class CardRedDragon extends MinionCard {

    private ClickListener clicker;


    public CardRedDragon(Handler handler, int id){
        super(handler, Assets.card_redDragon, id);

        this.mana = 3;
        this.movements = 4;
        this.spells = 2;
        this.health = 30;

    }

    @Override
    public void cast(Tile t){

        Minion m = handler.getBattle().getEntityManager().addMinion(new RedDragon(handler, 1));
        handler.getBattle().getEntityManager().addEntity(m);
        m.setXPos(t.getxPos());
        m.setYPos(t.getyPos());

        setInHand(false);
        setInActive(true);
    }

    @Override
    public void onClick() {
        turnTilesRed();
    }
    @Override
    protected void onDeselect() {
        turnRedTilesOff();
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
