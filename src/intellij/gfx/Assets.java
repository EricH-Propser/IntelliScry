package intellij.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage dirt, grass, stone, plant1, backgroundImage, redDragon, translucent;
    public static BufferedImage[] dragon_down, dragon_idle, dragon_attack;
    //	public static BufferedImage[] scryOrb;
    public static BufferedImage scryOrb;

    public static BufferedImage[] playButton, rockTile2, dirtTile2, grassTile2;


    private static final int WIDTH = 30, HEIGHT = 30;
    private static final int WIDTH_P = 59, HEIGHT_P = 55;
    private static final int WIDTH_DRAG = 166, HEIGHT_DRAG = 147;
    private static final int WIDTH_ORB = 52, HEIGHT_ORB = 49;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/mySheet.png"));
        SpriteSheet plantSheet = new SpriteSheet(ImageLoader.loadImage("/textures/eggplant_spritesheet_transp.png"));
        SpriteSheet dragonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/reddragon.png"));
        SpriteSheet crystalBallSheet = new SpriteSheet(ImageLoader.loadImage("/textures/crystalBallSheet.png"));
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/buttonSheet.png"));

        dragon_idle = new BufferedImage[3];
        dragon_idle[0] = dragonSheet.crop(0, 515, 153/*width*/, 127/*height*/);
        dragon_idle[1] = dragonSheet.crop(152, 515, 174, 127);
        dragon_idle[2] = dragonSheet.crop(338, 515, 173, 127);

        dragon_down = new BufferedImage[4];
        dragon_down[0] = dragonSheet.crop(546, 146, WIDTH_DRAG, HEIGHT_DRAG);
        dragon_down[1] = dragonSheet.crop(752, 146, WIDTH_DRAG, HEIGHT_DRAG);
        dragon_down[2] = dragonSheet.crop(950, 146, WIDTH_DRAG, HEIGHT_DRAG);
        dragon_down[3] = dragonSheet.crop(752, 146, WIDTH_DRAG, HEIGHT_DRAG);

        dragon_attack = new BufferedImage[2];
        dragon_attack[0] = dragonSheet.crop(168, 354, WIDTH_DRAG/*width*/, HEIGHT_DRAG/*height*/);
        dragon_attack[1] = dragonSheet.crop(334, 354, WIDTH_DRAG, HEIGHT_DRAG);



        playButton = new BufferedImage[2];
        playButton[0] = buttonSheet.crop(0, 0, 60, 30);
        playButton[1] = buttonSheet.crop(60, 0, 60, 30);

        rockTile2 = new BufferedImage[2];
        rockTile2[0] = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        rockTile2[1] = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);

        dirtTile2 = new BufferedImage[3];
        dirtTile2[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
        dirtTile2[1] = sheet.crop(WIDTH*4, 0, WIDTH, HEIGHT);
        dirtTile2[2] = sheet.crop(WIDTH*6, 0, WIDTH, HEIGHT);

        grassTile2 = new BufferedImage[3];
        grassTile2[0] = sheet.crop(WIDTH*1, 0, WIDTH, HEIGHT);
        grassTile2[1] = sheet.crop(WIDTH*5, 0, WIDTH, HEIGHT);
        grassTile2[2] = sheet.crop(WIDTH*7, 0, WIDTH, HEIGHT);

        scryOrb = crystalBallSheet.crop(107, 104, WIDTH_ORB, HEIGHT_ORB);

//		scryOrb = new BufferedImage[15];
//		scryOrb[0] = crystalBallSheet.crop(52, 0, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[1] = crystalBallSheet.crop(104, 0, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[2] = crystalBallSheet.crop(0, 49, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[3] = crystalBallSheet.crop(52, 49, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[4] = crystalBallSheet.crop(104, 49, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[5] = crystalBallSheet.crop(0, 104, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[6] = crystalBallSheet.crop(52, 104, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[7] = crystalBallSheet.crop(107, 104, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[8] = crystalBallSheet.crop(52, 104, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[9] = crystalBallSheet.crop(0, 104, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[10] = crystalBallSheet.crop(104, 48, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[11] = crystalBallSheet.crop(52, 48, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[12] = crystalBallSheet.crop(0, 48, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[13] = crystalBallSheet.crop(104, 0, WIDTH_ORB, HEIGHT_ORB);
//		scryOrb[14] = crystalBallSheet.crop(52, 0, WIDTH_ORB, HEIGHT_ORB);


        backgroundImage = ImageLoader.loadImage("/textures/mBackground1500x937.png");

        dirt = sheet.crop(0, 0, WIDTH, HEIGHT);
        grass = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        stone = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        translucent = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);

        redDragon = dragonSheet.crop(546, 146, WIDTH_DRAG, HEIGHT_DRAG);
        plant1 = plantSheet.crop(236, 55, WIDTH_P, HEIGHT_P);
    }


}
