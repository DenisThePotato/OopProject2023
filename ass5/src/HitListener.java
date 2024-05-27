//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * an interface representing hit listeners.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
