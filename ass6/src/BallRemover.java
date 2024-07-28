//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky
 * a listener class responsible for removing balls.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel gameLevel.
     * @param remainingBalls number of blocks removed.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the gameLevel.
     * Remember to remove this listener from the block that is being removed from the gameLevel.
     * @param beingHit the block being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
