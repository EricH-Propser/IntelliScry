package com.martyworm.Handler;


import com.martyworm.Battle.Battle;
import com.martyworm.game.Game;
import com.martyworm.gui.MouseController;

import java.awt.event.MouseEvent;

public class Handler {

    private Game game;
    private Battle battle;

    public Handler(Game game){
        this.game = game;
    }

    public void onMouseMove(MouseEvent e){
        if(battle != null){
            battle.onMouseMove(e);
        }
    }

    public void onLeftMouseRelease(MouseEvent e){
        if(battle != null){
            battle.onLeftMouseRelease(e);
        }
    }
    public void onRightMouseRelease(MouseEvent e){
        if(battle != null){
            battle.onRightMouseRelease(e);
        }
    }


    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public MouseController getMouseController(){
        return game.getMouseController();
    }
}
