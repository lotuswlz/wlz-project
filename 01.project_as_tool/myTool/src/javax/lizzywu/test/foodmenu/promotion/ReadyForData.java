/*
 * History
 *   Version     Update Date ¡¡¡¡        Updater¡¡¡¡¡¡¡¡       Details
 *   1.0.00  2011-4-26      Cathy Wu        Create
 */

package javax.lizzywu.test.foodmenu.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.lizzywu.test.foodmenu.FoodItem;
import javax.lizzywu.test.foodmenu.FoodType;
import javax.lizzywu.test.foodmenu.PromotionsItem;

public final class ReadyForData {

    public static List<FoodItem> menuList = new ArrayList<FoodItem>();
    static {
        FoodItem item = new FoodItem(1, FoodType.MAIN_FOOD, "Ã×·¹", new BigDecimal(1), new BigDecimal(50), new BigDecimal(50));
        menuList.add(item);
        item = new FoodItem(2, FoodType.MAIN_FOOD, "ÂøÍ·", new BigDecimal(1), new BigDecimal(40), new BigDecimal(80));
        menuList.add(item);
        item = new FoodItem(3, FoodType.MAIN_FOOD, "ÌïÔ°´à¼¦±¤", new BigDecimal(8), new BigDecimal(50), new BigDecimal(100));
        menuList.add(item);
        item = new FoodItem(4, FoodType.MAIN_FOOD, "ÐÂ°Â¶ûÁ¼¿¾¼¦ÍÈ±¤", new BigDecimal(15), new BigDecimal(80), new BigDecimal(150));
        menuList.add(item);
        item = new FoodItem(5, FoodType.MAIN_FOOD, "÷¨Óã±¤", new BigDecimal(14), new BigDecimal(70), new BigDecimal(100));
        menuList.add(item);
        item = new FoodItem(6, FoodType.MAIN_FOOD, "ÆßÏº±¤", new BigDecimal(16), new BigDecimal(70), new BigDecimal(110));
        menuList.add(item);
        item = new FoodItem(7, FoodType.MAIN_FOOD, "ÏãÀ±¼¦ÍÈ±¤", new BigDecimal(13.5), new BigDecimal(70), new BigDecimal(120));
        menuList.add(item);
        item = new FoodItem(8, FoodType.MAIN_FOOD, "¼¦Èâ¾í", new BigDecimal(15.5), new BigDecimal(80), new BigDecimal(100));
        menuList.add(item);
        item = new FoodItem(9, FoodType.SIDE_DISH, "Ô­Î¶¼¦", new BigDecimal(10), new BigDecimal(50), new BigDecimal(100));
        menuList.add(item);
        item = new FoodItem(10, FoodType.SIDE_DISH, "Õ¨¼¦³á", new BigDecimal(8), new BigDecimal(35), new BigDecimal(80));
        menuList.add(item);
        item = new FoodItem(11, FoodType.DESSERT, "µ°Ì¢1", new BigDecimal(3.5), new BigDecimal(20), new BigDecimal(30));
        menuList.add(item);
        item = new FoodItem(12, FoodType.DESSERT, "µ°Ì¢2", new BigDecimal(5), new BigDecimal(20), new BigDecimal(30));
        menuList.add(item);
        item = new FoodItem(13, FoodType.SIDE_DISH, "¿¾³á", new BigDecimal(9), new BigDecimal(40), new BigDecimal(90));
        menuList.add(item);
        item = new FoodItem(14, FoodType.SIDE_DISH, "ÊíÌõ", new BigDecimal(6.5), new BigDecimal(50), new BigDecimal(50));
        menuList.add(item);
        item = new FoodItem(15, FoodType.VEGETABLE_DISH, "ÓñÃ×É³À­", new BigDecimal(6), new BigDecimal(30), new BigDecimal(30));
        menuList.add(item);
        item = new FoodItem(16, FoodType.DRINK, "¿§·È", new BigDecimal(6), new BigDecimal(100), new BigDecimal(60));
        menuList.add(item);
        item = new FoodItem(17, FoodType.FRUIT, "Æ»¹û", new BigDecimal(1), new BigDecimal(150), new BigDecimal(50));
        menuList.add(item);
        item = new FoodItem(18, FoodType.DESSERT, "ºì¶¹¸þ", new BigDecimal(9), new BigDecimal(80), new BigDecimal(50));
        menuList.add(item);
        item = new FoodItem(19, FoodType.DESSERT, "Á«×Ó¸þ", new BigDecimal(10), new BigDecimal(80), new BigDecimal(50));
        menuList.add(item);
        item = new FoodItem(20, FoodType.DRINK, "¿ÉÀÖ", new BigDecimal(15), new BigDecimal(1000), new BigDecimal(200));
        menuList.add(item);
        item = new FoodItem(21, FoodType.VEGETABLE_DISH, "Êß²ËÉ³À­", new BigDecimal(6), new BigDecimal(30), new BigDecimal(30));
        menuList.add(item);
        item = new FoodItem(22, FoodType.DRINK, "ÏãèÖ·äÃÛ²è", new BigDecimal(6.5), new BigDecimal(150), new BigDecimal(50));
        menuList.add(item);
    }
    
    
    
    public static List<PromotionsItem> promotionList = new ArrayList<PromotionsItem>();
    static {
        PromotionsItem p = new PromotionsItem(1, "2·ÝÔ­Î¶¼¦");
        List<FoodItem> list = new ArrayList<FoodItem>();
        list.add(menuList.get(8));
        list.add(menuList.get(8));
        p.setFoodList(list);
        p.setPromotionPrice(new BigDecimal(18));
        promotionList.add(p);
        
        p = new PromotionsItem(2, "2·ÝÕ¨¼¦³á");
        list.clear();
        list.add(menuList.get(9));
        list.add(menuList.get(9));
        p.setPromotionPrice(new BigDecimal(15));
        promotionList.add(p);
        
        p = new PromotionsItem(3, "4·ÝÔ­Î¶¼¦");
        list.clear();
        list.add(menuList.get(8));
        list.add(menuList.get(8));
        list.add(menuList.get(8));
        list.add(menuList.get(8));
        p.setPromotionPrice(new BigDecimal(35));
        promotionList.add(p);
        
        p = new PromotionsItem(4, "4·ÝÕ¨¼¦³á");
        list.clear();
        list.add(menuList.get(9));
        list.add(menuList.get(9));
        list.add(menuList.get(9));
        list.add(menuList.get(9));
        p.setPromotionPrice(new BigDecimal(26));
        promotionList.add(p);
        
        p = new PromotionsItem(5, "µ°Ì¢2(6Ö»×°)");
        list.clear();
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        p.setPromotionPrice(new BigDecimal(27.5));
        promotionList.add(p);
        
        p = new PromotionsItem(6, "µ°Ì¢1(6Ö»×°)");
        list.clear();
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        p.setPromotionPrice(new BigDecimal(20));
        promotionList.add(p);
        
        p = new PromotionsItem(7, "µ°Ì¢Ë«Æ´3+3");
        list.clear();
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(11));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        list.add(menuList.get(10));
        p.setPromotionPrice(new BigDecimal(24));
        promotionList.add(p);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
