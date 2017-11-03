package com.martyworm.entities.minion;

import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;
import com.martyworm.entities.Entity;
import com.martyworm.entities.tools.PathFinder;
import com.martyworm.gfx.Animation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Minion extends Entity{


    //Animations
    protected Animation animDown, animIdle, animAttack;
    private boolean isAttacking = false;

    //id
    protected int id;
    protected String name;

    //Attack Timer
    private long lastAttackTimer;
    private long attackCooldown = 200;
    private long attackTimer = attackCooldown;

    //Movement Variables
    protected int xStart, yStart;
    protected int xMove, yMove;
    private ArrayList<Tile> movePath;


    //tile
    private Tile selectedTile;

    public Minion(Handler handler, int id, BufferedImage[] images, int playerId) {
        super(handler, id, playerId);
        movePath = new ArrayList<Tile>();
        for(int i = 0; i < movesAvailable + 1; i++){
            movePath.add(i, null);
        }
    }

    @Override
    public void tick() {
        animIdle.tick();
        animAttack.tick();

        toggleXAndYMove();

        if(selected){
            showAvailableMoves();
            showPathFinder();
            if(selectedTile != null){
                move(selectedTile);
            }
        }

        //attack
        //Inventory
    }

    @Override
    public void render(Graphics g) {

        if(name != "Skeleton") {
            g.drawImage(getCurrentAnimationFrame(), xPos - 13/*offset the image size to hitBox*/, yPos - 10/*offset the image size to hitBox */, width+4, height+4, null);//should be width and height not ints
        }
        else{
            g.drawImage(getCurrentAnimationFrame(), xPos -10/*offset the image size to hitBox*/, yPos - 10/*offset the image size to hitBox */, width-8, height-8, null);//should be width and height not ints
        }

        //VISUALS FOR HITBOX if needed
//		g.setColor(Color.cyan);
//		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void onMouseMove(MouseEvent e){
        hovering = hitBox.contains(e.getX(), e.getY());

    }

    public void onLeftMouseRelease(MouseEvent e){
        if(handler.getBattle().getSelectedTile().isHovering() && handler.getBattle().getSelectedTile().isHighlighted()){
            selectedTile = handler.getBattle().getSelectedTile();
        }
    }

    public void onRightMouseRelease(MouseEvent e){
        if(selected && !isMoving()) {    //deselects minion
            selected = false;
            handler.getBattle().getTileManager().getSemiHiTiles().clear();
        }

    }

    private void move(Tile t){
            //if minion is selected and the tile clicked does not equal Null
            if(t.isHighlighted() && !t.isOccupied()){

                if(getXDiff() >= getYDiff()){

                    //make sure the tile clicked is not occupied, solid, and is hovering(available)
                    if(checkTileBooleans(t)){
                        moveX();
                        toggleMoving();
                        if(xMove == 0){
                            moveY();
                        }
                    }
                }

                else if(getXDiff() < getYDiff()){

                    //make sure the tile clicked is not occupied, solid, and is hovering(available)
                    if(checkTileBooleans(t)){
                        moveY();
                        toggleMoving();

                        if(getyMove() == 0){
                            moveX();
                        }

                    }
                }
            }

    }

    @Override
    public void moveX(){

        if(xMove > 0) {//moving left and right
            if (xPos < selectedTile.getxPos()) {
                xPos += xMove;
            } else if (xPos > selectedTile.getxPos()) {
                xPos -= xMove;
            }
        }
    }


    @Override
    public void moveY(){

        if(yMove > 0){//moving up and down
            if(yPos < selectedTile.getyPos()){
                yPos += yMove;
            }
            else if(yPos > selectedTile.getyPos()){
                yPos -= yMove;
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
        if(selected){
            for(Tile t : handler.getBattle().getTileManager().getTiles()){
                if(t.isOccupied()){
                    return t;
                }
            }
        }
        return null;
    }


    protected boolean collisionWithTile(int tileId){
        return handler.getBattle().getTile(tileId).isSolid();

    }

    public int getOccupiedTileId(int tileId){
        return handler.getBattle().getTile(tileId).getId();

    }

    private void toggleXAndYMove(){

        if(!selected){ //if minion isn't selected, xMove and yMove should be 0
            xMove = 0;
            yMove = 0;
        }

        //If minion is at the selectedTile position, x and y move should be 0
        if(selectedTile != null){
            if(yPos == selectedTile.getyPos()){
                yMove = 0;
            }
            if(xPos == selectedTile.getxPos()){
                xMove = 0;
            }
        }
    }

    private void showAvailableMoves(){

        if(getOccupiedTileEnt() != null) {
            Tile current = getOccupiedTileEnt();

            int currentX = current.getxPos();
            int currentY = current.getyPos();

            int xDistance = Math.abs(xStart - currentX);
            int yDistance = Math.abs(yStart - currentY);

            int distance = (xDistance + yDistance) / 40;

            setDistanceMoved(distance);
            setMovesAvailable(Entity.DEFAULT_TILE_MOVEMENT - getDistanceMoved());
            PathFinder.SemiHighlightToggle(handler, this, getMovesAvailable());
        }
    }




    private void showPathFinder(){

        //working without pathfinding
        for(Tile t : handler.getBattle().getTileManager().getSemiHiTiles()) {
            PathFinder.highlightPath(handler.getMouseController(), this, t);
        }

    }

    private boolean checkTileBooleans(Tile selected){
        //make sure the tile clicked is not occupied, solid, and is hovering(available)
        return !selected.isOccupied() && !selected.isSolid() && selected.isSemiHighlited();
    }

    private void toggleMoving(){
        if(xPos == selectedTile.getxPos()){
            xMove = 0;

        }
        if(yPos == selectedTile.getyPos()){
            yMove = 0;
        }

    }


    //Getters & Setters

    @Override
    public void setSelectedTileForMovement(Tile t){
        selectedTile = t;
    }

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
    public boolean isHovering() {
        return hovering;
    }

    @Override
    public void setHovering(boolean hovering) {
        this.hovering = hovering;
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
    public int getMovesAvailable() {
        return movesAvailable;
    }
    @Override
    public void setMovesAvailable(int movesAvailable) {
        this.movesAvailable = movesAvailable;
    }
    @Override
    public int getDistanceMoved() {
        return distanceMoved;
    }
    @Override
    public void setDistanceMoved(int distanceMoved) {
        this.distanceMoved = distanceMoved;
    }


}
