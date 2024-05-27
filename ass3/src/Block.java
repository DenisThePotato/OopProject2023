//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Denis Mogilevsky.
 * class representing collidables.
 */
public class Block implements Collidable, Sprite {
    private Rectangle block = new Rectangle((new Point(0, 0)), 0, 0);
    private java.awt.Color color = new Color(0, 0, 0);

    /**
     * getter.
     * @return block rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * constructor. sets only the rectangle.
     * @param rectangle block rectangle.
     */
    public Block(Rectangle rectangle) {
        this.block = rectangle;
    }

    /**
     * constructor. sets the color and rectangle.
     * @param rectangle block rectangle.
     * @param color block color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.block = rectangle;
        this.color = color;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint collision point between object and block.
     * @param currentVelocity current velocity of object.
     * @return velocity change?
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double yThreshold = Math.min(Math.abs(collisionPoint.getY() - this.block.getUpperLeft().getY()),
                Math.abs(collisionPoint.getY() - this.block.getUpperLeft().getY() - this.block.getHeight()));
        double xThreshold = Math.min(Math.abs(collisionPoint.getX() - this.block.getUpperLeft().getX()),
                Math.abs(collisionPoint.getX() - this.block.getUpperLeft().getX() - this.block.getWidth()));
        if (yThreshold < xThreshold) {
            currentVelocity.dy = -currentVelocity.dy;
        } else {
            currentVelocity.dx = -currentVelocity.dx;
        }
        return currentVelocity;
    }

    /**
     * draws a rectangle with black edges.
     * @param d surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     *
     */
    public void timePassed() {

    }

    /**
     * adds the block to sprites and collidables.
     * @param g game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * setter.
     * @param color block color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
