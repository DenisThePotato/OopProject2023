//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * a class representing frames.
 */
public class SingleFrame {
    private Point start;
    private Point end;
    private java.awt.Color color = new Color(0, 0, 0);


    /**
     * constructor.
     * @param start starting point.
     * @param end ending point.
     * @param color color of frame.
     */
    public SingleFrame(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * getter.
     * @return start point.
     */
    public Point getStart() {
        return start;
    }

    /**
     * getter.
     * @return end point.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * draws the frame.
     * @param d DrawSurface.
     */
    public void draw(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) start.getX(), (int) start.getY(),
                (int) end.getX() - (int) start.getX(), (int) end.getY() - (int) start.getY());
    }
}
