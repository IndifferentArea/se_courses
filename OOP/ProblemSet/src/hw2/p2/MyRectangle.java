package hw2.p2;

import java.awt.*;

public class MyRectangle extends MyShape implements calcAreable {
	private int x_Low;
	private int y_Low;
	private int x_High;
	private int y_High;
	Color context_Color;
	Color line_Color;

	public MyRectangle() {
		x_Low = 0;
		y_Low = 0;
		x_High = 0;
		y_High = 0;
		line_Color = Color.black;
		context_Color = Color.white;
	}

	public MyRectangle(int x2, int y2) {
		this();
		x_High = x2;
		y_High = y2;
	}

	public MyRectangle(int x2, int y2, Color cc) {
		this(x2, y2);
		context_Color = cc;
	}

	public MyRectangle(int x2, int y2, Color cc, Color lc) {
		this(x2, y2, cc);
		line_Color = lc;
	}

	public MyRectangle(int x1, int y1, int x2, int y2) {
		this(x2, y2);
		x_Low = x1;
		y_Low = y1;
	}

	public MyRectangle(int x1, int y1, int x2, int y2, Color cc) {
		this(x1, y1, x2, y2);
		context_Color = cc;
	}

	public MyRectangle(int x1, int y1, int x2, int y2, Color cc, Color lc) {
		this(x1, y1, x2, y2, cc);
		line_Color = lc;
	}

	// Generate a rectangle taking line as diagonal just as an example, so no more Color-related method.
	public MyRectangle(MyLine line) {
		this(Math.min(line.getX1(), line.getX2()), Math.min(line.getY1(), line.getY2()),
				Math.max(line.getX1(), line.getX2()), Math.max(line.getY1(), line.getY2()), line.getColor());
	}

	public MyRectangle(MyCircle circle) {
		this(circle.getX_position() - circle.getRadix(), circle.getY_position() - circle.getRadix(),
				circle.getX_position() + circle.getRadix(), circle.getY_position() + circle.getRadix(),
				circle.getContext_Color(), circle.getLine_Color());
	}

	public void draw(Graphics g) {
		g.setColor(line_Color);
		g.drawRect(x_Low, y_Low, x_High - x_Low, y_High - y_Low);
		g.setColor(context_Color);
		g.fillRect(x_Low, y_Low, x_High - x_Low, y_High - y_Low);
	}

	@Override
	public double area() {
		return (x_High - x_Low)*(y_High - y_Low);
	}
}
