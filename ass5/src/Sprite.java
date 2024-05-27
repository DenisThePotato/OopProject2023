//207270521 Denis Mogilevsky
import biuoop.DrawSurface;

/**
 * @author Denis Mogilevsky.
 * every drawable object.
 */
public interface Sprite {
    /**
     * draw the sprite on the screen.
     * @param d surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
