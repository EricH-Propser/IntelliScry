package com.martyworm.cards;


import com.martyworm.Handler.Handler;
import com.martyworm.Player.Player;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardManager {

    private Handler handler;
    private ArrayList<Card> cards;


    private Player player;

    public CardManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;

        this.cards = new ArrayList<Card>();

    }

    public void tick(){
        for(Card c : cards){
            c.tick();
        }


        organizeHandForDisplay(sortAndUpdateHand());
        organizeActiveForDisplay(sortAndUpdateActive());

        turnOffRedTilesWhileNoCardSelected();


    }

    public void render(Graphics g){
        for(Card c : cards){
            c.render(g);
        }

    }



    private boolean noneSelected(ArrayList<Card> list){
        int count = 0;
        for(Card c : list){
            if(c.isSelected()){
                count++;
            }
        }
        if(count > 0) return false;
        else return true;
    }

    public void addCardToDeck(Card card){
        this.cards.add(card);
        card.setInDeck(true);
    }

    public void deal(int handSize){

        if(cards != null) {

            for (int i = 0; i < handSize; i++) {
                if (cards.get(i) != null) {
                    cards.get(i).setInHand(true);
                    cards.get(i).setInDeck(false);
                }
            }
        }

    }

    public ArrayList<Card> sortAndUpdateHand(){
        //Worried about hand.size()... seems to iterate and go 0, 1, 2, 2, 2, 2, 2, etc
        ArrayList<Card> hand = new ArrayList<>();

        for(Card c : cards) {

            if (c != null && c.isInHand()) {
                if(!hand.contains(c)) {
                    hand.add(c);
                }

            }
            if(!c.isInHand() && hand.contains(c)){
                hand.remove(c);
            }

        }
        return hand;
    }

    private void organizeHandForDisplay(ArrayList<Card> hand){
        int x = 0;

        for(Card c : hand) {
            c.setxPos(320 + x);
            c.setyPos(819);
            if (hand.size() > 9) {
                x += Card.SMALL_CARD_WIDTH;
            } else {
                x += Card.SMALL_CARD_WIDTH + 15;
            }

        }
    }

    public ArrayList<Card> sortAndUpdateActive(){

        ArrayList<Card> active = new ArrayList<>();

        for(Card c : cards) {

            if (c != null && c.isInActive()) {
                if(!active.contains(c)) {
                    active.add(c);
                }

            }
            if(!c.isInActive() && active.contains(c)){
                active.remove(c);
            }

        }
        return active;
    }


    private void organizeActiveForDisplay(ArrayList<Card> active){
        int x = 0;
        for(Card c : active) {
            c.setxPos(1225 + x);
            c.setyPos(510);
            if (active.size() > 9) {
                x += Card.SMALL_CARD_WIDTH;
            } else {
                x += Card.SMALL_CARD_WIDTH + 10;
            }

        }
    }

    public void shuffle(){

        Collections.shuffle(cards);
    }





    public void onMouseMove(MouseEvent e){

        for(Card c : cards){
            c.onMouseMove(e);
        }
    }
    public void onLeftMouseReleased(MouseEvent e){
        for(Card c : cards){
            //c.setSelected(false);
            for(Card d : cards) {
                if (d.isHovering()) {
                    c.setSelected(false);
                }
            }
            if(c.isHovering()){
                c.setSelected(true);
            }
            if(c.isSelected()) {
                c.onLeftMouseRelease(e);
            }
            if(!hoveringOnCard() && !c.hoveringOnRedTile()){
                c.setSelected(false);
            }
        }


    }
    public void onRightMouseReleased(MouseEvent e){
        for(Card c : cards){
            c.onRightMouseRelease(e);
        }

    }

    private boolean hoveringOnCard(){
        for(Card c : cards){
            if(handler.getController().getHitBox().intersects(c.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    private void turnOffRedTilesWhileNoCardSelected(){
        for(Card c : sortAndUpdateHand()) {
            if (noneSelected(sortAndUpdateHand())) {
                c.turnRedTilesOff();
            }
        }
    }

    //GETTERS AND SETTERS


    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Player getPlayer() {
        return player;
    }
}
