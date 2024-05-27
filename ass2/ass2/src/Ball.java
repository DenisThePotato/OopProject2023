//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Denis Mogilevsky.
 * ball class. has a middle location represented as Point, color and radius.
 */
public class Ball {

    private Point location = new Point(0, 0);
    private java.awt.Color color = new Color(0, 0, 0);
    private int radius;
    private Velocity velocity = new Velocity (0, 0);
    private SingleFrame frame;
    public SingleFrame getFrame() {
        return frame;
    }

    /**
     * constructor.
     * @param center ball center values, stored as Point.
     * @param r ball radius.
     * @param color ball color.
     */
    public Ball(Point center, int r, java.awt.Color color, SingleFrame frame) {
        this.location = center;
        this.color = color;
        this.radius = r;
        this.frame = frame;
    }

    /**
     * constructor.
     * @param center ball center values, stored as Point.
     * @param r ball radius.
     * @param color ball color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.location = center;
        this.color = color;
        this.radius = r;
    }

    /**
     * constructor.
     * @param xCenter
     * @param yCenter
     * @param r
     * @param color
     */
    public Ball(double xCenter, double yCenter, int r, java.awt.Color color) {
        this.location = new Point(xCenter, yCenter);
        this.color = color;
        this.radius = r;
    }

    /**
     * @return x value of location.
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * @return y value of location.
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * @return ball radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return ball color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface
     * @param surface surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) (this.location.getX()), (int) (this.location.getY()), this.radius);
    }

    /**
     *
     * @param v velocity to be set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx velocity of x axis.
     * @param dy velocity of y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.dx = dx;
        this.velocity.dy = dy;
    }

    /**
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * uses the applyToPoint method to move the location of ball.
     */
    public void moveOneStep() {
        //checks the next two next points.
        Point nextCenter = this.getVelocity().applyToPoint(this.location);
        if (!checkInboundsAndVelocity(nextCenter)) {
            nextCenter = this.getVelocity().applyToPoint(this.location);
            if (!checkInboundsAndVelocity(nextCenter)) {
                System.out.println("Velocity too high.");
                System.exit(0);
            }
        }
        this.location = nextCenter;
    }
    public boolean checkInboundsAndVelocity(Point center) {
        boolean ret = true;
        if (center.getX() - this.radius <= this.frame.getStart().getX()
                || center.getX() + this.radius >= this.frame.getEnd().getX()) { //checking x axis
            this.setVelocity(-this.getVelocity().dx, this.getVelocity().dy);
            ret = false;
        }
        if (center.getY() - this.radius <= this.frame.getStart().getY()
                || center.getY() + this.radius >= this.frame.getEnd().getY()) { //checking y axis
            this.setVelocity(this.getVelocity().dx, -this.getVelocity().dy);
            ret = false;
        }
        return ret;
    }
}
