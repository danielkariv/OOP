import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Paddle object. controlled by player.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle hitbox;
    private double speed = 16;
    private int leftLimit = 0 + 10;
    private int rightLimit = 800 - 10;
    /**
     * Constructor.
     * @param keyboard gui's keyboard sensor.
     */
    public Paddle(KeyboardSensor keyboard) {
        hitbox = new Rectangle(new Point(400, 500), 80, 20);
        this.keyboard = keyboard;
    }

    /**
     * Constructor.
     * @param x top-left x-axis.
     * @param y top-left y-axis.
     * @param width width of the paddle.
     * @param height height of the paddle.
     * @param keyboard gui's keyboard sensor.
     */
    public Paddle(int x,  int y,  int width,  int height, KeyboardSensor keyboard) {
        hitbox = new Rectangle(new Point(x, y), width, height);
        this.keyboard = keyboard;
    }

    /**
     * move paddle left.
     */
    public void moveLeft() {
        double value = hitbox.getUpperLeft().getX() - speed;
        if (value < leftLimit) {
            value = leftLimit;
        }
        hitbox.getUpperLeft().setX(value);
    }

    /**
     * move paddle right.
     */
    public void moveRight() {
        double value = hitbox.getUpperLeft().getX() + speed;
        if (value > rightLimit - hitbox.getWidth()) {
            value = rightLimit - hitbox.getWidth();
        }
        hitbox.getUpperLeft().setX(value);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) || keyboard.isPressed("a")) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) || keyboard.isPressed("d")) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle((int) this.hitbox.getUpperLeft().getX(), (int) this.hitbox.getUpperLeft().getY(),
                (int) this.hitbox.getWidth(), (int) this.hitbox.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.hitbox;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        Velocity velocity = new Velocity(dx, dy);
        Rectangle.SIDES[] sides = this.hitbox.getIntersectSide(collisionPoint);
        for (Rectangle.SIDES side : sides) {
            if (side == Rectangle.SIDES.UP) {
                for (int j = 0; j < 5; j++) {
                    if (collisionPoint.getX() >= hitbox.getUpperLeft().getX() + j * hitbox.getWidth() / 5
                        && collisionPoint.getX() <= hitbox.getUpperLeft().getX() + (j + 1) * hitbox.getWidth() / 5) {
                        velocity = Velocity.fromAngleAndSpeed((60 - 30 * j), currentVelocity.getSpeed());
                        break;
                    }
                }
            }
            if (side == Rectangle.SIDES.DOWN) {
                velocity.setDy(-currentVelocity.getDy());
            } else if (side == Rectangle.SIDES.LEFT || side == Rectangle.SIDES.RIGHT) {
                velocity.setDx(-currentVelocity.getDx());
            }
        }
        return velocity;
    }

    // Add this paddle to the game.

    /**
     * add paddle to Game.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * set a new speed value.
     * @param value new speed.
     */
    public void setSpeed(double value) {
        this.speed = value;
    }
}