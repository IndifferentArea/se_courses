package hw2.p1;

import hw2.p2.Drawable;
import hw2.p2.MyLine;

import java.awt.*;

public class MyTime implements Drawable {
    int hour;
    int minute;
    int second;

    MyTime() {
        hour = 0;
        minute = 0;
        second = 0;
    }

    MyTime(int h) {
        this();
        hour = h;
    }

    public MyTime(int h, int m) {
        this(h);
        minute = m;
    }

    public MyTime(int h, int m, int s) {
        this(h, m);
        second = s;
    }

    public MyTime(MyTime t4) {
        this(t4.hour, t4.minute, t4.second);
    }

    boolean ValidJudge(int num) {
        return num < 0 || num >= 60;
    }

    public String toUniversalString() {
        boolean flag = true;
        String s = "";

        if (ValidJudge(hour)) {
            flag = false;
            s += "hour must be 0-23\n";
        }
        if (ValidJudge(minute)) {
            flag = false;
            s += "minute must be 0-59\n";
        }
        if (ValidJudge(second)) {
            flag = false;
            s += "second must be 0-59\n";
        }
        if (flag) return String.format("   %02d:%02d:%02d", hour, minute, second);
        else return s;
    }

    @Override
    public String toString() {
        String s;
        s = (hour % 12 == 0) ? String.format("   %02d:%02d:%02d ", 12, minute, second) :
                String.format("   %02d:%02d:%02d ", hour % 12, minute, second);
        s += (hour < 12) ? "AM" : "PM";
        return s;
    }

    public void incrementHour() {
        if (++hour >= 24) hour = 0;
    }

    public void incrementMinute() {
        if (++minute >= 60) {
            minute = 0;
            incrementHour();
        }
    }

    public void incrementSecond() {
        if (++second >= 60) {
            second = 0;
            incrementMinute();
        }
    }

    @Override
    public void draw(Graphics g) {
        final int CENTER_X = 200, CENTER_Y = 150, RADIX = 100, FONT_SIZE = 20;
        g.drawOval(CENTER_X - RADIX, CENTER_Y - RADIX, 2 * RADIX, 2 * RADIX);

        Font font = new Font("Arial", Font.PLAIN, FONT_SIZE);
        g.setFont(font);
        g.drawString("12", CENTER_X - FONT_SIZE / 2, CENTER_Y - RADIX + FONT_SIZE);
        g.drawString("3", CENTER_X + RADIX - FONT_SIZE / 2 - 2, CENTER_Y + FONT_SIZE / 3);
        g.drawString("6", CENTER_X - FONT_SIZE / 4, CENTER_Y + RADIX);
        g.drawString("9", CENTER_X - RADIX + FONT_SIZE / 2 - 2, CENTER_Y + FONT_SIZE / 3);

        double second_angle = (float) second / 30 * Math.PI;
        MyLine second_line = new MyLine(CENTER_X, CENTER_Y, 80, second_angle - Math.PI / 2, Color.red);
        second_line.draw(g);

        double minute_angle = (float) minute/ 30 * Math.PI + second_angle / 60;
        MyLine minute_line = new MyLine(CENTER_X, CENTER_Y, 60, minute_angle - Math.PI / 2, Color.green);
        minute_line.draw(g);

        double hour_angle = (float)hour/6 *Math.PI + minute_angle / 12;
        MyLine hour_line = new MyLine(CENTER_X, CENTER_Y, 40, hour_angle - Math.PI / 2, Color.blue);
        hour_line.draw(g);

        g.setColor(Color.black);
        g.drawString(this.toUniversalString(),CENTER_X - RADIX/2, CENTER_Y - RADIX - FONT_SIZE );
    }
}
