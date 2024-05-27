//207270621 Denis Mogilevsky.

/**
 * a class representing a rectangle.
 */
public class Rectangle {
    private Point upperLeft = new Point(0, 0);
    private double width;
    private double height;

    /**
     * constructor.
     * @param upperLeft upper left point.
     * @param width width of rectangle.
     * @param height height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * returns a list of intersection points with a line.
     * @param line line to be checking for intersection points with.
     * @return list of all intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lineList = new Line[4];
        //top line.
        lineList[0] = new Line(getUpperLeft().getX(), getUpperLeft().getY(),
                getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        //left line.
        lineList[1] = new Line(getUpperLeft().getX(), getUpperLeft().getY(),
                getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        //right line.
        lineList[2] = new Line(getUpperLeft().getX() + getWidth(), getUpperLeft().getY(),
                getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        //bot line.
        lineList[3] = new Line(getUpperLeft().getX(), getUpperLeft().getY() + getHeight(),
                getUpperLeft().getX() + getWidth(), getUpperLeft().getY()  + getHeight());
        //3 possible scenarios. collision with corner (2 rectangle lines), collision with one line, or two lines.
        java.util.Set<Point> intersectionPointSet = new java.util.HashSet<>(0);
        for (int index = 0; index < 4; index++) {
            Point intersectionPoint = line.intersectionWith(lineList[index]);
            if (intersectionPoint != null) {
                intersectionPointSet.add(intersectionPoint);
            }
        }
        java.util.List<Point> intersectionPointList = new java.util.ArrayList<>(0);
        intersectionPointList.addAll(intersectionPointSet);
        return intersectionPointList;
    }

    /**
     * @return width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}