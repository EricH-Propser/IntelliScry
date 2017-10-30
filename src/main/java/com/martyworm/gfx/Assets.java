package com.martyworm.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage dirt, grass, stone, plant1, backgroundImage, redDragon, translucent, guiOverlay;
    public static BufferedImage[] dragon_down, dragon_idle, dragon_attack;

    public static BufferedImage[] skeleton_idle;

    public static BufferedImage[] playButton, endTurnButton, manaBubble;
    public static BufferedImage[] rockTile2, dirtTile2, grassTile2, card_Skeleton, card_redDragon;



    private static final int WIDTH = 30, HEIGHT = 30;
    private static final int WIDTH_P = 59, HEIGHT_P = 55;
    private static final int WIDTH_DRAG = 166, HEIGHT_DRAG = 147;
    private static final int WIDTH_ORB = 52, HEIGHT_ORB = 49;

    private static final int FULL_CARD_HEIGHT = 353;
    private  static final int FULL_CARD_WIDTH = 245;
    private static final int SMALL_CARD_HEIGHT = 81;
    private static final int SMALL_CARD_WIDTH = 86;


    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/mySheet.png"));
        SpriteSheet plantSheet = new SpriteSheet(ImageLoader.loadImage("/textures/eggplant_spritesheet_transp.png"));
//        SpriteSheet dragonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/reddragon.png"));
        SpriteSheet dragonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/dragonfaceSheet.png"));

        SpriteSheet crystalBallSheet = new SpriteSheet(ImageLoader.loadImage("/textures/crystalBallSheet.png"));
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/buttonSheet.png"));
        SpriteSheet card_SkeletonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Skeleton1.png"));
        SpriteSheet card_redDragonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/card_RedDragon.png"));
        SpriteSheet skeletonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/skellysheet.png"));

        SpriteSheet manaBubbleSheet = new SpriteSheet(ImageLoader.loadImage("/textures/manaBubble2.png"));

        backgroundImage = ImageLoader.loadImage("/textures/mBackground1500x937.png");
        guiOverlay = ImageLoader.loadImage("/textures/guiTest.png");


        //BUTTONS & MANA
        playButton = new BufferedImage[2];
        playButton[0] = buttonSheet.crop(0, 0, 60, 30);
        playButton[1] = buttonSheet.crop(60, 0, 60, 30);

        endTurnButton = new BufferedImage[3];
        endTurnButton[0] = buttonSheet.crop(120, 0, 60, 30);
        endTurnButton[1] = buttonSheet.crop(180, 0, 60, 30);
        endTurnButton[2] = buttonSheet.crop(240, 0, 60, 30);

        manaBubble = new BufferedImage[6];
        manaBubble[0] = manaBubbleSheet.crop(0, 0, 107, 85);
        manaBubble[1] = manaBubbleSheet.crop(108, 0, 107, 85);
        manaBubble[2] = manaBubbleSheet.crop(216, 0, 107, 85);
        manaBubble[3] = manaBubbleSheet.crop(324, 0, 107, 85);
        manaBubble[4] = manaBubbleSheet.crop(432, 0, 107, 85);
        manaBubble[5] = manaBubbleSheet.crop(540, 0, 107, 85);




        //  TILES #################################################################
        rockTile2 = new BufferedImage[4];
        rockTile2[0] = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        rockTile2[1] = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);
        rockTile2[2] = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);
        rockTile2[3] = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);

        dirtTile2 = new BufferedImage[4];
        dirtTile2[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
        dirtTile2[1] = sheet.crop(WIDTH*4, 0, WIDTH, HEIGHT);
        dirtTile2[2] = sheet.crop(WIDTH*6, 0, WIDTH, HEIGHT);
        dirtTile2[3] = sheet.crop(WIDTH*9, 0, WIDTH, HEIGHT);


        grassTile2 = new BufferedImage[4];
        grassTile2[0] = sheet.crop(WIDTH*1, 0, WIDTH, HEIGHT);
        grassTile2[1] = sheet.crop(WIDTH*5, 0, WIDTH, HEIGHT);
        grassTile2[2] = sheet.crop(WIDTH*7, 0, WIDTH, HEIGHT);
        grassTile2[3] = sheet.crop(WIDTH*10, 0, WIDTH, HEIGHT);

        dirt = sheet.crop(0, 0, WIDTH, HEIGHT);
        grass = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        stone = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        translucent = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);
        // ########################################################################





        //  MINIONS ######################################################################################
