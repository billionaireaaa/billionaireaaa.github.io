package tankgame;

import java.util.Vector;
@SuppressWarnings("all")
public class Hero extends Tank {
    //定义一个Shot，表示一个射击（线程）
    Shot shot = null;
    //用集合保存子弹，让我们的坦克可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    //射击，打出子弹
    public void shotEnemyTank() {
        //面板中自己的子弹最多只能有6颗
        if (shots.size()==6)
            return;
        //根据当前hero对象来创建shot对象
        switch (getDirect())//得到Hero对象的方向
        {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);//把新建的子弹加入到集合中
        // 启动Shot线程
        new Thread(shot).start();
    }
}
