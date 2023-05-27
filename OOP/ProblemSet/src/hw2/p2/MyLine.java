package hw2.p2;

import java.awt.Graphics;
import java.awt.Color;
public class MyLine extends MyShape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	public MyLine(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	public MyLine(int x, int y, int length, double rad, Color c) {
		this(x, y, x + (int) (length * Math.cos(rad)), y + (int) (length * Math.sin(rad)),c);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	public Color getColor() {
		return color;
	}

	public int getLength() {
		return (int) Math.round(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
	}
}
