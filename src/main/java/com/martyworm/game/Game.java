package com.martyworm.game;

import com.martyworm.Handler.Handler;
import com.martyworm.board.Board;
import com.martyworm.board.exceptions.LoadingException;
import com.martyworm.board.tiles.Tile;
import com.martyworm.board.TileManager;
import com.martyworm.entities.Entity;
import com.martyworm.entities.EntityManager;
import com.martyworm.gfx.Assets;
import com.martyworm.gui.MouseCommand;
import com.martyworm.gui.MouseController;
import com.martyworm.gui.Gui;
import com.martyworm.states.GameState;
import com.martyworm.states.MenuState;
import com.martyworm.states.State;

import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Game implements Runnable {


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
    private MouseController mouseController;

    //Handler
    private Handler handler;

    private Assets assets;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        this.mouseController = new MouseController(new MouseCommand() {
            @Override
            public void execute(Point point) {
                //do left click call, probably Battle.handleLeftClick or whatever is going to own the managers
            }
        }, new MouseCommand() {
            @Override
            public void execute(Point point) {
                //do right click call
            }
        }, new MouseCommand() {
            @Override
            public void execute(Point point) {
                //handle on mouse moved, same as clicks.
            }
        });


    }

    private void init() throws LoadingException{ //initialize the game and starting pieces/tiles
        gui = new Gui(mouseController, title, width, height);
        gui.getFrame().addMouseListener(mouseController);
        gui.getFrame().addMouseMotionListener(mouseController);
        gui.getCanvas().addMouseListener(mouseController);
        gui.getCanvas().addMouseMotionListener(mouseController);

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

        try {
            init();
        } catch(LoadingException le){
            System.out.println("Unable to initialize game: " + le.getMessage());
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


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public MouseController getMouseController() {
        return mouseController;
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

}
