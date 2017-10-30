package com.martyworm.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;


    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker){
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering){
            g.drawImage(images[1], (int)x, (int)y, width, height, null);
        }
        if(!hovering){
            g.drawImage(images[0], (int)x, (int)y, width, height, null);
        }
        //Show Hit Boxes if needed
//      g.setColor(Color.blue);
//		g.fillRect((int)x, (int)y, hitBox.width, hitBox.height);

    }

    @Override
    public void onClick() {
        clicker.onClick();
    }



}