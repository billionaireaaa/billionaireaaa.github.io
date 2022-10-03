package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings("all")
//绘图区域
//为了让panel不停的重绘，需要将此类当做一个线程
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();//和ArraysList一样但是支持多线程，敌方的坦克
    Vector<Bomb> bombs = new Vector<>();//存放炸弹，当坦克被击中就加入一个bomb在bombs中
    Image image1 = null;//炸弹图片
    Image image2 = null;
    Image image3 = null;
    int enemyTankSize = 3;//设置敌方坦克的起始数量

    public MyPanel() {
        hero = new Hero(500, 100);//初始化自己坦克的位置
        hero.setSpeed(5);//设置自己坦克的速度
        //把敌人的坦克装入集合内
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);//设置敌人坦克的起始位置
            enemyTank.setDirect(2);//设置敌人坦克的方向
            new Thread(enemyTank).start();//启动线程让坦克开始自己移动
            //存入一个子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            //加入到Vector成员中
            enemyTank.shots.add(shot);
            //启动子弹（线程）
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        //初始化炸弹图片的对象
        image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\图片壁纸\\bomb_1.png");
        image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\图片壁纸\\bomb_2.png");
        image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\图片壁纸\\bomb_3.png");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认颜色为黑色，背景的颜色
        if (hero != null && hero.isLive) {//判断时一定要先判断是否为空在判断是否存活顺序不能错，否则会抛出异常
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);//画自己的坦克
        }
        //如果bombs集合中有对象就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让炸弹的生命值减小，可以让爆炸的显示比较流程
            bomb.lifeDown();
            //如果bomb的生命值为0就移除
            if (bomb.life <= 0) {
                bombs.remove(bomb);
            }
        }
        //画敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {//判断是否还存活，才画坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        //从Vector中移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
        //遍历集合画自己坦克发出的子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.fill3DRect(shot.x, shot.y, 2, 2, false);
            } else//如果Shot对象已经失效了则直接移除
            {
                hero.shots.remove(shot);
            }
        }

    }

//编写方法，画坦克

    /**
     * @param x      坦克的起始左上角的x坐标
     * @param y      坦克的起始左上角的y坐标
     * @param g      画笔
     * @param direct 坦克的起始方向（上下左右）
     * @param type   坦克的类型（敌人的坦克和我的坦克）
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //不同类型，不同颜色
        switch (type) {
            case 0://我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //表示不同的方向的坦克
        //direct中，0：向上，1：右，2：下，3：左
        switch (direct) {
            case 0://表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克的右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的身体
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出泡桐
                break;
            case 1://表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上面边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克的下面的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的身体
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出泡桐
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克的右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克的身体
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出泡桐
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上面边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克的下面的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克的身体
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出泡桐
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //因为我方可以发射多颗子弹，所以要遍历所有子弹去判断是否击中敌方的某辆坦克
    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive) {
                //遍历敌人坦克看看是否有会被子弹击中的
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    public void hitHero() {
        //遍历敌人的所有坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {//遍历每辆坦克的所有子弹
                Shot shot = enemyTank.shots.get(j);
                //判断是否击中我们的坦克
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    //判断我方坦克的子弹是否击中敌方坦克
    public void hitTank(Shot s, Tank Tank) {
        //判断子弹s是否击中坦克
        switch (Tank.getDirect()) {//坦克向上和下
            case 0:
            case 2:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 40 && s.y > Tank.getY() && s.y < Tank.getY() + 60) {
                    s.isLive = false;
                    Tank.isLive = false;
                    //创建Bomb对象加入到bombs中
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                    //被击中的坦克就从集合移除
                    enemyTanks.remove(Tank);
                }
                break;
            //坦克向左和右
            case 1:
            case 3:
                if (s.x > Tank.getX() && s.x < Tank.getX() + 60 && s.y > Tank.getY() && s.y < Tank.getY() + 40) {
                    s.isLive = false;
                    Tank.isLive = false;
                    //创建Bomb对象加入到bombs中
                    Bomb bomb = new Bomb(Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                }

        }
    }

    //有字符输入会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //当某个键按下会自动触发调用
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)//按下W键后
        {
            //方向的改变
            hero.setDirect(0);
            //坦克位置的移动
            if (hero.getY() > 0)//判断是否到边界，如果到边界则不会继续移动了
                hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000)
                hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S
            hero.setDirect(2);
            if (hero.getY() + 60 < 750)
                hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A
            hero.setDirect(3);
            if (hero.getX() > 0)
                hero.moveLeft();
        }
        //如果按下J就发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //判断子弹是否消亡,第一次进去是第一个条件，之后进去是第二个条件，注意线程结束了不代表对象就是空
            //每次只能发射一颗子弹的情况
          /*  if (hero.shot == null || hero.shot.isLive == false) {
                hero.shotEnemyTank();
            }*/
            //可以发射多颗子弹
            hero.shotEnemyTank();
        }
        //让面板重绘，一定要写不然就看不到变化
        this.repaint();
    }

    //当某个键释放会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //每隔100毫秒就重绘一下，相当于不停的去刷新绘图区域，这样子弹就可以移动起来了
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断是否击中敌人的坦克
            hitEnemyTank();
            //判断是否击中我方的坦克
            hitHero();
            this.repaint();
        }
    }
}
