package com.martyworm.states;

import com.martyworm.Battle.Battle;
import com.martyworm.Handler.Handler;

import java.awt.Graphics;

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