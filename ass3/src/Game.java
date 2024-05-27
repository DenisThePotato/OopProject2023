//207270521 Denis Mogilevsky.
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
     * initializes the 4 blocks on the border of the screen.
     * will require a change if gui size changes. there's no gui.getHeight or width.
     * @param guiHeight gui height.
     * @param guiWidth gui width.
     */
    private void initializeBorderBlocks(int guiWidth, int guiHeight) {
        int blockWidth = 15;
        Block b1 = (new Block(new Rectangle(new Point(
                blockWidth, 0), guiWidth - blockWidth, blockWidth), Color.black));
        Block b2 = (new Block(new Rectangle(new Point(
                0, 0), blockWidth, guiHeight - blockWidth), Color.black));
        Block b3 = (new Block(new Rectangle(new Point(
                0, guiHeight - blockWidth), guiWidth - blockWidth, blockWidth), Color.black));
        Block b4 = (new Block(new Rectangle(new Point(
                guiWidth - blockWidth, blockWidth), blockWidth, guiHeight - blockWidth), Color.black));
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
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
        //adding paddle.
        Paddle temp = (new Paddle(
                new Rectangle(new Point(370, guiHeight - 25), 100, 10),
                new Color(150, 75, 0), //brown
                //Color.BLUE,
                gui.getKeyboardSensor()));
        temp.addToGame(this);
        //adding corner blocks.
        initializeBorderBlocks(guiWidth, guiHeight);
        //adding misc.
        initializeExtraBlocks();
        initializeExtraBalls();
    }

    private void initializeExtraBlocks() {
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
            }
            rowNumber++;
        }
    }
    private void initializeExtraBalls() {
        Ball temp = (new Ball(new Point(400, 560), 10, Color.black, this.environment));
        temp.addToGame(this);
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
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
