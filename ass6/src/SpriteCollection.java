//207270621 Denis Mogilevsky
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Mogilevsky.
 * collection of drawable objects.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * @return array list of sprites.
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

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
        List<Sprite> copiedList = new ArrayList<>(sprites);
        for (Sprite sprite: copiedList) {
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
