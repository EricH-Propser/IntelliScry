package intellij.game;

import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import intellij.Handler.Handler;
import intellij.entities.minion.*;
import intellij.entities.Entity;
import intellij.entities.EntityManager;
import intellij.board.*;
import intellij.gfx.Assets;
import intellij.gui.Controller;
import intellij.gui.Gui;
import intellij.states.GameState;
import intellij.states.MenuState;
import intellij.states.State;


public class Game implements Runnable {

    //TEST COMMENT FOR GIT

    private Gui gui;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    private Board board;

    //States
    public State gameState;
    public State menuState;

    //Entities
    private EntityManager entityManager;
    private ArrayList<Entity> entities;

    //Tiles
    private TileManager tileManager;
    private ArrayList<Tile> tiles;

    //Mouse input
    private Controller controller;

    //Handler
    private Handler handler;

    private Assets assets;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        this.controller = new Controller();


    }

    private void init(){ //initialize the game and starting pieces/tiles
        gui = new Gui(controller, title, width, height);
        gui.getFrame().addMouseListener(controller);
        gui.getFrame().addMouseMotionListener((MouseMotionListener) controller);
        gui.getCanvas().addMouseListener(controller);
        gui.getCanvas().addMouseMotionListener((MouseMotionListener) controller);

        //load assets(images etc)
        Assets.init();

        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);


    }

    public void tick(){
        if(State.getState() != null)
            State.getState().tick();



    }

    public void render(Graphics g){
        bs = gui.getCanvas().getBufferStrategy();
        if(bs == null){
            gui.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        if(State.getState() != null)
            State.getState().render(g);


        bs.show();
        g.dispose();
    }


    public void run(){

        init();

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
                render(g);
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

//    public Tile getSelectedTile(){
//        //Need to make sure not Null when calling this
//        return tileManager.getSelectedTile();
//    }


    public Tile getTile(int tileId){
        //Need to make sure not null when calling this
        if (tileId < 0 || tileId >= tiles.size()) {
            return null;
        }else{
            return tiles.get(tileId);

        }

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

    public ArrayList<Tile> getTiles() {
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



    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }



}
