//207270521 Denis Mogilevsky
import biuoop.DrawSurface;

/**
 * @author Denis Mogilevsky.
 * An interface for
 */
public interface Animation {
    /**
     * prints one frame.
     * @param d drawing surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     *
     * @return
     */
    boolean shouldStop();
}
