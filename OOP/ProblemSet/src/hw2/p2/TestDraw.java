package hw2.p2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestDraw {
    public static int APP_WIDTH = 400;
    public static int APP_HEIGHT = 300;

    public static void main(String[] args) throws InterruptedException {
        DrawPanel line_panel = new DrawPanel(generateLines());
        DrawPanel circle_panel = new DrawPanel(generateCircles());
        DrawPanel rec_panel = new DrawPanel(generateRectangles());
        JFrame application = new JFrame();
        application.setTitle("面向对象程序设计第2次作业");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.setSize(APP_WIDTH, APP_HEIGHT);
        application.setVisible(true);

        application.add(rec_panel);
        Thread.sleep(5000);
        rec_panel.setVisible(false);

        application.add(circle_panel);
        Thread.sleep(5000);
        circle_panel.setVisible(false);

        application.add(line_panel);
        Thread.sleep(5000);
        line_panel.setVisible(false);
    }

    public static MyLine[] generateLines() {
        Random randomNumber = new Random();
        MyLine[] lines;
        lines = new MyLine[5 + randomNumber.nextInt(5)];
        for (int count = 0; count < lines.length; count++) {
            int x1 = randomNumber.nextInt(APP_WIDTH);
            int y1 = randomNumber.nextInt(APP_HEIGHT);
            int x2 = randomNumber.nextInt(x1-60,x1+60);
            int y2 = randomNumber.nextInt(y1-60,y1+60);
            Color color = new Color(randomNumber.nextInt(256), randomNumber.nextInt(256),
                    randomNumber.nextInt(256));
            lines[count] = new MyLine(x1, y1, x2, y2, color);
        }
        return lines;
    }

    public static MyCircle[] generateCircles() {
        Random randomNumber = new Random();
        MyLine[] lines = generateLines();
        MyCircle[] circles = new MyCircle[lines.length];
        for (int count = 0; count < lines.length; count++) {
            Color color = new Color(randomNumber.nextInt(256), randomNumber.nextInt(256),
                    randomNumber.nextInt(256));
            circles[count] = new MyCircle(lines[count]);
            circles[count].setContext_Color(color);
        }
        return circles;
    }

    public static MyRectangle[] generateRectangles() {
        MyCircle[] circles = generateCircles();
        MyRectangle[] rectangles;
        rectangles = new MyRectangle[circles.length];
        for (int count = 0; count < rectangles.length; count++) {
            rectangles[count] = new MyRectangle(circles[count]);
        }
        return rectangles;
    }
}

