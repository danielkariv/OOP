import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Daniel Kariv
 * 2D Block.
 */
public class Block implements  Collidable, Sprite {
    private Rectangle hitbox;
    private Color color;
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
    }
    /**
     * Constructor.
     * @param rectangle block's rectangle.
     * @param color color of block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.hitbox = rectangle;
        this.color = color;
    }
    /**
     * Constructor. default color is gray.
     * @param rectangle block's rectangle.
     */
    public Block(Rectangle rectangle) {
        this.hitbox = rectangle;
        this.color = Color.gray;
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
     * @return new velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
}
