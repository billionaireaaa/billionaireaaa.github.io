package tankgame;

import javax.swing.*;
import java.awt.*;

public class TankGame extends JFrame {//相当于是一个画框的部分
    MyPanel mp = null;//相当于是画纸

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1200, 900);
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
