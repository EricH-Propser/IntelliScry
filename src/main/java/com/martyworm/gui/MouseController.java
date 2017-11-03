package com.martyworm.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


import com.martyworm.cards.CardManager;
import com.martyworm.ui.UIManager;


public class MouseController implements MouseInputListener{

    private int mouseX, mouseY;
    private Rectangle hitBox;

    private MouseCommand leftClickCommand;
    private MouseCommand rightClickCommand;
    private MouseCommand mouseMoveCommand;

    public MouseController(MouseCommand leftClickCommand, MouseCommand rightClickCommand, MouseCommand mouseMoveCommand){
        this.hitBox = new Rectangle(mouseX, mouseY, 1, 1);
        this.leftClickCommand = leftClickCommand;
        this.rightClickCommand = rightClickCommand;
        this.mouseMoveCommand = mouseMoveCommand;
    }


    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            leftClickCommand.execute(e.getPoint());
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightClickCommand.execute(e.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        /*
        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }
        if(cardManager != null){
            cardManager.onLeftMouseReleased(e);
        }
        */
    }



    public void mouseMoved(MouseEvent e) {

        mouseMoveCommand.execute(e.getPoint());
        /*
        if(uiManager != null){
            uiManager.onMouseMove(e);
        }
        if(cardManager != null) {
            cardManager.onMouseMove(e);
        }
        */
    }



    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

}
