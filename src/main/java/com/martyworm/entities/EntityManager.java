package com.martyworm.entities;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


import com.martyworm.Handler.Handler;
import com.martyworm.board.tiles.Tile;
import com.martyworm.entities.minion.Minion;
import com.martyworm.entities.tools.PathFinder;
import com.martyworm.gui.MouseController;


public class EntityManager {

    private Handler handler;
    private ArrayList<Entity> entities = new ArrayList<>();



    public EntityManager(Handler handler){
        this.handler = handler;
    }

    public void tick(){

        updateHitBoxPositions();

        for(Entity m : entities) {
            m.tick();
        }

    }

    public void render(Graphics g){
        //Game calls this which renders each entity
        for(Entity e : entities){
            e.render(g);
        }
    }

    public void onMouseMove(MouseEvent e){
        for(Entity m : entities){
            m.onMouseMove(e);
        }

    }

    public void onLeftMouseRelease(MouseEvent e){
        for(Entity m : entities){
            if(m.isSelected()) {
                m.onLeftMouseRelease(e);
            }
            for(Entity d : entities) {
                if (d.isHovering()) {
                    m.setSelected(false);
                }
            }
            if(m.isHovering()){
                m.setSelected(true);
                m.setDistanceMoved(0);
                if(getOccupiedTile(m) != null){
                    m.setxStart(getOccupiedTile(m).getxPos());
                    m.setyStart(getOccupiedTile(m).getyPos());
                }
            }
            if(m.isSelected() && !m.isHovering()){
                m.setxMove(m.getSpeed());
                m.setyMove(m.getSpeed());
            }
        }
    }

    public void onRightMouseRelease(MouseEvent e){
        for(Entity m : entities){
            m.onRightMouseRelease(e);
        }
    }



    private void updateHitBoxPositions(){
        for (Entity e : entities) {
            e.setHitBoxX(e.getXPos());
            e.setHitBoxY(e.getYPos());
        }
    }


    private Tile getOccupiedTile(Entity e){

        for(Tile t : handler.getBattle().getTileManager().getTiles()) {
            if (t.getHitBox().contains(e.getHitBox())) {
                return t;
            }
        }
        return null;
    }


    public void addEntity(Entity e){
        entities.add(e);
    }

    public Minion addMinion(Minion m){
        return m;
    }

    //Getters & Setters


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }


}
