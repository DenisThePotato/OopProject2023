//207270621 Denis Mogilevsky.
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author Denis Mogilevsky.
 * collection of drawable objects.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();
    /**
     * adds a sprite to the array list.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     *  call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite: sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }
}
