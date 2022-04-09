package com.zjj.jiayou.TankGamebak20211218.TankGame;

public class Tank {
    int x,y;
    int direct=8;
    int speed=3;
    int type;
    boolean isLive=true;
    public Tank(int x, int y, int direct,int type) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.type=type;
    }


}
