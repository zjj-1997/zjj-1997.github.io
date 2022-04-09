package com.zjj.jiayou.TankGame;

import javax.swing.*;

@SuppressWarnings({"all"})
public class GameStart extends JFrame {
    MyPanel mp=null;
    public static void main(String[] args) {
        GameStart gameStart = new GameStart();
    }

    GameStart(){
        mp=new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
    }

}
