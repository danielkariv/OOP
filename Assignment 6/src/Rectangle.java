import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle object.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;

    /**
     * Sides (used for known which side of the rectangle the points is).
     */
    public enum SIDES { INVALID, UP, DOWN, LEFT, RIGHT }

    /**
     * Constructor.
     * @param upperLeft top-left point.
     * @param width width.
     * @param height height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * given line segments for the rectangle.
     * @return line array (size 4).
     */
    private Line[] getRectangleLines() {
        Line[] rectLines = new Line[4];
        rectLines[0] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY());
        rectLines[1] = new Line(upperLeft.getX(), upperLeft.getY() + this.height,
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        rectLines[2] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + this.height);
        rectLines[3] = new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        return rectLines;
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * give all intersection points for given line with the rectangle.
     * @param line given line segment.
     * @return list of points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Line[] rectLines = getRectangleLines();

        for (Line rectLine:rectLines) {
            Point p = line.intersectionWith(rectLine);
            if (p != null) {
                boolean isUnique = true;
                for (Point existPoint : points) {
                    if (existPoint.equals(p)) {
                        isUnique = false;
                    }
                }
                if (isUnique) {
                    points.add(p);
                }
            }
        }
        return points;
    }

    /**
     * Get on which side given point is on.
     * @param collisionPoint given point.
     * @return array of 4 sides.
     */
    public SIDES[] getIntersectSide(Point collisionPoint) {
        SIDES[] sides = new SIDES[4];
        int index = 0;
        if ((int) collisionPoint.getY() == this.upperLeft.getY()) {
            if ((int) collisionPoint.getX() >= this.upperLeft.getX()
                    && (int) collisionPoint.getX() <= this.upperLeft.getX() + this.getWidth()) {
                // collisionPoint is on top line.
                sides[index] = SIDES.UP;
                index++;
            }
        }
        if ((int) collisionPoint.getY() == this.upperLeft.getY() + this.getHeight()) {
            if ((int) collisionPoint.getX() >= this.upperLeft.getX()
                    && (int) collisionPoint.getX() <= this.upperLeft.getX() + this.getWidth()) {
                // collisionPoint is on bottom line.
                sides[index] = SIDES.DOWN;
                index++;
            }
        }
        if ((int) collisionPoint.getX() == this.upperLeft.getX()) {
            if ((int) collisionPoint.getY() >= this.upperLeft.getY()
                    && (int) collisionPoint.getY() <= this.upperLeft.getY() + this.getHeight()) {
                // collisionPoint is on left line.
                sides[index] = SIDES.LEFT;
                index++;
            }
        }
        if ((int) collisionPoint.getX() == this.upperLeft.getX() + this.getWidth()) {
            if ((int) collisionPoint.getY() >= this.upperLeft.getY()
                    && (int) collisionPoint.getY() <= this.upperLeft.getY() + this.getHeight()) {
                // collisionPoint is on right line.
                sides[index] = SIDES.RIGHT;
                index++;
            }
        }
        return sides;
    }
    // Return the width and height of the rectangle

    /**
     * get width.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get height.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.

    /**
     * get upper left point.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set new upper left point.
     * @param point new point for upper left.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }
}