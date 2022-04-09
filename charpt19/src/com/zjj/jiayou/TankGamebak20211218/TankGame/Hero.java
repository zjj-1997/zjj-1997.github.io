package com.zjj.jiayou.TankGamebak20211218.TankGame;

import com.zjj.jiayou.TankGame.Shot;
import com.zjj.jiayou.TankGame.Tank;

import java.util.Vector;

public class Hero extends Tank {
    Vector<Shot> heroShot=new Vector<>();
    public Hero(int x, int y, int direct, int type) {
        super(x, y, direct, type);
    }

    public void shotEnemyTank(){
        switch (direct){
            case 0:
                heroShot.add(new Shot(x+30,y,direct));
                break;
            case 1:
                heroShot.add(new Shot(x+60,y+30,direct));
                break;
            case 2:
                heroShot.add(new Shot(x+30,y+60,direct));
                break;
            case 3:
                heroShot.add(new Shot(x,y+30,direct));
                break;
        }
    }
}
