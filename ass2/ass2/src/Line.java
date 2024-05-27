//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * A class representing lines.
 */
public class Line {
    private Point start = new Point(0, 0);
    private Point end = new Point(0, 0);
    private double slope;
    private double intercept;

    /**
     * getter.
     * @return starting point.
     */
    public Point getStart() {
        return start;
    }

    /**
     * getter.
     * @return ending point.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Constructor.
     * @param start starting point of line.
     * @param end ending point of line.
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
        calculateSlopeIntercept();
    }

    /**
     * Constructor.
     * @param x1 x coordinates of point start.
     * @param y1 y coordinates of point start.
     * @param x2 x coordinates of point end.
     * @param y2 y coordinates of point end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
        calculateSlopeIntercept();
    }

    /**
     * @return line length.
     */
    public double length() {
        return (start.distance(end));
    }

    /**
     * @return middle point of line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * @return start point of line.
     */
    public Point start() {
        return (this.start);
    }

    /**
     * @return end point of line.
     */
    public Point end() {
        return (this.end);
    }

    /**
     * @param other line to be compared with.
     * @return true if lines intersect.
     */
    public boolean isIntersecting(Line other) {
        if (equals(other)) {  //lines are equal
            return true;
        }
        if (slope == other.slope) {
            if (intercept == other.intercept) {  //lines have no overlap
                if (this.start.getX() > other.end.getX() || this.end.getX() < other.start.getX()) {
                    return false;
                }
            } else {
                return true;
            }
        }
        Point intersectionPoint = new Point(0, 0);
        intersectionPoint.setX((other.intercept - intercept) / (slope - other.slope));
        intersectionPoint.setY((slope * intersectionPoint.getX()) + intercept);
        //checks if the point of intersection isn't in both lines.
        return (intersectionPoint.getX() >= this.start.getX() && intersectionPoint.getX() <= this.end.getX()
                && intersectionPoint.getX() >= other.start.getX() && intersectionPoint.getX() <= other.end.getX());
    }

    /**
     * works only for lines with a single intersection point.
     * @param other the other line that gets checked for interaction points.
     * @return intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (equals(other)) {  //lines are equal
            return null;
        }
        if (slope == other.slope) {
            if (intercept != other.intercept) {
                return null;
            } else if (intercept == other.intercept) {
                //checks for single intersections on the edge of the lines.
                if (this.start.getX() == other.end.getX()) {  //one intersection at this.start
                    return this.start;
                } else if (this.end.getX() == other.start.getX()) {  //one intersection at this.end
                    return this.end;
                } else {  //many intersection points
                    return null;
                }
            }
        }
        Point intersectionPoint = new Point(0, 0);
        intersectionPoint.setX((other.intercept - intercept) / (slope - other.slope));
        intersectionPoint.setY((slope * intersectionPoint.getX()) + intercept);
        //checks if the point of intersection isn't in both lines.
        if (intersectionPoint.getX() >= this.start.getX() && intersectionPoint.getX() <= this.end.getX()
                && intersectionPoint.getX() >= other.start.getX() && intersectionPoint.getX() <= other.end.getX()) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * @param other line to be compared with.
     * @return true if lines are equal.
     */
    public boolean equals(Line other) {
        return (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()
        && this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY());
    }

    /**
     * calculates the slope and intercept for given line.
     */
    private void calculateSlopeIntercept() {
        this.slope = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        this.intercept = (this.start.getY() - (slope * this.start.getX()));
    }
}
