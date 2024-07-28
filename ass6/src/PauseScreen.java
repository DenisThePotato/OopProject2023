//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Denis Mogilevsky.
 * pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    protected String message;

    /**
     * constructor.
     * @param k keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        message = "paused -- press space to continue";
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, message, 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * @return keyboard sensor.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }
}

