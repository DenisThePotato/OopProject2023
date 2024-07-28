//207270521 Denis Mogilevsky
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Denis Mogilevsky
 */
public class GameFlow {
//    private List<LevelInformation> levelInformation;
    private Counter score = new Counter();
    private AnimationRunner runner;

    /**
     * constructor.
     * @param runner animation runner.
     */
    public GameFlow(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * runs the game.
     * @param levels list of levels to be played.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score, runner);
            level.initialize();
            while (level.isContinue()) {
                level.run();
            }
            if (level.getBallAmount() == 0) {
                isWon = false;
                break;
            }
        }
        endGameScreen(isWon);
    }

    /**
     * @param isWon represents whether the player won or lost.
     */
    private void endGameScreen(boolean isWon) {
        //AnimationRunner animationEndScreen = new AnimationRunner("End Screen", 800, 600, 60);
        String message =
                isWon ? "You Win! Your score is " + score.getValue() : "Game Over. Your score is " + score.getValue();
        Animation screen = new EndScreen(runner.getKeyboardSensor(), message);
        Animation interactiveScreen =
                new KeyPressStoppableAnimation(runner.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, screen);
        runner.run(interactiveScreen);
        runner.close();
    }

}
