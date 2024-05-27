//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * relevant for anything collidable.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint collision point between collidable and ball.
     * @param currentVelocity current velocity of ball.
     * @param hitter the ball hitting the block.
     * @return velocity expected after hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
