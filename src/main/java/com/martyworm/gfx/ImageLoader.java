package com.martyworm.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ClassLoader.getSystemClassLoader().getResource(path));
        } catch (IOException e) {
            System.out.println("Couldnt load image");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
