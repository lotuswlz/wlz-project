/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-16      Cathy Wu        Create
 */

package testCollection.myCollection;

public class CathyArrayList<T> implements CathyList<T> {
    
    private CathyLinkedTable<T>[] array;
    private int realCount;
    
    @SuppressWarnings("unchecked")
    public CathyArrayList() {
        array = new CathyLinkedTable[10];
        realCount = 0;
    }

    public void add(T obj) {
        CathyLinkedTable<T> preObj = null;
        if (realCount >= this.array.length && realCount > 0) {
            CathyLinkedTable<T>[] temp = new CathyLinkedTable[this.array.length + 10];
            for (int i = 0; i < this.array.length; i++) {
                temp[i] = this.array[i];
            }
            this.array = temp;
        } else if (realCount >= 1) {
            preObj = this.array[realCount - 1];
        }
        array[realCount] = new CathyLinkedTable<T>(obj, preObj == null ? null : preObj.element());
        realCount++;
    }

    public T get(int index) {
        if (index >= realCount) {
            throw new RuntimeException("Expected " + (index + 1) + ", in actual " + realCount);
        }
        return array[index].element();
    }

    public void remove(T obj) {
        // TODO Auto-generated method stub

    }

    public void set(int index, T obj) {
        // TODO Auto-generated method stub

    }

    public int size() {
        return realCount;
    }
    
    public static void main(String[] args) {
        CathyArrayList<String> list = new CathyArrayList<String>();
        for (int i = 0; i < 21; i++) {
            list.add(String.valueOf(i));
        }
        String temp = null;
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            System.out.println(temp);
        }
    }

}
