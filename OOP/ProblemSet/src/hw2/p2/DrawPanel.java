package hw2.p2;

import javax.swing.JPanel;
import java.awt.*;


public class DrawPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Object[] objects;

    public DrawPanel() {
        setBackground(Color.BLACK);
    }
    public DrawPanel(MyShape[] s) {
        setBackground(Color.WHITE);
        objects = s;
    }

    // @TODO deal with the following things.
    public DrawPanel(Object o){
        setBackground(Color.WHITE);
        objects = new Object[1];
        objects[0] = o;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (objects == null){
            g.drawString("在DrawPanel的构造函数中，你传递的引用参数是NULL。", 50, 50);
            return;
        }

        if (!objects.getClass().isArray()) {
            g.drawString("在DrawPanel的构造函数中，你传递的引用参数必须是某个形状的数组类型。", 50, 50);
            return;
        }
        for (Object o : objects){
            if(o instanceof Drawable)
                ((Drawable)o).draw(g);
            else g.drawString(objects.getClass().getName() + "doesn't implements Drawable",50,50);
        }

    }
}


