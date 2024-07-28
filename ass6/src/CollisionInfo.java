//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * holds the information about a collision.
 */
public class CollisionInfo {
    public Point collisionPoint = new Point(0, 0);
    public Collidable collisionObject;

    /**
     * @param collisionPoint collision point between object and ball.
     * @param collisionObject object that's collided with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
}
