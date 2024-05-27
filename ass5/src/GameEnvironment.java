//207270521 Denis Mogilevsky
import java.util.ArrayList;

/**
 * @author Denis Mogilevsky.
 * holds the game environment- collidables.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<>();
    /**
     * add the given collidable to the environment.
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * @return collidables array list.
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory trajectory.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int indexOfClosestCollision = -1;
        int shortestDistanceCollision = Integer.MAX_VALUE;
        Point closestPointOfCollision = new Point(0, 0);
        for (Collidable collidable: collidables) {
            Point tempPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if ((tempPoint != null) && tempPoint.distance(trajectory.getLineBeginning()) < shortestDistanceCollision) {
                shortestDistanceCollision = (int) tempPoint.distance(trajectory.getLineBeginning());
                indexOfClosestCollision = collidables.indexOf(collidable);
                closestPointOfCollision = tempPoint;
            }
        }
        if (indexOfClosestCollision == -1) {
            return null;
        }
        return (new CollisionInfo(closestPointOfCollision, collidables.get(indexOfClosestCollision)));
    }
}
