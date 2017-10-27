package com.martyworm.entities.tools;

import com.martyworm.board.Tile;
import com.martyworm.entities.Entity;
import com.martyworm.game.Game;
import com.martyworm.gui.Controller;

import java.util.ArrayList;

public class PathFinder {

    private static Tile farthestTile;
    private static Tile destination;

//    public static void highlightPath(Controller c, Entity e, ArrayList<Tile> tiles, int movesAvailable, ArrayList<Tile> movePath) {
//
//        setMoveValues(tiles, e);
//        destination = getDestination(tiles);
//
//
//        if (destination != null) {
//            tiles.sort(Comparator.comparingInt(Tile::getMoveValue));
//            movePath.set(0, tiles.get(0));
//            for(int i = 0; i < destination.getgValue(); i++){
//                //I DUNNO MAN
////                        if(tiles.get(j).getMoveValue() <= 4) {
////                            if (movePath.get(i - 1) != null && isAdjacent(movePath.get(i - 1), tiles.get(j))) {
////                                movePath.set(i, tiles.get(j));
////                            }
////                        }
////                    }
//                }
//
//
//                for (int j = movePath.size() - 1; j > 0; j--) {
//                    if (destination.getgValue() == j - 1) {
//                        movePath.set(j, null);
//                    }
//                }
//            }
//
//
//
//
//        for(Tile t : tiles){
////            System.out.println("INDEX: " + tiles.indexOf(t) + ".. ID:  " + t.getId() +  ", gVal " + t.getgValue() + " |, hVal " + t.gethValue() + " |, Val: " + t.getMoveValue());
//            if(movePath.contains(t)){
//                t.setHighlighted(true);
//            }else{
//                t.setHighlighted(false);
//            }
//        }
////        for(Tile t : tiles){
////            if(movePath.contains(t)){
////                t.setHighlighted(true);
////            }else{
////                t.setHighlighted(false);
////            }
////        }
//
////        if(destination != null){
////            for(Tile t : tiles) {
////                for (int i = 0; i < movesAvailable; i++) {
////                    if (movePath.get(i) == null) {
////                        if(t.getMoveValue())
////                    }
////                }
////            }
////        }
//
//    }

    public static void setMoveValues(ArrayList<Tile> tiles, Entity e) {
        if (!e.isMoving())
            for (Tile t : tiles) {
                if (t.getHoveringTile() != null) {
                    int destinationX = t.getHoveringTile().getxPos();
                    int destinationY = t.getHoveringTile().getyPos();
                    int minionX = e.getOccupiedTileEnt().getxPos();
                    int minionY = e.getOccupiedTileEnt().getyPos();
                    for (Tile h : tiles) {
                        h.sethValue((Math.abs(destinationX - h.getxPos()) / 40) + (Math.abs(destinationY - h.getyPos()) / 40));
                        h.setgValue((Math.abs(h.getxPos() - minionX) / 40) + (Math.abs(h.getyPos() - minionY) / 40));
                        h.setMoveValue((h.gethValue() + h.getgValue()));

                    }

                }
            }
    }


