/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lizzywu.test.foodmenu.FoodItem;
import javax.lizzywu.test.foodmenu.PromotionsItem;

public class FoodOrder {

    private List<PromotionsItem> promotionList;
    
    private List<FoodItem> foodList;
    
    private Map<Integer, Integer> orderMap;
    
    public Map<Integer, Integer> getOrderMap() {
        if (this.orderMap == null) {
            this.updateMap();
        }
        return orderMap;
    }

    public void setOrderMap(Map<Integer, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    public FoodOrder() {
        this.promotionList = new ArrayList<PromotionsItem>();
        this.foodList = new ArrayList<FoodItem>();
    }
    
    public void addPromotionItem(int promotionId) {
        this.promotionList.add(ReadyForData.promotionList.get(promotionId - 1));
    }
    
    public void addPromotionItems(int promotionId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.promotionList.add(ReadyForData.promotionList.get(promotionId - 1));
        }
    }
    
    public void addFoodItem(int foodId) {
        this.foodList.add(ReadyForData.menuList.get(foodId - 1));
    }
    
    public void addFoodItems(int foodId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.foodList.add(ReadyForData.menuList.get(foodId - 1));
        }
    }

    public List<PromotionsItem> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<PromotionsItem> promotionList) {
        this.promotionList = promotionList;
    }

    public List<FoodItem> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodItem> foodList) {
        this.foodList = foodList;
    }
    
    public BigDecimal calcFoodTotalPrice() {
        return this.calcTotalPrice(this.foodList).add(calcTotalPromotionPrice());
    }
    
    private BigDecimal calcTotalPrice(List<FoodItem> list) {
        if (list == null || list.size() == 0) {
            return BigDecimal.ZERO;
        }
        FoodItem food = null;
        BigDecimal price = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            food = list.get(i);
            price = price.add(food.getPrice());
        }
        return price;
    }
    
    private BigDecimal calcTotalPromotionPrice() {
        if (this.promotionList == null) {
            return BigDecimal.ZERO;
        }
        PromotionsItem item = null;
        BigDecimal price = new BigDecimal(0);
        for (int i = 0; i < this.promotionList.size(); i++) {
            item = this.promotionList.get(i);
            price = price.add(this.calcTotalPrice(item.getFoodList()));
        }
        return price;
    }
    
    private void updateMap() {
        this.orderMap = new HashMap<Integer, Integer>();
        List<FoodItem> list = new ArrayList<FoodItem>();
        list.addAll(this.foodList);
        for (PromotionsItem item : this.promotionList) {
            list.addAll(item.getFoodList());
        }
        for (FoodItem f : list) {
            if (this.orderMap.get(f.getFoodId()) != null) {
                this.orderMap.put(f.getFoodId(), this.orderMap.get(f.getFoodId()) + 1);
            } else {
                this.orderMap.put(f.getFoodId(), 1);
            }
        }
    }
}
