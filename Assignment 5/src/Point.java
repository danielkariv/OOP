/**
 * @author Daniel Kariv
 * 2D point
 */
public class Point {
    private double x, y;
    /**
     * Constructor.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance from other point.
     * @param other the other point.
     * @return the distance of two points.
     */
    public double distance(Point other) {
        // square root of '((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))'
        double dist = (Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
        dist = Math.sqrt(dist);
        return dist;
    }

    /**
     * Checks if both points are the same.
     * @param other the other point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * Get the x coordinate of the point.
     * @return x coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Set a new x coordinate value for the point.
     * @param value new value for x coordinate of the point.
     */
    public void setX(double value) {
        this.x = value;
    }

    /**
     * Get the y coordinate of the point.
     * @return y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set a new y coordinate value for the point.
     * @param value new value for y coordinate of the point.
     */
    public void setY(double value) {
        this.y = value;
    }
}

