package com.martyworm.board;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.martyworm.ui.ClickListener;
import com.martyworm.game.Game;

public class Tile {

    public static final int TILEWIDTH = 40;
    public static final int TILEHEIGHT = 40;

    public static Tile[] tiles = new Tile[337];

    private int xPos, yPos, width, height, id;
    private Rectangle hitBox;

    private BufferedImage[] images;
    private BufferedImage image;

    private boolean hovering;
    private boolean semiHighlited;
    private boolean highlighted = false;
    private boolean isOccupied = false;
    private boolean isSelected = false;;

    //For Movement
    int gValue;
    int hValue;
    int moveValue;
    private int distanceFromEntity;



    private ClickListener clicker;
    private Game game;

    public Tile(int id, BufferedImage[] images, int xPosition, int yPosition){

        this.images = images;
        this.clicker = clicker;
        tiles[id] = this;

        this.game = game;
        this.width = TILEWIDTH;
        this.height = TILEHEIGHT;

        semiHighlited = false;

        this.image = image;
        this.id = id;
        this.xPos = xPosition;
        this.yPos = yPosition;

        //invisible rectangle for mouse/game interaction
        hitBox = new Rectangle(xPosition, yPosition, height-1, width-1);
    }

    public void tick(){


    }

    public void render(Graphics g){

        //just displays which tile image to use based on the tile's boolean 'hovering'
        if(semiHighlited){
            if(highlighted){
                g.drawImage(images[2], xPos, yPos, width, height, null);
            }else {
                g.drawImage(images[1], xPos, yPos, width, height, null);
            }
        }
        else{
            g.drawImage(images[0], xPos, yPos, width, height, null);
        }

        //Show Tile Hit Boxes if needed
//		g.setColor(Color.white);
//		g.fillRect((int) (xPos /*+ bounds.x*/), (int)(yPos /*+ bounds.y*/), hitBox.width, hitBox.height);

    }



    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getxPos() {
        return xPos;
    }


    public int getyPos() {
        return yPos;
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isHovering() {
        return hovering;
    }

    public Tile getHoveringTile(){
        if(isHovering()) return this;
        else return null;
    }

    public int getgValue() {
        return gValue;
    }

    public void setgValue(int gValue) {
        this.gValue = gValue;
    }

    public int gethValue() {
        return hValue;
    }

    public void sethValue(int hValue) {
        this.hValue = hValue;
    }

    public int getMoveValue() {
        return moveValue;
    }

    public void setMoveValue(int moveValue) {
        this.moveValue = moveValue;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isSemiHighlited() {
        return semiHighlited;
    }

    public void setSemiHighlited(boolean hovering) {
        this.semiHighlited = hovering;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isSolid() {
        return false;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getDistanceFromEntity() {
        return distanceFromEntity;
    }

    public void setDistanceFromEntity(int distanceFromEntity) {
        this.distanceFromEntity = distanceFromEntity;
    }
}
