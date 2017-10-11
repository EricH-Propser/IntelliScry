package intellij.gui;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Gui {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Gui(Controller controller, String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }



    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        //Setting location to my 2nd monitor..
        //Could set to center of first screen with below code
        //frame.setLocationRelativeTo(null);
        frame.setLocation(1980, 50);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}