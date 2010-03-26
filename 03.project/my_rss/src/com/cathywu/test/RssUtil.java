/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.cathywu.test.entry.RssBean;

public class RssUtil {

    private final static String XMLFILEPATH = "E:/MyProject/wlz-project/03.project/my_rss/src/resources/rss_map.xml";
    
    private static Map<String, RssBean> rssMap;
    static {
        rssMap = new HashMap<String, RssBean>();
    }
    
    public static List<RssBean> getRssBeanByCategory(String category) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(XMLFILEPATH);
        Element root = doc.getRootElement();
        List options = root.getChildren("rss_category");
        
        if (options == null || options.size() == 0) {
            return new ArrayList<RssBean>();
        }
        
        rssMap.clear();
        
        List<RssBean> list = new ArrayList<RssBean>();
        RssBean bean = null;
        String desc = null;
        for (Object obj : options) {
            Element e = (Element) obj;
            if (!e.getAttributeValue("name").equals(category)) {
                continue;
            }
            desc = e.getAttributeValue("description");
            List temp = e.getChildren("entry");
            for (Object o : temp) {
                bean = new RssBean(((Element) o).getChild("name").getValue(),
                        ((Element) o).getChild("link").getValue(), desc,
                        ((Element) o).getChild("cat_id").getValue());
                list.add(bean);
                rssMap.put(bean.getCatId(), bean);
            }
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        try {
            List<RssBean> list = getRssBeanByCategory("news");
            for (RssBean bean : list) {
                System.out.println(bean);
            }
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Map<String, RssBean> getRssMap() {
        return rssMap;
    }
}