    private static Tile getDestination(ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            if (t.isHovering()) {
                return t;
            }
        }
        return null;
    }

    private static boolean isAdjacent(Tile t1, Tile t2) {
        int checkTile = t2.getId();
        int current = t1.getId();
        if (checkTile + 1 == current
                || checkTile - 1 == current
                || checkTile - 21 == current
                || checkTile + 21 == current) {
            return true;
        }

        return false;
    }

    //WORKING WITHOUT PATHFINDING
    public static void highlightPath(Controller c, Entity e, Tile t) {

        if (e.getOccupiedTileEnt() != null) {

            int currentX = e.getOccupiedTileEnt().getxPos();
            int currentY = e.getOccupiedTileEnt().getyPos();

            int occupiedTileId = e.getOccupiedTileEnt().getId();

            if (c.getHitBox().intersects(t.getHitBox()) && t.isSemiHighlited()) {//if tile is semiHighlighted and mouse is on it...

                int destinationX = t.getxPos();
                int desintationY = t.getyPos();
                int xDistance = destinationX - currentX;
                int yDistance = desintationY - currentY;

                if (e.getXDiff() >= e.getYDiff()) {
                    drawXPath(xDistance, e, occupiedTileId);
                    if (farthestTile != null) {
                        drawYPath(yDistance, e, farthestTile.getId());
                    }
                } else if (e.getXDiff() < e.getYDiff()) {
                    drawYPath(yDistance, e, occupiedTileId);
                    if (farthestTile != null) {
                        drawXPath(xDistance, e, farthestTile.getId());
                    }
                }
            }
        }


    }

    private static void drawXPath(int xDistance, Entity e, int occupiedTileId) {
        if (xDistance >= 0) {
            for (int i = 0; i <= xDistance / 10; i += 4) {
                e.getTileByIdEnt(occupiedTileId + (i / 4)).setHighlighted(true);
                if (i == xDistance / 10) {
                    setFarthestTile(e.getTileByIdEnt(occupiedTileId + (i / 4)));
                }
            }
        } else if (xDistance < 0) {
            for (int j = 0; j >= xDistance / 10; j -= 4) {
                e.getTileByIdEnt(occupiedTileId + (j / 4)).setHighlighted(true);
                if (j == xDistance / 10) {
                    setFarthestTile(e.getTileByIdEnt(occupiedTileId + (j / 4)));
                }
            }
        }
    }

    private static void drawYPath(int yDistance, Entity e, int occupiedTileId) {
        int upDown = 21;
        if (yDistance >= 0) {
            for (int i = 0; i <= yDistance / 10; i += 4) {
                e.getTileByIdEnt(occupiedTileId + (upDown * (i / 4))).setHighlighted(true);
                if (i == yDistance / 10) {
                    setFarthestTile(e.getTileByIdEnt(occupiedTileId + (upDown * (i / 4))));
                }
            }
        } else if (yDistance < 0) {
            for (int j = 0; j >= yDistance / 10; j -= 4) {
                e.getTileByIdEnt(occupiedTileId + (upDown * (j / 4))).setHighlighted(true);
                if (j == yDistance / 10) {
                    setFarthestTile(e.getTileByIdEnt(occupiedTileId + (upDown * (j / 4))));
                }
            }
        }
    }

    private static Tile setFarthestTile(Tile t) {
        farthestTile = t;
        return farthestTile;
    }


    public static void SemiHighlightToggle(Game game, Entity e, int maxMoves) {
        if (e.getOccupiedTileEnt() != null) {
            for (Tile t : game.getTileManager().getTiles()) {
                int currentX = e.getOccupiedTileEnt().getxPos();
                int currentY = e.getOccupiedTileEnt().getyPos();
                int tCurrentX = t.getxPos();
                int tCurrentY = t.getyPos();

                int xDistance = Math.abs(tCurrentX - currentX) / 40;
                int yDistance = Math.abs(tCurrentY - currentY) / 40;

                int sumDistance = xDistance + yDistance;

                if(sumDistance <= maxMoves && !t.isSolid()){
                    t.setSemiHighlited(true);
                }
                else{
                    t.setSemiHighlited(false);
                }
                t.setHighlighted(false);
            }
        }
    }


}






















