import java.awt.Color;
import biuoop.DrawSurface;
/**
 * @author Daniel Kariv
 * 2D Ball.
 */
public class Ball {
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
        this.center = this.getVelocity().applyToPoint(this.center);
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
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
}