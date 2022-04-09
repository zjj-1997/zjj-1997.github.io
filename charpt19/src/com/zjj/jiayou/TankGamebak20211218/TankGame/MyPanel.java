package com.zjj.jiayou.TankGamebak20211218.TankGame;

import com.zjj.jiayou.TankGame.Bomb;
import com.zjj.jiayou.TankGame.EnemyTank;
import com.zjj.jiayou.TankGame.Hero;
import com.zjj.jiayou.TankGame.Shot;
import com.zjj.jiayou.TankGame.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings({"all"})
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    int enemyTanksize = 3;
    int enemyx = 100, enemyy = 10;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    MyPanel() {
        hero = new Hero(100, 100, 0, 0);
        for (int i = 0; i < enemyTanksize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0, 2, 1);
            enemyTanks.add(enemyTank);
            new Thread(enemyTank).start();
        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/3.png"));
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (hero != null && hero.heroShot.size() != 0) {
                for (int j = 0; j < hero.heroShot.size(); j++) {
                    Shot shot = hero.heroShot.get(j);
                    if (shot.isLive) {
                        for (int i = 0; i < enemyTanks.size(); i++) {
                            EnemyTank enemyTank = enemyTanks.get(i);
                            hitTank(shot, enemyTank);
                        }
                    }
                }
            }
            if (enemyTanks.size() != 0) {
                for (int j = 0; j < enemyTanks.size(); j++) {
                    for (int x = 0; x < enemyTanks.get(j).shots.size(); x++) {
                        Shot shot = enemyTanks.get(j).shots.get(x);
                        if (shot.isLive) {
                            if (hero != null && hero.isLive) {
                                hitTank(shot, hero);
                            }
                        }
                    }
                }
            }
            int count = (int) (Math.random() * 8);
            if (enemyTanks.size() > 0) {
                for (int z = 0; z < enemyTanks.size(); z++) {
                    if (enemyTanks.get(z).isLive) {
                        if (count == 1) {
                            enemyTanks.get(z).shotHeroTank();
                        }
                    }
                    for (int f = 0; f < enemyTanks.get(z).shots.size(); f++) {
                        new Thread(enemyTanks.get(z).shots.get(f)).start();
                    }
                }
            }

            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        if (hero != null&&hero.isLive) {
            drawTank(hero, g);
        }
        if (hero != null && hero.heroShot.size() != 0) {
            for (int z = 0; z < hero.heroShot.size(); z++) {
                Shot shot = hero.heroShot.get(z);
                if (shot.isLive != false) {
                    new Thread(shot).start();
                    g.draw3DRect(shot.x, shot.y, 2, 2, false);
                }
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int t = 0; t < bombs.size(); t++) {
            Bomb bomb = bombs.get(t);
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bomb.isLive = false;
                bombs.remove(bomb);
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank, g);

                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.fill3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }

    /**
     * drawTank draw tank
     *
     * @param tank
     * @param g
     */
    public void drawTank(Tank tank, Graphics g) {
        switch (tank.type) {
            case 0:
                g.setColor(Color.YELLOW);
                break;
            case 1:
                g.setColor(Color.CYAN);
                break;
        }

        switch (tank.direct) {
            case 0:
                g.fill3DRect(tank.x, tank.y, 10, 60, false);
                g.fill3DRect(tank.x + 10, tank.y + 10, 40, 40, false);
                g.fill3DRect(tank.x + 50, tank.y, 10, 60, false);
                g.fillOval(tank.x + 10, tank.y + 10, 40, 40);
                g.drawLine(tank.x + 30, tank.y, tank.x + 30, tank.y + 30);
                break;
            case 1:
                g.fill3DRect(tank.x, tank.y, 60, 10, false);
                g.fill3DRect(tank.x + 10, tank.y + 10, 40, 40, false);
                g.fill3DRect(tank.x, tank.y + 50, 60, 10, false);
                g.fillOval(tank.x + 10, tank.y + 10, 40, 40);
                g.drawLine(tank.x + 60, tank.y + 30, tank.x + 30, tank.y + 30);
                break;
            case 2:
                g.fill3DRect(tank.x, tank.y, 10, 60, false);
                g.fill3DRect(tank.x + 10, tank.y + 10, 40, 40, false);
                g.fill3DRect(tank.x + 50, tank.y, 10, 60, false);
                g.fillOval(tank.x + 10, tank.y + 10, 40, 40);
                g.drawLine(tank.x + 30, tank.y + 60, tank.x + 30, tank.y + 30);
                break;
            case 3:
                g.fill3DRect(tank.x, tank.y, 60, 10, false);
                g.fill3DRect(tank.x + 10, tank.y + 10, 40, 40, false);
                g.fill3DRect(tank.x, tank.y + 50, 60, 10, false);
                g.fillOval(tank.x + 10, tank.y + 10, 40, 40);
                g.drawLine(tank.x, tank.y + 30, tank.x + 30, tank.y + 30);
                break;
        }
    }

    public void hitTank(Shot s, Tank enemyTank) {
        if (s.x > enemyTank.x && s.x < enemyTank.x + 60
                && s.y > enemyTank.y && s.y < enemyTank.y + 60) {
            s.isLive = false;
            enemyTank.isLive = false;
            if(enemyTank instanceof EnemyTank){
                enemyTanks.remove(enemyTank);
            }
            Bomb bomb = new Bomb(enemyTank.x, enemyTank.y);
            bombs.add(bomb);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                hero.direct = 0;
                if (hero.y - hero.speed >= 0) {
                    hero.y -= hero.speed;
                }
                break;
            case KeyEvent.VK_D:
                hero.direct = 1;
                if (hero.x + hero.speed <= 1000) {
                    hero.x += hero.speed;
                }
                break;
            case KeyEvent.VK_S:
                hero.direct = 2;
                if (hero.y + hero.speed <= 750) {
                    hero.y += hero.speed;
                }
                break;
            case KeyEvent.VK_A:
                hero.direct = 3;
                if (hero.x - hero.speed >= 0) {
                    hero.x -= hero.speed;
                }
                break;
            case KeyEvent.VK_J:
                hero.shotEnemyTank();
                break;

        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
