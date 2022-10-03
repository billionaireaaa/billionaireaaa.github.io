package java绘图.DrawCircle;

import javax.swing.*;
import java.awt.*;

public class DC extends JFrame {

    private MyPanel mp=null;

    public static void main(String[] args) {
        new DC();
    }

    public DC()
    {
        mp=new MyPanel();
        this.add(mp);
        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
class MyPanel extends JPanel
{
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawOval(30,30,300,300);
    }
}
