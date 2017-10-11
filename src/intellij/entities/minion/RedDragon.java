package intellij.entities.minion;

import intellij.entities.Entity;
import intellij.game.Game;
import intellij.gfx.Animation;
import intellij.gfx.Assets;

public class RedDragon extends Minion{

    public RedDragon(Game game, int id) {
        super(game, id, Assets.dragon_idle);

        this.id = id;

        animIdle = new Animation(250, Assets.dragon_idle);
        animAttack = new Animation(250, Assets.dragon_attack);

        xMove = 0;
        yMove = 0;

        this.width = Entity.DEFAULT_ENTITY_WIDTH;
        this.height = Entity.DEFAULT_ENTITY_HEIGHT;
    }

}
