import java.util.List;

/**
 * @author Daniel Kariv
 * Line segment.
 */
public class Line {
    private Point start, end;

    /**
     * Constructor.
     * @param start the line starting point.
     * @param end the line ending point.
     */
    public Line(Point start, Point end) {
        //if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        //} else {
        //    this.start = end;
        //    this.end = start;
        //}
    }

    /**
     * Constructor.
     * @param x1 the x coordinate of the start point.
     * @param y1 the y coordinate of the start point.
     * @param x2 the x coordinate of the end point.
     * @param y2 the y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1, p2;
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        //if (x1 < x2) {
            this.start = p1;
            this.end = p2;
        //} else {
        //    this.start = p2;
        //    this.end = p1;
        //}
    }

    /**
     * calculate the slope of the line (the 'm' in 'y=mx+b').
     * @return slope of the line.
     */
    public double getSlope() {
        double deltaY, deltaX;
        deltaY = this.start.getY() - this.end.getY();
        deltaX = this.start.getX() - this.end.getX();
        return deltaY / deltaX;
    }

    /**
     * calculate the intercept of the line (the 'b' in 'y=mx+b').
     * @return intercept of the line.
     */
    public double getIntercept() {
        double slope = this.getSlope();
        double intercept = this.start.getY() - slope * this.start.getX();
        return intercept;
    }

    /**
     * calculate the length of the line segment.
     * @return length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the middle point of the line segment.
     * @return the middle point of the line.
     */
    public Point middle() {
        double x, y;
        x = this.start.getX() + this.end.getX();
        y = this.start.getY() + this.end.getY();
        return new Point(x / 2, y / 2);
    }

    /**
     * Get the the start point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Get the the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if two line segment are intersecting.
     * @param other the other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // m1*x+b1 = m2*x+b2 -> (m1-m2)*x = b2-b1 -> x = (b2-b1)/(m1-m2)
        double m1 = this.getSlope();
        double b1 = this.getIntercept();
        double m2 = other.getSlope();
        double b2 = other.getIntercept();

        // error when slope is zero
        if (m1 == m2) {
            // means that the lines are parallels
            if (b1 == b2) {
                // means both line share the same inf line.
                boolean isPointsOnLines = this.isPointOnLine(other.start()) || other.isPointOnLine(this.start);
                return isPointsOnLines;
            } else {
                // lines aren't intersecting!
                return false;
            }
        } else {
            double suspectX = (b2 - b1) / (m1 - m2);
            double suspectY = m1 * suspectX + b1;
            Point suspectPoint = new Point(suspectX, suspectY);
            boolean isPointOnLines = this.isPointOnLine(suspectPoint) && other.isPointOnLine(suspectPoint);
            return isPointOnLines;
        }
    }

    /**
     * Calculate the intersection point between two line segments, if exist.
     * @param other the other line.
     * @return Returns the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // using linear algebra to calculate and find out if this line and other line are intersection.
        // Line 1 (a1x + b1y = c1)
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());

        // Line 2 (a2x + b2y = c2)
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // The lines are parallel.
            if (isPointOnLine(other.start)) {
                return other.start;
            }
            if (isPointOnLine(other.end)) {
                return other.end;
            } else if (other.isPointOnLine(this.start)) {
                return this.start;
            } else if (other.isPointOnLine(this.end)) {
                return this.end;
            } else {
                return null;
            }
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point suspect = new Point(x, y);
            if (isPointOnLine(suspect) && other.isPointOnLine(suspect)) {
                return new Point(x, y);
            } else {
                return null;
            }
        }
    }
    /**
     * Check if a given point is on the line segment.
     * @param other the point to check.
     * @return true if the point on line, false otherwise.
     */
    public boolean isPointOnLine(Point other) {
        double distTotal, distStart, distEnd;
        distTotal = this.length();
        distStart = this.start.distance(other);
        distEnd = this.end.distance(other);
        return (Math.floor(distStart + distEnd) == Math.floor(distTotal));
    }

    /**
     * Check if the two lines are the same.
     * @param other the other line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()));
    }

    /**
     * get the closest intersection to the start of line for given rectangle.
     * @param rect given rectangle.
     * @return closest point of intersection. ( null if not exist)
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);

        if (points.size() == 0) {
            return null;
        } else {
            Point closePoint = points.get(0);
            double distance = this.start.distance(closePoint);
            for (Point p: points) {
                double value = this.start.distance(p);
                if (value <= distance) {
                    closePoint = p;
                    distance = value;
                }
            }
            return closePoint;
        }
    }
}