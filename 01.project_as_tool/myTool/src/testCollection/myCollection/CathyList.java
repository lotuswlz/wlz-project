/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-16      Cathy Wu        Create
 */

package testCollection.myCollection;

public interface CathyList<T> {

    T get(int index);
    int size();
    void add(T obj);
    void remove(T obj);
    void set(int index, T obj);
}
