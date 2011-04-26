/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionsItem {

    private int promotionId;
    
    private String promotionText;
    
    private BigDecimal promotionPrice;
    
    private List<FoodItem> foodList;
    
    private Map<Integer, Integer> quantityMap;

    public PromotionsItem(int promotionId, String text) {
        this.promotionId = promotionId;
        this.promotionText = text;
    }

    public List<FoodItem> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodItem> foodList) {
        this.foodList = foodList;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionText() {
        return promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }
    
    private void updateFoodQtyMap() {
        quantityMap = new HashMap<Integer, Integer>();
        for (FoodItem item : foodList) {
            if (quantityMap.containsKey(item.getFoodId())) {
                quantityMap.put(item.getFoodId(), 1);
            } else {
                quantityMap.put(item.getFoodId(), quantityMap.get(item.getFoodId()) + 1);
            }
        }
    }
    
    public int getFoodQty(int foodId) {
        if (quantityMap == null) {
            updateFoodQtyMap();
        }
        
        return quantityMap.get(foodId);
    }
    
    public int getFoodCount() {
        if (quantityMap == null) {
            updateFoodQtyMap();
        }
        return this.quantityMap.keySet().size();
    }

    public Map<Integer, Integer> getQuantityMap() {
        if (quantityMap == null) {
            updateFoodQtyMap();
        }
        return quantityMap;
    }
}
