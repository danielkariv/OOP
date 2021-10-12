import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment interface.
 */
public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<>();

    // add the given collidable to the environment.

    /**
     * add collidable object.
     * @param c new collidable.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * get closest collision for a given line.
     * @param trajectory given line of movement.
     * @return collisionInfo - report position and which object collided with.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable collid:collidableList) {
            Point p = trajectory.closestIntersectionToStartOfLine(collid.getCollisionRectangle());
            if (p != null) {
                CollisionInfo collisionInfo = new CollisionInfo(p, collid);
                return collisionInfo;
            }
        }
        return null;
    }

    /**
     * remove collidable object from list.
     * @param c collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> listeners = new ArrayList<Collidable>(this.collidableList);
        for (Collidable collidable : listeners) {
            if (c == collidable) {
                collidableList.remove(c);
            }
        }
    }

}