package tankgame;

/**
 * 子弹射击
 * 一颗子弹就是一个线程
 */
@SuppressWarnings("all")
public class Shot implements Runnable {
    int x;//子弹初始的横坐标
    int y;//子弹初始的纵坐标
    int direct = 0;//子弹的初始方向
    int speed = 2;//子弹的初始速度,就是子弹的飞行的速度
    boolean isLive = true;//子弹是否还存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            //休眠一下不然看不到子弹的移动
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据子弹的方向去移动
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            //超出了边界，则线程（子弹）就结束,或者击中坦克后
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }
        }
    }
}
