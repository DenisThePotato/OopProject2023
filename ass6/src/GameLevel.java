//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.Random;

/**
 * @author Denis Mogilevsky.
 * runs the game arkanoid.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockAmount = new Counter();
    private Counter ballAmount = new Counter();
    private Counter score = new Counter();
    private int guiWidth = 800;
    private int guiHeight = 600;
    private AnimationRunner runner;
    private PauseScreen pauseScreen;
    private boolean running = true;
    private LevelInformation levelInformation;

    /**
     * constructor.
     * @param level current level.
     * @param score game score.
     * @param runner animation runner.
     */
    public GameLevel(LevelInformation level, Counter score, AnimationRunner runner) {
        this.levelInformation = level;
        this.score = score;
        this.runner = runner;
        pauseScreen = new PauseScreen(runner.getKeyboardSensor());
    }

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
        initializeBackground();
        initializeBorderBlocks(guiWidth, guiHeight);
        initializeBallKillerBlock(guiWidth, guiHeight);
        initializeBalls();
        initializeBlocks();
        initializePaddle();
        initializeScoreIndicator();
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

    private void initializeBalls() {
        for (Velocity ballVelocity : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point(400, 560), 10, Color.BLACK, this.environment);
            ball.setVelocity(ballVelocity);
            ball.addToGame(this);
            ballAmount.increase(1);
        }
    }

    private void initializeBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, blockAmount);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            blockAmount.increase(1);
        }
    }

    /**
     * creates the paddle.
     */
    public void initializePaddle() {
        Paddle paddle = (new Paddle(
                new Rectangle(new Point(370, guiHeight - 25), levelInformation.paddleWidth(), 10),
                new Color(150, 75, 0),
                runner.getKeyboardSensor()));
        paddle.setPaddleSpeed(levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * creates the score indicator.
     */
    public void initializeScoreIndicator() {
        sprites.addSprite(new ScoreIndicator(this.score));
    }

    /**
     * creates the background.
     */
    public void initializeBackground() {
        sprites.addSprite(levelInformation.getBackground());
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
     * Run the game, start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
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

    @Override
    public void doOneFrame(DrawSurface d) {
        if (blockAmount.getValue() == 0) {
            score.increase(100);
        }
        if (blockAmount.getValue() == 0 || ballAmount.getValue() == 0) {
            System.out.println("Final score: " + score.getValue());
            this.running = false;
        }
        if (pauseScreen.getKeyboard().isPressed("p")) {
            Animation interactivePause =
                    new KeyPressStoppableAnimation(pauseScreen.getKeyboard(), KeyboardSensor.SPACE_KEY, pauseScreen);
            this.runner.run(interactivePause);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * getter.
     * @return boolean running value.
     */
    public boolean isContinue() {
        return this.running;
    }

    /**
     * @return ball amount in current level.
     */
    public int getBallAmount() {
        return this.ballAmount.getValue();
    }
}
