//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

/**
 * @author Denis Mogilevsky.
 * creates an animation of 6 balls with randomly generated fields.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * draws everything.
     * @param ballArray array of balls to be drawn.
     */
    static public void drawAnimation(ArrayList<Ball> ballArray) {
        GUI gui = new GUI("Multiples bouncing ball animation", 1600, 900);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball:ballArray) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
    //another way is to probably call the one from BouncingBallAnimation
    /**
     * everything is randomized except for the balls size.
     * @param ballSize size of ball to be created.
     * @param frame frame.
     * @return newly created ball. includes all of balls fields (location, radius, color, velocity).
     */
    public static Ball generateBall(int ballSize, SingleFrame frame) {
        Random rand = new Random();
        double xCoordinate = ThreadLocalRandom.current().nextDouble(frame.getStart().getX() + ballSize,
                frame.getEnd().getX() - ballSize);
        double yCoordinate = ThreadLocalRandom.current().nextDouble(frame.getStart().getY() + ballSize,
                frame.getEnd().getY() - ballSize);
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        java.awt.Color color = new Color(red, green, blue);
        double speed;
        if (ballSize <= 50) {
            speed = 27 - (ballSize / 2);
        } else {
            speed = 2;
        }
        Velocity velocityAngleSpeed =
                Velocity.fromAngleAndSpeed(ThreadLocalRandom.current().nextDouble(-180, 180), speed);
        Ball ball = new Ball(new Point(xCoordinate, yCoordinate), ballSize, color, frame);
        ball.setVelocity(velocityAngleSpeed);
        return ball;
    }

    /**
     * @param ballSizes ball radius.
     * @param frame frame.
     * @param startIndex starting index.
     * @param endIndex ending index.
     * @return ball array. each index has all of balls fields (location, radius, color, velocity).
     */
    public static ArrayList<Ball> generateBallArray(int[] ballSizes, SingleFrame frame, int startIndex, int endIndex) {
        ArrayList<Ball> ballArray = new ArrayList<Ball>();
        for (int index = startIndex; index <= endIndex; index++) {
            ballArray.add(generateBall(ballSizes[index], frame));
        }
        return ballArray;
    }

    /**
     * creates an int array from the user input as well as validating it.
     * @param args user input from main.
     * @return int array representing ball radius.
     */
    public static int[] inputValidation(String[] args) {
        int[] intInput = new int[args.length];
        for (int index = 0; index < args.length; index++) {
            try {
                intInput[index] = Integer.parseInt(args[index]);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                System.exit(0);
            }
            if (intInput[index] <= 0 || intInput[index] >= 200) {
                System.out.print("Ball size invalid. 0 < size < 200");
                System.exit(0);
            }
        }
        return intInput;
    }

    /**
     * main.
     * @param args input by user. 6 ints representing ball radius.
     */
    public static void main(String[] args) {
        SingleFrame frame = new SingleFrame(new Point(1, 1), new Point(1600, 900), Color.white);
        int[] intInput = inputValidation(args);
        ArrayList<Ball> ballArray = generateBallArray(intInput, frame, 0, intInput.length - 1);
        drawAnimation(ballArray);
    }
}
