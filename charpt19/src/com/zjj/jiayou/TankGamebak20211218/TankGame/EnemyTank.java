package com.zjj.jiayou.TankGamebak20211218.TankGame;

import com.zjj.jiayou.TankGame.Shot;
import com.zjj.jiayou.TankGame.Tank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots =new Vector<>();
    public EnemyTank(int x, int y, int direct, int type) {
        super(x, y, direct, type);
    }

    public void shotHeroTank(){

        switch (direct){
            case 0:
                shots.add(new Shot(x+30,y,direct));
                break;
            case 1:
                shots.add(new Shot(x+60,y+30,direct));
                break;
            case 2:
                shots.add(new Shot(x+30,y+60,direct));
                break;
            case 3:
                shots.add(new Shot(x,y+30,direct));
                break;
        }
    }

    @Override
    public void run() {
        int randomdirect=0;
        int count=0;
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count%30==0){
                randomdirect=(int)(Math.random()*4);
            }
            count++;
            switch (randomdirect) {
                case 0:
                    direct = 0;
                    if(y - speed>=0){
                        y -= speed;
                    }
                    break;
                case 1:
                    direct = 1;
                    if(x + speed<=1000){
                        x += speed;
                    }
                    break;
                case 2:
                    direct = 2;
                    if(y + speed<=750){
                        y += speed;
                    }
                    break;
                case 3:
                    direct = 3;
                    if(x-speed>=0){
                        x -= speed;
                    }
                    break;
            }
            if(!isLive){
                break;
            }

        }
    }
}
