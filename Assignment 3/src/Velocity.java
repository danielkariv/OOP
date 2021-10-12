/**
 * @author Daniel Kariv
 * 2D Velocity.
 */
public class Velocity {
    // Velocity specifies the change in position on the `x` and the `y` axes.s
    private double dx, dy;

    /**
     * Constructor.
     * @param dx x coordinate of velocity.
     * @param dy y coordinate of velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get the x coordinate of velocity.
     * @return x coordinate of velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Set a new x coordinate value of velocity.
     * @param value the new x coordinate value of velocity.
     */
    public void setDx(double value)  {
        this.dx = value;
    }

    /**
     * Get the y coordinate of velocity.
     * @return y coordinate of velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set a new y coordinate value of velocity.
     * @param value the new y coordinate value of velocity.
     */
    public void setDy(double value) {
        this.dy = value;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the given point.
     * @return new point with position after applying velocity on given point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Calculate new velocity from given angle and speed.
     * @param angle the angle of velocity.
     * @param speed the speed of velocity.
     * @return new Velocity for the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle + 90)) * speed;
        double dy = -Math.sin(Math.toRadians(angle + 90)) * speed;

        return new Velocity(dx, dy);
    }

    /**
     * Get speed of velocity.
     * @return speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Get normalized velocity as a Point, speed equals 1.
     * @return normalize vector.
     */
    public Point getNormalize() {
        double speed = getSpeed();
        Point p = new Point(this.dx / speed, this.dy / speed);
        return p;
    }
}