//207270521 Denis Mogilevsky
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Denis Mogilevsky.
 */
public class WideEasy implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(new Velocity(5, 5));
        ballVelocities.add(new Velocity(4, 5));
        ballVelocities.add(new Velocity(3, 5));
        ballVelocities.add(new Velocity(2, 5));
        ballVelocities.add(new Velocity(1, 5));
        ballVelocities.add(new Velocity(-1, 5));
        ballVelocities.add(new Velocity(-2, 5));
        ballVelocities.add(new Velocity(-3, 5));
        ballVelocities.add(new Velocity(-4, 5));
        ballVelocities.add(new Velocity(-5, 5));
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(255, 255, 120));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int blockWidth = 57;
        int blockHeight = 20;
        Random random = new Random();
        for (int blockNumber = 0; blockNumber < 10; blockNumber++) {
            Color color = new Color(random.nextInt(226), random.nextInt(226), random.nextInt(226));
            blockList.add(
                    new Block(
                            new Rectangle(
                                    new Point(
                                            15 + blockWidth * blockNumber, 50), blockWidth, blockHeight), color));
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
