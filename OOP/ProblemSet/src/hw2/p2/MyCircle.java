package hw2.p2;

import java.awt.*;

public class MyCircle extends MyShape implements calcAreable {
	private int x_position;
	private int y_position;
	private int radix;
	private Color context_Color;
	private Color line_Color;

	public MyCircle() {
		x_position = 0;
		y_position = 0;
		radix = 0;
		line_Color = Color.black;
		context_Color = Color.white;
	}

	public MyCircle(int r) {
		this();
		radix = r;
	}

	public  MyCircle(int r, Color cc) {
		this(r);
		context_Color = cc;
	}

	public  MyCircle(int r, Color cc, Color lc) {
		this(r, cc);
		line_Color = lc;
	}

	public MyCircle(int x, int y, int r) {
		this(r);
		x_position = x;
		y_position = y;
	}

	public MyCircle(int x, int y, int r, Color cc) {
		this(x, y, r);
		context_Color = cc;
	}

	public MyCircle(int x, int y, int r, Color cc, Color lc) {
		this(x, y, r, cc);
		line_Color = lc;
	}

	// Generate a circle taking line as diameter, just as an example, so no more Color-related method.
	public MyCircle(MyLine line){
		this(line.getX1()+ line.getX2() >>1, line.getY1()+ line.getY2()>>1,
				line.getLength()>>1, line.getColor());
	}

	public int getX_position() {
		return x_position;
	}

	public int getY_position() {
		return y_position;
	}

	public int getRadix() {
		return radix;
	}

	public void setContext_Color(Color cc){
		context_Color = cc;
	}

	public Color getContext_Color() {
		return context_Color;
	}

	public Color getLine_Color() {
		return line_Color;
	}

	public void draw(Graphics g) {
		g.setColor(line_Color);
		g.drawOval(x_position - radix, y_position - radix, 2*radix, 2*radix);
		g.setColor(context_Color);
		g.fillOval(x_position - radix, y_position - radix, 2*radix, 2*radix);
	}

	@Override
	public double area() {
		return Math.pow(radix,2)*Math.PI;
	}
}
