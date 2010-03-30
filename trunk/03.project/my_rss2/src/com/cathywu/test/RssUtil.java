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
import com.cathywu.test.entry.RssCategoryBean;
import com.cathywu.test.entry.RssItemBean;
import com.sun.syndication.feed.synd.SyndEntry;

public class RssUtil {

//    private final static String XMLFILEPATH = "E:/project/JAVA/wlz-project/03.project/my_rss2/resources/rss_map.xml";
    private final static String XMLFILEPATH = "E:/MyProject/wlz-project/03.project/my_rss2/resources/rss_map.xml";
    
    private static Map<String, RssBean> rssMap;
    private static Map<String, RssCategoryBean> rssCategoryMap;
    static {
        rssMap = new HashMap<String, RssBean>();
        rssCategoryMap = new HashMap<String, RssCategoryBean>();
    }
    
    
    
    public static List<RssBean> getRssBeanByCategory(String category) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(XMLFILEPATH);
        Element root = doc.getRootElement();
        List options = root.getChildren("rss_category");
        
        if (options == null || options.size() == 0) {
            return new ArrayList<RssBean>();
        }
        
        if (rssCategoryMap.isEmpty()) {
        	updateRssMap();
        }
        
        return rssCategoryMap.get(category).getChildTypeList();
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
    
    /**
     * Get rss item list.
     * @param catId String
     * @return List&lt;RssItemBean&gt;
     * @throws JDOMException
     * @throws IOException
     */
    public static List<RssItemBean> getRssItemListByType(String catId) throws JDOMException, IOException {
		RssBean rss = getRssMap().get(catId);
		long current = System.currentTimeMillis();
		TestRss t = new TestRss(rss.getLink());
		List<SyndEntry> list = t.getRssList();
		List<RssItemBean> result = new ArrayList<RssItemBean>();
		RssItemBean r = null;
		for (SyndEntry e : list) {
			r = new RssItemBean();
			r.setAuthor(e.getAuthor());
			r.setDescription(e.getDescription().getValue());
			r.setHref(e.getLink());
			r.setTitle(e.getTitle());
			r.setPublishDate(e.getPublishedDate());
			r.setParentRssBean(rss);
			result.add(r);
		}
		rss.setUseTime(System.currentTimeMillis() - current);
		return result;
	}

    public static Map<String, RssBean> getRssMap() {
    	if (rssMap.isEmpty()) {
    		try {
				updateRssMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
        return rssMap;
    }
    
    public static Map<String, RssCategoryBean> getRssCategoryMap() {
    	if (rssCategoryMap.isEmpty()) {
    		try {
				updateRssMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
		return rssCategoryMap;
	}

	private synchronized static void updateRssMap() throws JDOMException, IOException {
    	SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(XMLFILEPATH);
        Element root = doc.getRootElement();
        List options = root.getChildren("rss_category");
        
        if (options == null || options.size() == 0) {
            return;
        }
        
        rssMap.clear();
        rssCategoryMap.clear();
        
        List<RssBean> list = null;
        RssCategoryBean cat = null;
        RssBean bean = null;
        for (Object obj : options) {
            Element e = (Element) obj;
            List temp = e.getChildren("entry");
            cat = new RssCategoryBean();
            cat.setCategory(e.getAttributeValue("name"));
            cat.setCatName(e.getAttributeValue("description"));
            cat.setId(e.getAttributeValue("id"));
            list = new ArrayList<RssBean>();
            for (Object o : temp) {
                bean = new RssBean(((Element) o).getChild("name").getValue(),
                        ((Element) o).getChild("link").getValue(),
                        ((Element) o).getChild("cat_id").getValue());
                bean.setDisplayDetail(Boolean.parseBoolean(((Element) o)
						.getChild("display_detail").getValue()));
                list.add(bean);
                rssMap.put(bean.getCatId(), bean);
            }
            cat.setChildTypeList(list);
            rssCategoryMap.put(cat.getCategory(), cat);
        }
	}
}
