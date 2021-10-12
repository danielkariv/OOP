/**
 * Collidable interface. used for detection and effect collidable objects.
 */
public interface Collidable {
    /**
     * get collision rectangle.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Calculate what the new velocity should be for given collision point and velocity.
     * @param collisionPoint hit point.
     * @param currentVelocity current velocity.
     * @param hitter ball that hits.
     * @return new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}