package com.zjj.jiayou.TankGamebak20211218.TankGame;

import javax.swing.*;

@SuppressWarnings({"all"})
public class GameStart extends JFrame {
    com.zjj.jiayou.TankGame.MyPanel mp=null;
    public static void main(String[] args) {
        com.zjj.jiayou.TankGame.GameStart gameStart = new com.zjj.jiayou.TankGame.GameStart();
    }

    GameStart(){
        mp=new com.zjj.jiayou.TankGame.MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
    }

}
