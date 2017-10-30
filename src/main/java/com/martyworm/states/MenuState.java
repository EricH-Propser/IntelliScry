package com.martyworm.states;

import com.martyworm.Handler.Handler;
import com.martyworm.gfx.Assets;
import com.martyworm.ui.ClickListener;
import com.martyworm.ui.UIImageButton;
import com.martyworm.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getController().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.playButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getController().setUIManager(null);
                setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();

         //Temporarily just go directly to the GameState, skip the menu state!
//        handler.getController().setUIManager(null);
//        State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}