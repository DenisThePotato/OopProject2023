//207270621 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * a listener class responsible for removing block.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel gameLevel.
     * @param remainingBlocks number of blocks removed.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the gameLevel.
     * Remember to remove this listener from the block that is being removed from the gameLevel.
     * @param beingHit the block being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}
