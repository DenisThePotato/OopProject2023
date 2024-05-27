//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Denis Mogilevsky.
 * ball class. has a middle location represented as Point, color and radius.
 */
public class Ball implements Sprite {

    private Point location = new Point(0, 0);
    private java.awt.Color color = new Color(0, 0, 0);
    private int radius;
    private Velocity velocity = new Velocity(5, 5);
    private GameEnvironment gameEnvironment;
    private GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }
    /**
     * constructor.
     * @param center ball center values, stored as Point.
     * @param r ball radius.
     * @param color ball color.
     * @param gameEnvironment game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.location = center;
        this.color = color;
        this.radius = r;
        this.gameEnvironment = gameEnvironment;
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
     * @param xCenter center x coordinates.
     * @param yCenter center y coordinates.
     * @param r radius.
     * @param color color.
     */
    public Ball(double xCenter, double yCenter, int r, java.awt.Color color) {
        this.location = new Point(xCenter, yCenter);
        this.color = color;
        this.radius = r;
    }

    /**
     * sets the location of the middle of ball.
     * @param location location of middle point.
     */
    public void setLocation(Point location) {
        this.location = location;
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
     * draw the ball on the given DrawSurface.
     * @param surface surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) (this.location.getX()), (int) (this.location.getY()), this.radius);
    }

    /**
     * what the ball does for every update of gui.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * setter.
     * @param v velocity to be set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx velocity of x-axis.
     * @param dy velocity of y-axis.
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
     * moves the ball.
     */
    public void moveOneStep() {
        CollisionInfo collision = this.getGameEnvironment().
                getClosestCollision(new Line(this.location, this.getVelocity().applyToPoint(this.location)));
        if (collision == null || (this.location == collision.collisionPoint)) {
            this.location = this.getVelocity().applyToPoint(this.location);
        } else {
            this.location.setX(collision.collisionPoint.getX());
            this.location.setY(collision.collisionPoint.getY());
            //moving the ball back a radius length in the opposite direction to velocity.
            double magnitude = Math.sqrt(
                    (this.getVelocity().dx * this.getVelocity().dx) + (this.getVelocity().dy * this.getVelocity().dy));
            double normalizedDx = this.velocity.dx / magnitude;
            double normalizedDy = this.velocity.dy / magnitude;
            this.setLocation(
                    new Point(this.getX() - normalizedDx * this.radius, this.getY() - normalizedDy * this.radius));
            this.velocity = collision.collisionObject.hit(collision.collisionPoint, this.getVelocity());
        }
    }

    /**
     * adds the ball to sprites.
     * @param g game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
