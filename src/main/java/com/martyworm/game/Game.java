package com.martyworm.game;

import com.martyworm.board.Board;
import com.martyworm.board.LoadingException;
import com.martyworm.board.Tile;
import com.martyworm.board.TileManager;
import com.martyworm.config.PropertiesManager;
import com.martyworm.entities.Entity;
import com.martyworm.entities.EntityManager;
import com.martyworm.entities.minion.RedDragon;
import com.martyworm.gfx.Assets;
import com.martyworm.gui.Controller;
import com.martyworm.gui.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;


public class Game implements Runnable {

    private Gui gui;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    private Board board;


    //Entities
    private EntityManager entityManager;
    private ArrayList<Entity> entities;

    //Tiles
    private TileManager tileManager;
    private List<Tile> tiles;

    //Mouse input
    private Controller controller;

    private Assets assets;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        this.board = new Board(this);
        this.controller = new Controller();
        this.tiles = new ArrayList<Tile>();
        this.entities = new ArrayList<Entity>();
        tileManager = new TileManager(this);
        entityManager = new EntityManager(this);

    }

    private void init() throws LoadingException { //initialize the game and starting pieces/tiles

        gui = new Gui(controller, title, width, height);
        gui.getFrame().addMouseListener(controller);
        gui.getFrame().addMouseMotionListener((MouseMotionListener) controller);
        gui.getCanvas().addMouseListener(controller);
        gui.getCanvas().addMouseMotionListener((MouseMotionListener) controller);


        //load assets(images etc)
        Assets.init();

        //initialize board
        String boardString = Board.loadBoard("worlds/world3.txt");
        this.tiles = Board.initBoard(boardString);


        //Will be refactored eventually
        entityManager.addEntity(new RedDragon(this, 1));

        for(Entity e : entities){
            if(e.getId() == 1){
                e.setXPos(getTile(253).getxPos());
                e.setYPos(getTile(253).getyPos());
            } else{
                e.setXPos(getTile(185).getxPos());
                e.setYPos(getTile(185).getyPos());
            }

        }

    }

    private void tick(){
        tileManager.tick();
        entityManager.tick();
    }


    private void render(){
        bs = gui.getCanvas().getBufferStrategy();
        if(bs == null){
            gui.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        //Render Background Image
        g.drawImage(Assets.backgroundImage, 0, 0, null);

        //Render Tiles
        tileManager.render(g);

        //render entities
        entityManager.render(g);


        if(PropertiesManager.getInstance().getProperty("showMouseHitbox")) {
		    g.setColor(Color.white);
		    g.fillRect((int) (this.controller.getMouseX()), (int)(this.controller.getMouseY()),
                    this.controller.getHitBox().width, this.controller.getHitBox().height);
        }
        bs.show();
        g.dispose();
    }


    public void run(){

        try {
            init();
        } catch (LoadingException loadingException){
            System.out.println("Unable to initialize game, loading exception: " + loadingException.getMessage());
            return;
        }

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }


    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Tile getSelectedTile(){
        //Need to make sure not Null when calling this
        return tileManager.getSelectedTile();
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Tile getTile(int tileId){
        //Need to make sure not null when calling this
        if (tileId < 0 || tileId >= tiles.size()) {
            return null;
        }
        return tiles.get(tileId);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

}
