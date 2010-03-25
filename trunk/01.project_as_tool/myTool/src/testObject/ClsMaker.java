/*
 * All Rights Reserved. Copyright(C) 2008 OfferMe.com.au, Australia
 *
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2009-11-27      Cathy Wu        Create
 */

package testObject;

public abstract class ClsMaker<T> {
    
    private T data;

    public ClsMaker() {
        
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
