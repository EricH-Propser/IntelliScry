package com.martyworm.board;

public enum TileType {
    GrassTile('0'),
    DirtTile('1'),
    RockTile('2');

    public char value;

    TileType(char value){
        this.value = value;
    }

    public static TileType fromChar(char value){
        for(TileType tt : TileType.values()){
            if(tt.value == value){
                return tt;
            }
        }

        //todo: throw exception?
        System.out.println("ERROR: Unsupported TileType: " + value);

        return null;
    }
}
