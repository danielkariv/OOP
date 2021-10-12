import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Kariv
 * 2D Block.
 */
public class Block implements  Collidable, Sprite, HitNotifier {
    private Rectangle hitbox;
    private Color color;
    private List<HitListener> hitListeners;
    /**
     * Constructor.
     * @param x value of left-top point x-axis.
     * @param y value of left-top point y-axis.
     * @param width width of block.
     * @param height height of block.
     * @param color color of block.
     */
    public Block(int x, int y, int width, int height, Color color) {
        Point upperLeft = new Point(x, y);
        this.hitbox = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Constructor.
     * @param rectangle block's rectangle.
     * @param color color of block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.hitbox = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Constructor. default color is gray.
     * @param rectangle block's rectangle.
     */
    public Block(Rectangle rectangle) {
        this.hitbox = rectangle;
        this.color = Color.gray;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Gets collision rectangle.
     * @return collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.hitbox;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * Calls when object collide, return new velocity based on current velocity and hit position.
     * @param collisionPoint hit point.
     * @param currentVelocity given velocity.
     * @param hitter ball that hits.
     * @return new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Rectangle.SIDES[] sides = this.hitbox.getIntersectSide(collisionPoint);
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        for (int i = 0; i < sides.length; i++) {
            Rectangle.SIDES side = sides[i];
            if (side == Rectangle.SIDES.INVALID) {
                break;
            } else if (side == Rectangle.SIDES.UP || side == Rectangle.SIDES.DOWN) {
                dy = -dy;
            } else if (side == Rectangle.SIDES.LEFT || side == Rectangle.SIDES.RIGHT) {
                dx = -dx;
            }
        }
        Velocity velocity = new Velocity(dx, dy);
        return velocity;
    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.hitbox.getUpperLeft().getX(), (int) this.hitbox.getUpperLeft().getY(),
                                (int) this.hitbox.getWidth(), (int) this.hitbox.getHeight());
    }

    @Override
    public void timePassed() {

    }
    /**
     * Drawing function, shows the Block.
     * @param g current game to add Block to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove current block from game.
     * @param g game to remove from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all listeners when hitter ( Ball) hits.
     * @param hitter ball that hits
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
