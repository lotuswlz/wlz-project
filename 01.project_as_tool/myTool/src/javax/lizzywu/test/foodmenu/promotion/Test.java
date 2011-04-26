/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.lizzywu.test.foodmenu.FoodItem;
import javax.lizzywu.test.foodmenu.PromotionsItem;

public class Test {
    
    private List<FoodItem> orderList;
    
    public Test() {
        this.orderList = new ArrayList<FoodItem>();
    }
    
    public void order(int foodId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.orderList.add(ReadyForData.menuList.get(foodId - 1));
        }
    }
    
    public BigDecimal calcTotalPrice() {
        FoodItem food = null;
        BigDecimal price = new BigDecimal(0);
        for (int i = 0; i < this.orderList.size(); i++) {
            food = this.orderList.get(i);
            price = price.add(food.getPrice());
        }
        return price;
    }
    
    public FoodOrder mergeOrder() {
        FoodOrder order = new FoodOrder();
        
        Map<Integer, Integer> foodQuantityMap = new HashMap<Integer, Integer>();
        
        for (FoodItem item : orderList) {
            if (foodQuantityMap.containsKey(item.getFoodId())) {
                foodQuantityMap.put(item.getFoodId(), 1);
            } else {
                foodQuantityMap.put(item.getFoodId(), foodQuantityMap.get(item.getFoodId()) + 1);
            }
        }
        
//        for (Iterator<Entry<Integer, Integer>> it = foodQuantityMap.entrySet().iterator(); it.hasNext();) {
//            Entry<Integer, Integer> e = it.next();
//            int qty = e.getValue();
//            for (PromotionsItem p : ReadyForData.promotionList) {
//                if (p.getFoodCount() > 1) {
//                    continue;
//                }
//                int pQty = p.getFoodQty(e.getKey());
//                if (pQty == 0) {
//                    continue;
//                }
//                if (qty % pQty == 0) {
//                    order.addPromotionItems(p.getPromotionId(), qty / pQty);
//                } else if (qty > pQty) {
//                    order.addPromotionItems(p.getPromotionId(), qty / pQty);
//                    int pCnt = qty % pQty;
//                    order.addFoodItems(e.getKey(), pCnt);
//                } else if (qty < pQty) {
//                    order.addFoodItems(e.getKey(), qty);
//                }
//            }
//        }
        
        for (PromotionsItem p : ReadyForData.promotionList) {
            Map<Integer, Integer> map = p.getQuantityMap();
            int orderCount = 0;
            for (Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
                Entry<Integer, Integer> e = it.next();
                int pId = e.getKey();
                int pQty = e.getValue();
                if (foodQuantityMap.get(pId) > 0) {
                    orderCount++;
                    
                }
            }
        }
        
        return order;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.order(1, 2);
        t.order(2, 3);
        t.order(8, 4);
        t.order(9, 6);
        t.order(11, 2);
        t.order(11, 1);
        t.order(10, 3);
        t.order(13, 1);
        t.order(12, 1);
        t.order(22, 1);
        FoodOrder o = new FoodOrder();
        o.setFoodList(t.orderList);
        for (Integer b : o.getOrderMap().keySet()) {
            System.out.println(b + ", " + ReadyForData.menuList.get(b - 1).getItemName() + ", " + o.getOrderMap().get(b));
        }
        System.out.println("Total Price: " + t.calcTotalPrice());
    }

    public List<FoodItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<FoodItem> orderList) {
        this.orderList = orderList;
    }
}
