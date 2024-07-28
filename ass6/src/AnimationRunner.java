//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Denis Mogilevsky.
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * @param guiTitle gui title.
     * @param guiWidth gui width.
     * @param guiHeight gui height.
     * @param fps frames per second.
     */
    public AnimationRunner(String guiTitle, int guiWidth, int guiHeight, int fps) {
        gui = new GUI(guiTitle, guiWidth, guiHeight);
        framesPerSecond = fps;
    }

    /**
     * runs the animation loop.
     * @param animation animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * closes the gui.
     */
    public void close() {
        gui.close();
    }

    /**
     * @return keyboard sensor.
     */
    public KeyboardSensor getKeyboardSensor() {
        return gui.getKeyboardSensor();
    }
}
