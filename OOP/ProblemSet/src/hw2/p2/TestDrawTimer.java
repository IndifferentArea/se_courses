package hw2.p2;

import hw2.p1.MyTime;

import javax.swing.*;

public class TestDrawTimer {
    public static int APP_WIDTH = 400;
    public static int APP_HEIGHT = 300;

    public static void main(String[] args) throws InterruptedException {
        MyTime Test_Time =  new MyTime(21, 30, 15);
        DrawPanel timer_panel = new DrawPanel(Test_Time);
        JFrame application = new JFrame();
        application.setTitle("面向对象程序设计第2次作业");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(timer_panel);
        application.setSize(APP_WIDTH, APP_HEIGHT);
        application.setVisible(true);
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); //设置绘制的时间间隔为 1 秒
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                    //正好也可以用来检验第 1 道题目中时钟类型的这个方法是否正确
                    Test_Time.incrementSecond();
                    //更新绘制图形面板上的内容（也就是绘制的图像）
                    timer_panel.updateUI();
                }
            }
        };
        t.start();
    }

}
