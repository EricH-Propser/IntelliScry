package intellij.states;

import intellij.Battle.Battle;
import intellij.Handler.Handler;
import intellij.game.Game;
import intellij.gfx.Assets;
import intellij.ui.ClickListener;
import intellij.ui.UIImageButton;
import intellij.ui.UIManager;

import java.awt.*;

public class GameState extends State {

    private Battle battle;

    public GameState(Handler handler) {
        super(handler);
        battle = new Battle(handler);
        handler.setBattle(battle);

    }

    @Override
    public void tick() {
        battle.tick();
    }

    @Override
    public void render(Graphics g) {
        battle.render(g);
    }

}