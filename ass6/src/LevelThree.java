//207270521 Denis Mogilevsky
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Denis Mogilevsky.
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(new Velocity(5, 5));
        ballVelocities.add(new Velocity(-5, 5));
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(200, 50, 50));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int blockWidth = 50;
        int blockHeight = 15;
        int rowNumber = 0;
        Random random = new Random();
        for (int blockNumber = 12; blockNumber >= 7; blockNumber--) {
            Color color = new Color(random.nextInt(226), random.nextInt(226), random.nextInt(226));
            for (int block = 0; block < blockNumber; block++) {
                blockList.add(new Block(new Rectangle(new Point(
                        800 - blockHeight - blockWidth * (block + 1), 200 + blockHeight * rowNumber),
                        blockWidth, blockHeight), color));
            }
            rowNumber++;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
