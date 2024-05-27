//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    double dx;   //change later- private
    double dy;   //change later- private

    /**
     * constructor.
     * @param dx change of position on x axis.
     * @param dy change of position on y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * factory.
     * @param angle y
     * @param speed x
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point to be returned.
     * @return new point with velocity added to its initial location values.
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return (new Point((p.getX() + this.dx),(p.getY() + this.dy)));
    }
}
