//207270521 Denis Mogilevsky
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Denis Mogilevsky.
 * creates an animation of a bouncing ball.
 */
public class BouncingBallAnimation {
    /**
     *
     * @param start starting point.
     * @param dx velocity, x axis.
     * @param dy velocity, y axis.
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing ball animation", 1600, 900);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * @param args input by user. 4 ints.
     */
    public static void main(String[] args) {
        int[] intInput = new int[4];
        for (int index = 0; index < 4; index++) {
            try {
                intInput[index] = Integer.parseInt(args[index]);
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
        if (intInput[0] < 31 || intInput[0] > 1569 || intInput[1] < 31 || intInput[1] > 869) {
            System.out.println("Starting point out of bounds. (30, 30) < (x, y) < (1570, 870)");
            System.exit(0);
        }
        Point startingPoint = new Point(intInput[0], intInput[1]);
        drawAnimation(startingPoint, intInput[2], intInput[3]);
    }
}
