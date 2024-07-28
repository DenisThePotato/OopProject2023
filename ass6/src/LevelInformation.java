//207270521 Denis Mogilevsky
import java.util.List;

public interface LevelInformation {
    /**
     * @return the amount of balls in the level.
     */
    int numberOfBalls();

    /**
     * the initial velocity of each ball.
     * @return list of ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return the background sprite.
     */
    Sprite getBackground();

    /**
     * @return the level's blocks.
     */
    List<Block> blocks();

    /**
     * @return number of blocks needed to win the level.
     */
    int numberOfBlocksToRemove();
}
