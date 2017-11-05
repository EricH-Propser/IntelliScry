package com.martyworm.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


import com.martyworm.Handler.Handler;
import com.martyworm.board.TileManager;
import com.martyworm.cards.CardManager;
import com.martyworm.entities.EntityManager;
import com.martyworm.ui.UIManager;


public class MouseController implements MouseInputListener{

    private boolean leftPressed, rightPressed, clicked;
    private int mouseX, mouseY;
    private UIManager uiManager;

    private Handler handler;
    private Rectangle hitBox;

    public MouseController(){
        this.hitBox = new Rectangle(mouseX, mouseY, 1, 1);

    }

    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }


    public void mousePressed(MouseEvent e) {
        leftPressed = e.getButton() == MouseEvent.BUTTON1;
        rightPressed = e.getButton() == MouseEvent.BUTTON3;
    }

    public void mouseReleased(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
            if(handler != null) {
                handler.onLeftMouseRelease(e);
            }
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
            if(handler != null){
                handler.onRightMouseRelease(e);
            }

        }
        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }


    }



    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        hitBox.x = mouseX;
        hitBox.y = mouseY;

        //These three calls must be in this order
        if(uiManager != null){
            uiManager.onMouseMove(e);
        }
        if(handler != null) {
            handler.onMouseMove(e);
        }


    }



    public void mouseDragged(MouseEvent e) {

    }


    public void mouseClicked(MouseEvent e) {
        clicked = true;
    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }


    public void setHitBox(Rectangle bounds) {
        this.hitBox = bounds;
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }


    public boolean isLeftPressed(){
        return leftPressed;
    }
    public boolean isRightPressed(){
        return rightPressed;
    }
    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }

    public boolean isClicked() {
        return clicked;
    }

}