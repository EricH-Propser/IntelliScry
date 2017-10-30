package com.martyworm.cards.individualCards;


import com.martyworm.Handler.Handler;
import com.martyworm.board.Tile;
import com.martyworm.cards.MinionCard;
import com.martyworm.entities.Entity;
import com.martyworm.entities.minion.Minion;
import com.martyworm.entities.minion.RedDragon;
import com.martyworm.entities.minion.Skeleton;
import com.martyworm.gfx.Assets;
import com.martyworm.ui.ClickListener;

public class CardSkeleton extends MinionCard {

    private ClickListener clicker;


    public CardSkeleton(Handler handler, int id, int playerNumber){
        super(handler, Assets.card_Skeleton, id, playerNumber);

        this.setMana(2);

        this.mana = 2;
        this.movements = 4;
        this.spells = 1;
        this.health = 20;
    }

    @Override
    public void cast(Tile t){

        if(manaCheck(handler.getBattle().getCurrentPlayer())) {

            Minion m = handler.getBattle().getEntityManager().addMinion(new Skeleton(handler, 2));
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
