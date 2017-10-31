package com.martyworm.cards.individualCards;


import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;
import com.martyworm.cards.MinionCard;
import com.martyworm.entities.minion.Minion;
import com.martyworm.entities.minion.RedDragon;
import com.martyworm.gfx.Assets;
import com.martyworm.ui.ClickListener;

public class CardRedDragon extends MinionCard {

    private ClickListener clicker;


    public CardRedDragon(Handler handler, int id, int playerNumber){
        super(handler, Assets.card_redDragon, id, playerNumber);



        this.setMana(3);

        this.mana = 3;
        this.movements = 4;
        this.spells = 2;
        this.health = 30;

    }

    @Override
    public void cast(Tile t){
        if(manaCheck(handler.getBattle().getCurrentPlayer())) {

            Minion m = handler.getBattle().getEntityManager().addMinion(new RedDragon(handler, 1));
            handler.getBattle().getEntityManager().addEntity(m);
            m.setXPos(t.getxPos());
            m.setYPos(t.getyPos());

            setInHand(false);
            setInActive(true);
            handler.getBattle().getCurrentPlayer().subtractMana(this.mana);

        }
    }

    @Override
    public void onClick() {
        turnTilesRed(playerNumber);
    }
    @Override
    protected void onDeselect() {
        turnRedTilesOff(playerNumber);
    }


}