//        dragon_idle = new BufferedImage[3];
//        dragon_idle[0] = dragonSheet.crop(0, 515, 153/*width*/, 127/*height*/);
//        dragon_idle[1] = dragonSheet.crop(152, 515, 174, 127);
//        dragon_idle[2] = dragonSheet.crop(338, 515, 173, 127);


//        dragon_down = new BufferedImage[4];
//        dragon_down[0] = dragonSheet.crop(546, 146, WIDTH_DRAG, HEIGHT_DRAG);
//        dragon_down[1] = dragonSheet.crop(752, 146, WIDTH_DRAG, HEIGHT_DRAG);
//        dragon_down[2] = dragonSheet.crop(950, 146, WIDTH_DRAG, HEIGHT_DRAG);
//        dragon_down[3] = dragonSheet.crop(752, 146, WIDTH_DRAG, HEIGHT_DRAG);


//        dragon_attack = new BufferedImage[2];
//        dragon_attack[0] = dragonSheet.crop(168, 354, WIDTH_DRAG/*width*/, HEIGHT_DRAG/*height*/);
//        dragon_attack[1] = dragonSheet.crop(334, 354, WIDTH_DRAG, HEIGHT_DRAG);

        dragon_idle = new BufferedImage[4];
        dragon_idle[0] = dragonSheet.crop(0, 0, 99/*width*/, 127/*height*/);
        dragon_idle[1] = dragonSheet.crop(100, 0, 99, 127);
        dragon_idle[2] = dragonSheet.crop(200, 0, 99, 127);
        dragon_idle[3] = dragonSheet.crop(300, 0, 99, 127);

        dragon_down = new BufferedImage[4];
        dragon_idle[0] = dragonSheet.crop(0, 0, 99/*width*/, 127/*height*/);
        dragon_idle[1] = dragonSheet.crop(100, 0, 99, 127);
        dragon_idle[2] = dragonSheet.crop(200, 0, 99, 127);
        dragon_idle[3] = dragonSheet.crop(300, 0, 99, 127);

        dragon_attack = new BufferedImage[4];
        dragon_idle[0] = dragonSheet.crop(0, 0, 99/*width*/, 127/*height*/);
        dragon_idle[1] = dragonSheet.crop(100, 0, 99, 127);
        dragon_idle[2] = dragonSheet.crop(200, 0, 99, 127);
        dragon_idle[3] = dragonSheet.crop(300, 0, 99, 127);


        skeleton_idle = new BufferedImage[4];
        skeleton_idle[0] = skeletonSheet.crop(0, 0, 99, 127);
        skeleton_idle[1] = skeletonSheet.crop(100, 0, 99, 127);
        skeleton_idle[2] = skeletonSheet.crop(200, 0, 99, 127);
        skeleton_idle[3] = skeletonSheet.crop(300, 0, 99, 127);




//        redDragon = dragonSheet.crop(546, 146, WIDTH_DRAG, HEIGHT_DRAG);
        plant1 = plantSheet.crop(236, 55, WIDTH_P, HEIGHT_P);
        // ################################################################################################


        // CARDS   ##########################################################################################

        card_Skeleton = new BufferedImage[3];
        card_Skeleton[0] = card_SkeletonSheet.crop(0, 0, FULL_CARD_WIDTH, FULL_CARD_HEIGHT);
        card_Skeleton[1] = card_SkeletonSheet.crop(246, 0, SMALL_CARD_WIDTH, SMALL_CARD_HEIGHT);
        card_Skeleton[2] = card_SkeletonSheet.crop(332, 0, SMALL_CARD_WIDTH, SMALL_CARD_HEIGHT);

        card_redDragon = new BufferedImage[3];
        card_redDragon[0] = card_redDragonSheet.crop(0, 0, FULL_CARD_WIDTH, FULL_CARD_HEIGHT);
        card_redDragon[1] = card_redDragonSheet.crop(246, 0, SMALL_CARD_WIDTH, SMALL_CARD_HEIGHT);
        card_redDragon[2] = card_redDragonSheet.crop(332, 0, SMALL_CARD_WIDTH, SMALL_CARD_HEIGHT);


        // ###################################################################################################

    }


}
