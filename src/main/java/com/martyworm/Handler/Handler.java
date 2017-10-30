package com.martyworm.Handler;


import com.martyworm.Battle.Battle;
import com.martyworm.game.Game;
import com.martyworm.gui.Controller;

public class Handler {

    private Game game;
    private Battle battle;

    public Handler(Game game){
        this.game = game;
    }

    public Controller getController(){
        return game.getController();
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

    public void setGame(Game game) {
        this.game = game;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

}
