package tankgame;

import java.util.Vector;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable {
    //在敌人的坦克类，使用Vector保存多个shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;//坦克是否存活

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (isLive && shots.size() <=3 ) {//创建一个子弹放入,让敌人的坦克最多只能发射3个子弹
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克的方向去移动
            switch (getDirect()) {//上右下左
                case 0://让坦克一次移动30步(可以自己调节），因为如果移动步数太少，就看起来很乱
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0)//判断坦克，如果到达了边界就不在继续移动
                            moveUp();
                        //休眠一下，否则移动迅速
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000)
                            moveRight();
                        //休眠一下，否则移动迅速
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750)
                            moveDown();
                        //休眠一下，否则移动迅速
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0)
                            moveLeft();
                        //休眠一下，否则移动迅速
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //让坦克方向随机改变
            setDirect((int) (Math.random() * 4));
            //写多线程的并发程序时一定要注意什么时候让线程退出，否则就会一直执行
            if (!isLive) {
                break;
            }
        }
    }
}
