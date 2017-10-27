package com.martyworm.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import com.martyworm.ui.UIManager;

public class Controller implements MouseInputListener{

    private boolean leftPressed, rightPressed, clicked;
    private int mouseX, mouseY;
    private UIManager uiManager;
    private Rectangle hitBox;

    public Controller(){
        this.hitBox = new Rectangle(mouseX, mouseY, 1, 1);

    }

    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
        }
        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null){
            uiManager.onMouseMove(e);
        }

    }




    public void mouseDragged(MouseEvent e) {


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = true;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    public void setHitBox(Rectangle bounds) {
        this.hitBox = bounds;
    }

    public Rectangle getHitBox() {
        hitBox.x = mouseX;
        hitBox.y = mouseY;
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

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


}
