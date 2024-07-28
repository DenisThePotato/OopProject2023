//207270521 Denis Mogilevsky

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Denis Mogilevsky
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    String key;
    private boolean stop;

    private boolean isAlreadyPressed = true;

    private Animation animation;

    /**
     * @param sensor keyboard sensor.
     * @param key string.
     * @param animation animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        keyboard = sensor;
        this.key = key;
        stop = false;
        this.animation = animation;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
