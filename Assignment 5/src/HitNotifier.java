/**
 * HitNotifier Interface.
 */
public interface HitNotifier {
    /**
     * adding new listener to hit events.
     * @param hl new listener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * remove listener from the list of listeners to hit events.
     * @param hl hit listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
