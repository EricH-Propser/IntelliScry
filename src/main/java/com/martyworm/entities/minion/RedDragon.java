package com.martyworm.entities.minion;


import com.martyworm.Handler.Handler;
import com.martyworm.cards.Card;
import com.martyworm.entities.Entity;
import com.martyworm.gfx.Animation;
import com.martyworm.gfx.Assets;


public class RedDragon extends Minion{

    public RedDragon(Handler handler, int id, int playerId, Card cardThatSummoned) {
        super(handler, id, Assets.dragon_idle, playerId, cardThatSummoned);

        this.id = id;
        this.name = "RedDragon";

        animIdle = new Animation(250, Assets.dragon_idle);
        animAttack = new Animation(250, Assets.dragon_idle);

        xMove = 0;
        yMove = 0;

        this.width = Entity.DEFAULT_ENTITY_WIDTH;
        this.height = Entity.DEFAULT_ENTITY_HEIGHT;
    }

}

