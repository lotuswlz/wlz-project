/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-16      Cathy Wu        Create
 */

package testCollection.myCollection;

public class CathyLinkedTable<T> {
    
    private T element;
    private T preElement;

    public CathyLinkedTable(T e) {
        this.element = e;
    }
    
    public CathyLinkedTable(T e, T preE) {
        this.element = e;
        this.preElement = preE;
    }
    
    public void addPreElement(T e) {
        this.preElement = e;
    }
    
    public T element() {
        return this.element;
    }
    
    public T preElement() {
        return this.preElement;
    }
}
