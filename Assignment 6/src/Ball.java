import java.awt.Color;
import biuoop.DrawSurface;
/**
 * @author Daniel Kariv
 * 2D Ball.
 */
public class Ball implements Sprite {
    private GameEnvironment gameEnvironment;
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Constructor.
     * @param center center point for the ball.
     * @param r radius for the ball.
     * @param color Color for the ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    /**
     * Constructor.
     * @param center center point for the ball.
     * @param r radius for the ball.
     * @param color Color for the ball.
     * @param ge GameEnvironment where the ball exists.
     */
    public Ball(Point center, int r, Color color, GameEnvironment ge) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = ge;
    }
    /**
     * Get the ball's gameEnvironment where the ball exists.
     * @return ball's gameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * Set a new gameEnvironment for the ball.
     * @param ge new gameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }
    /**
     * Get the x coordinate of the center of ball.
     * @return x coordinate of the center of ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y coordinate of the center of ball.
     * @return y coordinate of the center of ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the radius of the ball.
     * @return radius of the ball.
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * Get the color of the ball.
     * @return color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set a new velocity for the ball.
     * @param v new velocity value.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set a new velocity for the ball.
     * @param dx x coordinate of the new velocity value.
     * @param dy y coordinate of the new velocity value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball in direction of its velocity.
     */
    public void moveOneStep() {
        CollisionInfo ci = this.gameEnvironment.getClosestCollision(
                new Line(this.center, this.velocity.applyToPoint(this.center)));
        if (ci != null) {
            Velocity v = ci.collisionObject().hit(this, ci.collisionPoint(), this.velocity);
            setVelocity(v);
            this.center = ci.collisionPoint();
            this.center.setX(this.center.getX() + v.getNormalize().getX());
            this.center.setY(this.center.getY() + v.getNormalize().getY());
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Moves the ball in direction of its velocity.
     * also process collisions with the frame, by reflect the velocity.
     * @param x x coordinate of the top-left frame.
     * @param y y coordinate of the top-left frame.
     * @param width width of the frame.
     * @param height height of the frame.
     */
    public void moveOneStep(double x, double y, double width, double height) {
        CollisionInfo ci = this.gameEnvironment.getClosestCollision(
                new Line(this.center, this.velocity.applyToPoint(this.center)));
        if (ci != null) {
            Velocity v = ci.collisionObject().hit(this, ci.collisionPoint(), this.velocity);
            setVelocity(v);
            this.center = ci.collisionPoint();
        }
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.center.getX() <= (x) + this.radius && getVelocity().getDx() <= 0) {
            setVelocity(-getVelocity().getDx(), getVelocity().getDy());
        } else if (this.center.getX() >= (x + width) - this.radius && getVelocity().getDx() >= 0) {
            setVelocity(-getVelocity().getDx(), getVelocity().getDy());
        }
        if (this.center.getY() <= (y) + this.radius && getVelocity().getDy() <= 0) {
            setVelocity(getVelocity().getDx(), -getVelocity().getDy());
        } else if (this.center.getY() >= (y + height) - this.radius && getVelocity().getDy() >= 0) {
            setVelocity(getVelocity().getDx(), -getVelocity().getDy());
        }

    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface given DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
    /**
     * Update method. Moves the ball one step forward.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * Add ball to game.
     * @param g given Game to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove ball from game.
     * @param g given Game to remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}