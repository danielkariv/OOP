import biuoop.DrawSurface;

/***
 * Animation Class.
 */
public interface Animation {
    /***
     * Process the next frame of the animation.
     * @param d given from GUI object.
     */
    void doOneFrame(DrawSurface d);

    /***
     * tells if the animation needs to stop.
     * @return true when the animation is stopping.
     */
    boolean shouldStop();
}