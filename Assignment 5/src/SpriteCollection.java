import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Sprite Collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<>();;

    /**
     * add new sprite.
     * @param s new sprite.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    // call timePassed() on all sprites.

    /**
     * update all sprite. ( timePassed() )
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * Draw all sprites. ( drawOn(d) )
     * @param d drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * remove sprite object from list.
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            if (s == sprite) {
                spriteList.remove(s);
            }
        }
    }
}
