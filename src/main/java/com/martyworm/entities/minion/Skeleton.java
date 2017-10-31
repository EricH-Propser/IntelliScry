package com.martyworm.entities.minion;

import com.martyworm.Handler.Handler;
import com.martyworm.entities.Entity;
import com.martyworm.gfx.Animation;
import com.martyworm.gfx.Assets;

public class Skeleton extends Minion {

    public Skeleton(Handler handler, int id) {
        super(handler, id, Assets.skeleton_idle);

        this.id = 9;

        animIdle = new Animation(250, Assets.skeleton_idle);
        animAttack = new Animation(250, Assets.skeleton_idle);

        xMove = 0;
        yMove = 0;

        this.width = Entity.DEFAULT_ENTITY_WIDTH;
        this.height = Entity.DEFAULT_ENTITY_HEIGHT;
    }

}