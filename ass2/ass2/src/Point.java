//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * A class representing dots.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x is the x value of point.
     * @param y is the y value of point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other point being compared with.
     * @return distance between the two points.
     */
    public double distance(Point other) {
        double distanceX = this.x - other.x;
        double distanceY = this.y - other.y;
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
    }

    /**
     * @param other location is compared to given point.
     * @return true if and only if the points are equal.
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * @return x value of given point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return y value of given point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setter.
     * @param newX x to be set.
     */
    public void setX(double newX) {
        x = newX;
    }

    /**
     * setter.
     * @param newY y to be set.
     */
    public void setY(double newY) {
        y = newY;
    }
}