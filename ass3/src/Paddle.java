//207270521 Denis Mogilevsky
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * a class representing the user controlled paddle.
 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle = new Rectangle((new Point(0, 0)), 0, 0);
    private Color color = new Color(0, 0, 0);

    /**
     * constructor. only sets paddle size.
     * @param rectangle paddle.
     * @param keyboard keyboard sensor.
     */
    public Paddle(Rectangle rectangle, biuoop.KeyboardSensor keyboard) {
        this.paddle = rectangle;
        this.keyboard = keyboard;
    }

    /**
     * constructor. sets paddle size and color.
     * @param rectangle rectangle.
     * @param color color.
     * @param keyboard keyboard sensor.
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard) {
        this.paddle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * setter.
     * @param color paddle color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * getter.
     * @return paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * returns the velocity of object according to collision. a hit on the top of the paddle will result in different
     * velocity changes depending on segment hit. sides will flip the horizontal velocity. if no collision occurs,
     * the velocity will remain the same.
     * @param collisionPoint collision point with paddle.
     * @param currentVelocity current velocity of object colliding with paddle.
     * @return relevant velocity of object.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double speed = currentVelocity.calculateSpeed();
        //no collision with paddle.
        if (collisionPoint == null) {
            return currentVelocity;
        }
        //collision with top of paddle.
        if (Math.abs(collisionPoint.getY() - this.paddle.getUpperLeft().getY()) < 0.0001) {
            int segment = 0;
            //find out what segment the collision happens at.
            for (; segment < 5; segment++) {
                if (collisionPoint.getX()
                        >= this.paddle.getUpperLeft().getX() + (segment * (this.paddle.getWidth() / 5))
                        && collisionPoint.getX()
                        <= this.paddle.getUpperLeft().getX() + ((segment + 1) * (this.paddle.getWidth() / 5))) {
                    break;
                }
            }
            //change velocity according to the segment of paddle hit.
            switch (segment) {
                case 0:
                    //currentVelocity = Velocity.fromAngleAndSpeed(-60, speed);
                    currentVelocity.dy = -speed * Math.sin(Math.toRadians(30));
                    currentVelocity.dx = -speed * Math.cos(Math.toRadians(30));
                    break;
                case 1:
                    //currentVelocity = Velocity.fromAngleAndSpeed(-30, speed);
                    currentVelocity.dy = -speed * Math.sin(Math.toRadians(60));
                    currentVelocity.dx = -speed * Math.cos(Math.toRadians(60));
                    break;
                case 2:
                    //currentVelocity = Velocity.fromAngleAndSpeed(0, speed);
                    currentVelocity.dy = -currentVelocity.dy;
                case 3:
                    //currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
                    currentVelocity.dy = -speed * Math.sin(Math.toRadians(60));
                    currentVelocity.dx =  speed * Math.cos(Math.toRadians(60));
                    break;
                case 4:
                    //currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
                    currentVelocity.dy = -speed * Math.sin(Math.toRadians(30));
                    currentVelocity.dx =  speed * Math.cos(Math.toRadians(30));
                    break;
                default:
                    break;
            }
            //collision with sides of paddle.
        } else if ((Math.abs(collisionPoint.getX() - this.paddle.getUpperLeft().getX()) < 0.0001)
        || (Math.abs(collisionPoint.getX() - (this.paddle.getUpperLeft().getX() + this.paddle.getWidth()))) < 0.0001) {
            currentVelocity.dx = -currentVelocity.dx;
        }
        return currentVelocity;
    }

    /**
     * draws the paddle.
     * @param d surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * what the paddle does every frame.
     */
    public void timePassed() {
        int guiWidth = 800;
        int stepSize = 10;
        int cornerBlocksWidth = 15;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.paddle.getUpperLeft().getX() - stepSize >= cornerBlocksWidth) {
                this.paddle.getUpperLeft().setX(this.paddle.getUpperLeft().getX() - stepSize);
            } else {
                this.paddle.getUpperLeft().setX(cornerBlocksWidth);
            }
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + stepSize <= guiWidth - cornerBlocksWidth) {
                this.paddle.getUpperLeft().setX(this.paddle.getUpperLeft().getX() + stepSize);
            } else {
                this.paddle.getUpperLeft().setX((guiWidth - cornerBlocksWidth) - this.paddle.getWidth());
            }
        }
    }

    /**
     * Add this paddle to the game.
     * @param g Game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
