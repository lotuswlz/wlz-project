/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.report.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.cathywu.report.bean.QueryParam;

public class DataValueCalculateUtils {

    private final static String XMLFILEPATH = "E:/MyProject/wlz-project/03.project/google_report_system/resources/data_value.xml";
    
    private static Map<String, QueryParam> valueDefMap;
    static {
        valueDefMap = new HashMap<String, QueryParam>();
    }
    
    public static void main(String[] args) {
        
    }

    public static Map<String, QueryParam> getValueDefMap() {
    	if (valueDefMap.isEmpty()) {
    		try {
				updateRssMap();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
    	}
        return valueDefMap;
    }

	private synchronized static void updateRssMap() throws JDOMException, IOException {
    	SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(XMLFILEPATH);
        Element root = doc.getRootElement();
        List options = root.getChildren("query");
        
        if (options == null || options.size() == 0) {
            return;
        }
        
        valueDefMap.clear();
        
        QueryParam qp = null;
        for (Object obj : options) {
            Element e = (Element) obj;
            qp = new QueryParam();
            qp.setDesc(e.getAttributeValue("description"));
            qp.setQueryId(e.getAttributeValue("id"));
            qp.setDimensions(e.getChild("dimensions").getValue());
            qp.setMetrics(e.getChildText("metrics"));
            qp.setFilters(e.getChildText("filters"));
            qp.setMaxResults(Integer.parseInt(e.getChildText("maxresult")));
            qp.setSort(e.getChildText("sort"));
            valueDefMap.put(qp.getQueryId(), qp);
        }
	}
}
