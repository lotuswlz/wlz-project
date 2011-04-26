/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu;

public enum FoodType {

    MAIN_FOOD(1), VEGETABLE_DISH(2), FRUIT(3), SIDE_DISH(4), DESSERT(5), MEAT_DISH(6), DRINK(7);
    
    private FoodType(int val) {
        this.type = val;
    }
    
    private int type;
    
    public int getValue() {
        return type;
    }
}
