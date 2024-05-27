//207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky
 * a listener class responsible for removing balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game game.
     * @param remainingBalls number of blocks removed.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     * @param beingHit the block being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
