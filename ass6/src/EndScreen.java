//207270521 Denis Mogilevsky
import biuoop.KeyboardSensor;

/**
 * @author Denis Mogilevsky
 */
public class EndScreen  extends PauseScreen {
    /**
     * constructor.
     * @param k keyboard sensor.
     */
    public EndScreen(KeyboardSensor k) {
        super(k);
    }

    /**
     * constructor.
     * @param k keyboard sensor.
     * @param message message to be displayed on the ending screen.
     */
    public EndScreen(KeyboardSensor k, String message) {
        super(k);
        this.message = message;
    }

}
