package intellij.entities;

import java.awt.Graphics;
import java.util.ArrayList;


import intellij.board.Tile;
import intellij.entities.tools.PathFinder;
import intellij.game.Game;

public class EntityManager {

    private Game game;
    private ArrayList<Entity> entities;


    //movement variables
    private int movesAvailable;
    private Tile selectedTile;

    public EntityManager(Game game){
        this.game = game;
        entities = game.getEntities();

    }

    public void tick(){
        //movement
        getInput();

        for(Entity e : entities){
            e.tick();
            if(e.isSelected()){

                if(movesAvailable > 0 && game.getSelectedTile() != null){
                    selectedTile = game.getSelectedTile();
                }
                System.out.println(e.getxMove() + ", " + e.getyMove());
                showAvailableMoves(e);
                showPathFinder(e);
                move();

            }
            updateHitBoxPosition();
        }

    }

    public void render(Graphics g){
        //Game calls this which renders each entity
        for(Entity e : entities){
            e.render(g);
        }
    }


    //GET INPUT MAIN FUNC
    private void getInput(){

        for(Entity e : entities){


            setStartingSelectionVariables(e);

            toggleMinionDeselect(e);

            toggleSpeed(e);

            toggleXAndYMove(e);

        }



    }


    //MAIN MOVE FUNC
    private void move(){

        for(Entity e : entities){
            //if minion is selected and the tile clicked does not equal Null
            if(selectedTile != null && selectedTile.isHighlighted() && !selectedTile.isOccupied()){

                if(e.getXDiff() >= e.getYDiff()){

                    //make sure the tile clicked is not occupied, solid, and is hovering(available)
                    if(checkTileBooleans(selectedTile)){
                        e.moveX();
                        toggleMoving(e);
                        if(e.getxMove() == 0){
                            e.moveY();
                        }
                    }
                }

                else if(e.getXDiff() < e.getYDiff()){

                    //make sure the tile clicked is not occupied, solid, and is hovering(available)
                    if(checkTileBooleans(selectedTile)){
                        e.moveY();
                        toggleMoving(e);

                        if(e.getyMove() == 0){
                            e.moveX();
                        }

                    }
                }
            }
        }
    }
    private void showAvailableMoves(Entity e){

        if(e.getOccupiedTileEnt() != null) {
            Tile current = e.getOccupiedTileEnt();

            int currentX = current.getxPos();
            int currentY = current.getyPos();

            int xDistance = Math.abs(e.getxStart() - currentX);
            int yDistance = Math.abs(e.getyStart() - currentY);

            int distance = (xDistance + yDistance) / 40;

            int movesAvailable = 4 - distance;
            PathFinder.SemiHighlightToggle(game, e, movesAvailable);
        }
    }




    private void showPathFinder(Entity e){

        //working without pathfinding
        for(Tile t : game.getTileManager().getSemiHiTiles()) {
            PathFinder.highlightPath(game.getController(), e, t);
        }

    }


    private void updateHitBoxPosition(){
        for (Entity e : entities) {
            e.setHitBoxX(e.getXPos());
            e.setHitBoxY(e.getYPos());
        }
    }


    //**************************
    //MOVEMENT & INPUT HELPER FUNCTIONS
    //**************************
    private boolean checkTileBooleans(Tile selected){
        //make sure the tile clicked is not occupied, solid, and is hovering(available)
        return !selected.isOccupied() && !selected.isSolid() && selected.isSemiHighlited();
    }

    private void toggleMoving(Entity e){
        if(e.getXPos() == selectedTile.getxPos()){
            e.setxMove(0);
            //TESTING BELOW
            //e.setxStart(selectedTile.getxPos());
        }
        if(e.getYPos() == selectedTile.getyPos()){
            e.setyMove(0);
            //TESTING BELOW
           // e.setyStart(selectedTile.getyPos());
        }

    }

    private void toggleMinionDeselect(Entity e){
        if(e.isSelected()){	//deselects minion
            if(game.getController().isRightPressed() && !e.isMoving()){//disallow deselection during movement
                e.setSelected(false);
                game.getTileManager().getSemiHiTiles().clear();
            }
        }
    }

    private void toggleXAndYMove(Entity e){

        if(!e.isSelected()){ //if minion isn't selected, xMove and yMove should be 0
            e.setxMove(0);
            e.setyMove(0);
        }

        //If minion is at the selectedTile position, x and y move should be 0
        if(selectedTile != null){
            if(e.getYPos() == selectedTile.getyPos()){
                e.setyMove(0);
            }
            if(e.getXPos() == selectedTile.getxPos()){
                e.setxMove(0);
            }
        }
    }

    private void toggleSpeed(Entity e){
        if(e.isSelected()){	//Turns on speed > 0 once minion is selected and the mouse is left clicked
            if(!game.getController().getHitBox().intersects(e.getHitBox()) && game.getController().isLeftPressed()){
                e.setxMove(e.getSpeed());
                e.setyMove(e.getSpeed());
            }
        }
    }

    private void setStartingSelectionVariables(Entity e){
        //If mouse is left clicked while minion hitbox and mouse hitbox intersect
        if(game.getController().isLeftPressed() && e.hitBox.intersects(game.getController().getHitBox()) && !e.isSelected()){
            movesAvailable = 4;
            e.setPixelsMovedTest(0);
            for(Entity g : entities){
                g.setSelected(false);
            }
            e.setSelected(true);

            if(getOccupiedTile(e) != null){
                e.setxStart(getOccupiedTile(e).getxPos());
                e.setyStart(getOccupiedTile(e).getyPos());
            }
        }
    }
    private Tile getOccupiedTile(Entity e){

        for(Tile t : game.getTileManager().getTiles()) {
            if (t.getHitBox().contains(e.getHitBox())) {
                return t;
            }
        }
        return null;
    }



    public void addEntity(Entity e){
        entities.add(e);
    }

    //Getters & Setters

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


}
