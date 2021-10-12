import biuoop.DrawSurface;

/**
 * Sprite interface. used for rendering and updating objects.
 */
public interface Sprite {
    /**
     * Draw function. "draw the sprite to the screen".
     * @param d given drawSurface.
     */
    void drawOn(DrawSurface d);
    /**
     * Update function. "notify the sprite that time has passed".
     */
    void timePassed();
}
