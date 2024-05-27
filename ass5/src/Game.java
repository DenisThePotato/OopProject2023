//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * @author Denis Mogilevsky.
 * runs the game arkanoid.
 */
public class Game {
    private GUI gui;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockAmount = new Counter();
    private Counter ballAmount = new Counter();
    private Counter score = new Counter();

    /**
     * adds a collidable to the game environment.
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a sprite to the sprite collection.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        String guiTitle = "Arkanoid";
        int guiWidth = 800;
        int guiHeight = 600;
        gui = new GUI(guiTitle, guiWidth, guiHeight);
        //adding ball.
        Ball firstBall = (
                new Ball(new Point((double) guiWidth / 2, (double) guiHeight / 2), 10, Color.black, this.environment));
        firstBall.addToGame(this);
        ballAmount.increase(1);
        //adding paddle.
        Paddle temp = (new Paddle(
                new Rectangle(new Point(370, guiHeight - 25), 100, 10),
                new Color(150, 75, 0), //brown
                //Color.BLUE,
                gui.getKeyboardSensor()));
        temp.addToGame(this);
        //adding corner blocks.
        initializeBorderBlocks(guiWidth, guiHeight);
        initializeBallKillerBlock(guiWidth, guiHeight);
        //adding misc.
        initializeExtraBlocks();
        initializeExtraBalls();
        sprites.addSprite(new ScoreIndicator(this.score));
    }

    /**
     * initializes the 4 blocks on the border of the screen.
     * will require a change if gui size changes. there's no gui.getHeight or width.
     * @param guiHeight gui height.
     * @param guiWidth gui width.
     */
    private void initializeBorderBlocks(int guiWidth, int guiHeight) {
        int blockWidth = 15;
        Block upperBlock = (new Block(new Rectangle(new Point(
                blockWidth, 0), guiWidth - (2 * blockWidth), blockWidth), Color.black));
        Block leftBlock = (new Block(new Rectangle(new Point(
                0, 0), blockWidth, guiHeight), Color.black));
        Block rightBlock = (new Block(new Rectangle(new Point(
                guiWidth - blockWidth, 0), blockWidth, guiHeight), Color.black));
        upperBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
    }

    private void initializeBallKillerBlock(int guiWidth, int guiHeight) {
        BallRemover ballRemover = new BallRemover(this, ballAmount);
        int blockWidth = 15;
        Block ballKiller = (new Block(new Rectangle(new Point(
                0, guiHeight), guiWidth, blockWidth), Color.black));
        ballKiller.addToGame(this);
        ballKiller.addHitListener(ballRemover);
    }

    private void initializeExtraBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, blockAmount);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
        int blockWidth = 50;
        int blockHeight = 15;
        int rowNumber = 0;
        Random random = new Random();
        for (int blockNumber = 12; blockNumber >= 7; blockNumber--) {
            Color color = new Color(random.nextInt(226), random.nextInt(226), random.nextInt(226));
            for (int block = 0; block < blockNumber; block++) {
                Block temp = (new Block(new Rectangle(new Point(
                        800 - blockHeight - blockWidth * (block + 1), 200 + blockHeight * rowNumber),
                        blockWidth, blockHeight), color));
                temp.addToGame(this);
                temp.addHitListener(blockRemover);
                temp.addHitListener(scoreTracker);
                blockAmount.increase(1);
            }
            rowNumber++;
        }
    }

    /**
     *
     */
    private void initializeExtraBalls() {
        Ball firstExtra = (new Ball(new Point(400, 560), 10, Color.black, this.environment));
        firstExtra.addToGame(this);
        ballAmount.increase(1);
        Ball secondExtra = (new Ball(new Point(100, 560), 10, Color.black, this.environment));
        secondExtra.setVelocity(-5, -5);
        secondExtra.addToGame(this);
        ballAmount.increase(1);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //beige background color
            d.setColor(new java.awt.Color(245, 245, 220));
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (blockAmount.getValue() == 0) {
                score.increase(100);
            }
            if (blockAmount.getValue() == 0 || ballAmount.getValue() == 0) {
                System.out.println("Final score: " + score.getValue());
                gui.close();
            }
        }
    }

    /**
     * removes a collidable.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * removes a sprite.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }
}
