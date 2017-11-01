package com.martyworm.cards;


import com.martyworm.Handler.Handler;
import com.martyworm.Player.Player;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardManager {

    private static final int MAX_HAND_BEFORE_RESIZE = 9;

    private Handler handler;
    private ArrayList<Card> cards;


    private Player player;

    public CardManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        this.cards = new ArrayList<>();
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

    public boolean noneSelected(List<Card> list){
        return !list.stream().anyMatch(c-> c.isSelected());
    }

    public void addCardToDeck(Card card){
        this.cards.add(card);
        card.setInDeck(true);
    }

    public void deal(int handSize){

        if(cards == null) {
            return;
        }

        for (int i = 0; i < handSize; i++) {
            if (cards.get(i) != null) {
                cards.get(i).setInHand(true);
                cards.get(i).setInDeck(false);
            }
        }

    }

    //this method doesn't make any sense given it's name - what does it do?
    public ArrayList<Card> sortAndUpdateHand(){
        //Worried about hand.size()... seems to iterate and go 0, 1, 2, 2, 2, 2, 2, etc
        ArrayList<Card> hand = new ArrayList<>();

        for(Card c : cards) {

            if (c.isInHand()) {
                if(!hand.contains(c)) { //perhaps make the hand a set instead of a list?
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
        int y = 0;
        for(Card c : hand) {
            if(c.getPlayerNumber() == 1) {
                c.setxPos(320 + x);
                c.setyPos(819);
                x += hand.size() > MAX_HAND_BEFORE_RESIZE ? Card.SMALL_CARD_WIDTH : (Card.SMALL_CARD_WIDTH + 15);
            } else if(c.getPlayerNumber() == 2){
                c.setxPos(320 + y);
                c.setyPos(20);
                y+= hand.size() > MAX_HAND_BEFORE_RESIZE ? Card.SMALL_CARD_HEIGHT : (Card.SMALL_CARD_HEIGHT + 15);
            }
        }
    }

    //this name is misleading - where is there any sorting?
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
        int y = 0;
        for(Card c : active) {
            if (c.getPlayerNumber() == 1) {
                c.setxPos(1225 + x);
                c.setyPos(510);
                x += active.size() > MAX_HAND_BEFORE_RESIZE ? Card.SMALL_CARD_WIDTH : (Card.SMALL_CARD_WIDTH + 10);
            } else if(c.getPlayerNumber() == 2){
                c.setxPos(1225 + y);
                c.setyPos(204);
                y += active.size() > MAX_HAND_BEFORE_RESIZE ? Card.SMALL_CARD_HEIGHT : (Card.SMALL_CARD_HEIGHT + 10);
            }
        }
    }

    public void shuffle(){ //this should belong to a deck class.
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
                c.turnRedTilesOff(c.playerNumber);
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
