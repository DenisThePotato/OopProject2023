//207270521 Denis Mogilevsky
import biuoop.DrawSurface;

/**
 * @author Denis Mogilevsky.
 * a class representing a score indicator sprite.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     * @param score score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new java.awt.Color(245, 245, 220));
        d.drawText(390, 10, String.valueOf(score.getValue()), 10);
    }

    @Override
    public void timePassed() {

    }
}
