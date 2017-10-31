package com.martyworm.Player;


import com.martyworm.Handler.Handler;
import com.martyworm.cards.CardManager;
import com.martyworm.entities.EntityManager;
import com.martyworm.gfx.Assets;

import java.awt.Graphics;

public class Player {

    private Handler handler;

    //Cards
    private CardManager cardManager;

    //Entities
    private EntityManager entityManager;

    //Mana
    private static final int PLAYER1_MANA_DISPLAY_SPOT_X = 154;
    private static final int PLAYER1_MANA_DISPLAY_SPOT_Y = 848;
    private static final int PLAYER2_MANA_DISPLAY_SPOT_X = 154;
    private static final int PLAYER2_MANA_DISPLAY_SPOT_Y = 45;

    private int mana;

    private int id;


    private boolean turn;

    public Player(Handler handler, int id){

        this.handler = handler;
        this.id = id;

        this.cardManager = new CardManager(handler, this);
//        handler.getMouseController().setCardManager(cardManager);
        this.mana = 5;

    }

    public void tick(){

        cardManager.tick();
        if(turn){
            handler.getController().setCardManager(cardManager);
        }
    }

    public void render(Graphics g) {
        cardManager.render(g);
        if(id == 1) {
            for (int i = 0; i < 6; i++) {
                if (mana == i) {
                    g.drawImage(Assets.manaBubble[i], PLAYER1_MANA_DISPLAY_SPOT_X, PLAYER1_MANA_DISPLAY_SPOT_Y, null);
                }
            }
        }else if (id == 2){
            for (int i = 0; i < 6; i++) {
                if (mana == i) {
                    g.drawImage(Assets.manaBubble[i], PLAYER2_MANA_DISPLAY_SPOT_X, PLAYER2_MANA_DISPLAY_SPOT_Y, null);
                }
            }
        }
    }



    //GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void subtractMana(int num){
        if(this.mana - num >= 0) {
            this.mana -= num;
        }
    }
}