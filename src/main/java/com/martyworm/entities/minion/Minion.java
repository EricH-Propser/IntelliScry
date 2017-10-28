package com.martyworm.entities.minion;

import java.awt.Graphics;


import java.awt.image.BufferedImage;
import java.util.*;

import com.martyworm.board.Tile;
import com.martyworm.entities.Entity;
import com.martyworm.game.Game;
import com.martyworm.gfx.Animation;

public class Minion extends Entity{


    //Animations
    protected Animation animDown, animIdle, animAttack;
    private boolean isAttacking = false;

    //id
    protected int id;

    //Attack Timer
    private long lastAttackTimer;
    private long attackCooldown = 200;
    private long attackTimer = attackCooldown;

    //Movement Variables
    protected int xStart, yStart;
    protected int xMove, yMove;
    private ArrayList<Tile> movePath;


    //test
    private Tile selectedTile;
    private Tile checkPoint;

    public Minion(Game game, int id, BufferedImage[] images) {
        super(game, id);
        movePath = new ArrayList<Tile>();
        for(int i = 0; i < tileMoves + 1; i++){
            movePath.add(i, null);
        }
    }

    @Override
    public void tick() {
        animIdle.tick();
        animAttack.tick();

        //attack
        //Inventory
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), xPos-22/*offset the image size to hitBox*/, yPos-22/*offset the image size to hitBox */, width, height, null);//should be width and height not ints


        //VISUALS FOR HITBOX if needed
//		g.setColor(Color.cyan);
//		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }


    @Override
    public void moveX(){
        selectedTile = game.getSelectedTile();

        if(selectedTile != null){
            if(xMove > 0){//moving left and right
                if(xPos < selectedTile.getxPos()){
                    xPos += xMove;
                }
                else if(xPos > selectedTile.getxPos()){
                    xPos -= xMove;
                }
            }
        }
    }


    @Override
    public void moveY(){
        selectedTile = game.getSelectedTile();

        if(selectedTile != null){
            if(yMove > 0){//moving up and down
                if(yPos < selectedTile.getyPos()){
                    yPos += yMove;
                }
                else if(yPos > selectedTile.getyPos()){
                    yPos -= yMove;
                }
            }
        }
    }


    @Override
    public void die(){
        System.out.println("dead");
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(isAttacking == true){
            return animAttack.getCurrentFrame();
        }
        else{//idle or default
            return animIdle.getCurrentFrame();
        }

    }

    public Tile getOccupiedTile(){
        if(isSelected){
            for(Tile t : game.getTiles()){
                if(t.isOccupied()){
                    return t;
                }
            }
        }
        return null;
    }


    protected boolean collisionWithTile(int tileId){
        return game.getTile(tileId).isSolid();

    }

    public int getOccupiedTileId(int tileId){
        return game.getTile(tileId).getId();

    }






    //Getters & Setters


    @Override
    public int getXDiff(){

        return Math.abs(xPos - xStart);
    }
    @Override
    public int getYDiff(){

        return Math.abs(yPos - yStart);
    }

    @Override
    public boolean isMoving(){
        if(xMove > 0 || yMove > 0){
            return true;
        }
        return false;
    }

    @Override
    public void setHitBoxX(int x){
        hitBox.x = x;
    }
    @Override
    public void setHitBoxY(int y){
        hitBox.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getSpeed() {
        return speed;
    }


    @Override
    public int getxMove() {
        return xMove;
    }

    @Override
    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    @Override
    public int getyMove() {
        return yMove;
    }

    @Override
    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    @Override
    public int getxStart() {
        return xStart;
    }
    @Override
    public void setxStart(int xStart) {
        this.xStart = xStart;
    }
    @Override
    public int getyStart() {
        return yStart;
    }
    @Override
    public void setyStart(int yStart) {
        this.yStart = yStart;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }



    @Override
    public void setPixelsMovedTest(int pixelsMovedTest){
        this.pixelsMovedTest = pixelsMovedTest;
    }



}
