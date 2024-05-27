//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;

/**
 * creates two fields with animations of balls inside each.
 * @author Denis Mogilevsky.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * draws the animation.
     * @param ballArray ball array.
     */
     private static void drawAnimation(ArrayList<Ball> ballArray) {
        GUI gui = new GUI("Multiples bouncing ball animation", 1600, 900);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            SingleFrame current = null;
            for (Ball ball:ballArray) {
                if (current != ball.getFrame()) {
                    ball.getFrame().draw(d);
                    current = ball.getFrame();
                }
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
        /**
         * main.
         * @param args input by user. input ints representing ball radius.
         */
        public static void main(String[] args) {
            SingleFrame bigFrame = new SingleFrame(new Point(50, 50), new Point(500, 500), Color.gray);
            SingleFrame smallFrame = new SingleFrame(new Point(450, 450), new Point(600, 600), Color.yellow);
            int[] intInput = MultipleBouncingBallsAnimation.inputValidation(args);
            ArrayList<Ball> ballArray = new ArrayList<>();
            ballArray.addAll(MultipleBouncingBallsAnimation.generateBallArray(
                    intInput, bigFrame, 0, args.length / 2 - 1));
            ballArray.addAll(MultipleBouncingBallsAnimation.generateBallArray(
                    intInput, smallFrame, args.length / 2, args.length - 1));
            drawAnimation(ballArray);
        }
    }
