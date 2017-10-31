package com.martyworm.entities.minion;

import com.martyworm.Handler.Handler;
import com.martyworm.entities.Entity;
import com.martyworm.gfx.Animation;
import com.martyworm.gfx.Assets;

public class Skeleton extends Minion {

    public Skeleton(Handler handler, int id, int playerId) {
        super(handler, id, Assets.skeleton_idle, playerId);

        this.id = 9;
        this.name = "Skeleton";

        animIdle = new Animation(250, Assets.skeleton_idle);
        animAttack = new Animation(250, Assets.skeleton_idle);

        xMove = 0;
        yMove = 0;

        this.width = Entity.DEFAULT_ENTITY_WIDTH;
        this.height = Entity.DEFAULT_ENTITY_HEIGHT;
    }

}