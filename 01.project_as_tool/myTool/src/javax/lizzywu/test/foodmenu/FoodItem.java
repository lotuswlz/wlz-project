/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu;

import java.math.BigDecimal;

public class FoodItem {
    
    private int FoodId;

    private FoodType foodType;
    
    private String itemName;
    
    private BigDecimal price;
    
    private BigDecimal weight;
    
    private BigDecimal heat;
    
    public FoodItem(int id, FoodType type, String name, BigDecimal price,
            BigDecimal weight, BigDecimal heat) {
        this.FoodId = id;
        this.foodType = type;
        this.itemName = name;
        this.price = price;
        this.weight = weight;
        this.heat = heat;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeat() {
        return heat;
    }

    public void setHeat(BigDecimal heat) {
        this.heat = heat;
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }
}