//    public static void SemiHighlightToggle(Game game, Entity e, int maxMoves) {
//        Iterator var3 = game.getTileManager().getTiles().iterator();
//
//        while(var3.hasNext()) {
//            Tile t = (Tile)var3.next();
//            if (e.getXDiff() == 0 && e.getYDiff() == 0 && maxMoves > 0) {
//                if (t.getHitBox().contains(e.getHitBox())) {
//                    int recurseInt = 1;
//                    rightCheck(game, recurseInt, t.getId(), maxMoves);
//                    upCheck(game, recurseInt, t.getId(), maxMoves);
//                    leftCheck(game, recurseInt, t.getId(), maxMoves);
//                    downCheck(game, recurseInt, t.getId(), maxMoves);
//                }
////                if(e.getOccupiedTileEnt() != null) {
//                int currentX = e.getOccupiedTileEnt().getxPos();
//                int currentY = e.getOccupiedTileEnt().getyPos();
//                int tCurrentX = t.getxPos();
//                int tCurrentY = t.getyPos();
////
//                int xDistance = Math.abs(tCurrentX - currentX) / 40;
//                int yDistance = Math.abs(tCurrentY - currentY) / 40;
//
//                int sumDistance = xDistance + yDistance;
//                if (sumDistance > maxMoves) {
//                    t.setSemiHighlited(false);
//                }
//
////                }
//                t.setHighlighted(false);
//
//            }
//
//        }
//
//    }
//
//    private static void upCheck(Game game, int x, int ID, int maxMoves) {
//        int up = 21;
//
//        while(x <= maxMoves) {
//            if (game.getTile(ID - up) != null) {
//                if (game.getTile(ID - up).isSolid()) {
//                    return;
//                }
//
//                game.getTile(ID - up).setSemiHighlited(true);
//                if (x == 1) {
//                    rightCheck(game, 1, ID - up, maxMoves - 1);
//                    leftCheck(game, 1, ID - up, maxMoves - 1);
//                }
//
//                if (x == 2) {
//                    rightCheck(game, 1, ID - up, maxMoves - 2);
//                    leftCheck(game, 1, ID - up, maxMoves - 2);
//                }
//
//                if (x == 3) {
//                    rightCheck(game, 1, ID - up, maxMoves - 3);
//                    leftCheck(game, 1, ID - up, maxMoves - 3);
//                }
//            }
//
//            up += 21;
//            ++x;
//            upCheck(game, x, ID, maxMoves);
//        }
//
//    }
//
//    private static void rightCheck(Game game, int x, int ID, int maxMoves) {
//        while(x <= maxMoves) {
//            if (game.getTile(ID + x) != null) {
//                if (game.getTile(ID + x).isSolid()) {
//                    return;
//                }
//
//                game.getTile(ID + x).setSemiHighlited(true);
//                if (x == 1) {
//                    upCheck(game, 1, ID + x, maxMoves - 1);
//                    downCheck(game, 1, ID + x, maxMoves - 1);
//                }
//
//                if (x == 2) {
//                    upCheck(game, 1, ID + x, maxMoves - 2);
//                    downCheck(game, 1, ID + x, maxMoves - 2);
//                }
//
//                if (x == 3) {
//                    upCheck(game, 1, ID + x, maxMoves - 3);
//                    downCheck(game, 1, ID + x, maxMoves - 3);
//                }
//            }
//
//            ++x;
//            rightCheck(game, x, ID, maxMoves);
//        }
//
//    }
//
//    private static void leftCheck(Game game, int x, int ID, int maxMoves) {
//        while(x <= maxMoves) {
//            if (game.getTile(ID - x) != null) {
//                if (game.getTile(ID - x).isSolid()) {
//                    return;
//                }
//
//                game.getTile(ID - x).setSemiHighlited(true);
//                if (x == 1) {
//                    upCheck(game, 1, ID - x, maxMoves - 1);
//                    downCheck(game, 1, ID - x, maxMoves - 1);
//                }
//
//                if (x == 2) {
//                    upCheck(game, 1, ID - x, maxMoves - 2);
//                    downCheck(game, 1, ID - x, maxMoves - 2);
//                }
//
//                if (x == 3) {
//                    upCheck(game, 1, ID - x, maxMoves - 3);
//                    downCheck(game, 1, ID - x, maxMoves - 3);
//                }
//            }
//
//            ++x;
//            leftCheck(game, x, ID, maxMoves);
//        }
//
//    }
//
//    private static void downCheck(Game game, int x, int ID, int maxMoves) {
//        int up = 21;
//
//        while(x <= maxMoves) {
//            if (game.getTile(ID + up) != null) {
//                if (game.getTile(ID + up).isSolid()) {
//                    return;
//                }
//
//                game.getTile(ID + up).setSemiHighlited(true);
//                if (x == 1) {
//                    rightCheck(game, 1, ID + up, maxMoves - 1);
//                    leftCheck(game, 1, ID + up, maxMoves - 1);
//                }
//
//                if (x == 2) {
//                    rightCheck(game, 1, ID + up, maxMoves - 2);
//                    leftCheck(game, 1, ID + up, maxMoves - 2);
//                }
//
//                if (x == 3) {
//                    rightCheck(game, 1, ID + up, maxMoves - 3);
//                    leftCheck(game, 1, ID + up, maxMoves - 3);
//                }
//            }
//
//            up += 21;
//            ++x;
//            downCheck(game, x, ID, maxMoves);
//        }

