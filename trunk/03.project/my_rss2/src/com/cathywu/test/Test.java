package com.cathywu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.cathywu.test.entry.RssBean;
import com.cathywu.test.entry.RssCategoryBean;

public class Test {
    public static List<String[]> getCsvContent(String path) throws IOException {
        File file = new File(path);
        FileReader fr = null;
        BufferedReader br = null;
        
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        
        String temp = null;
        String[] arr = null;
        List<String[]> list = new ArrayList<String[]>();
        do{
            temp = br.readLine();
            if (temp == null) {
                continue;
            }
            arr = temp.split(",");
            if (!arr[0].trim().equals("")) {
                list.add(arr);
            }
        }while(temp != null);
        br.close();
        fr.close();
        return list;
    }
    
    public static String findContent() {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(XMLFILEPATH);
        Element root = doc.getRootElement();
        List options = root.getChildren("rss_category");
        
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
        return null;
    }
    
    public static void main(String[] args) {
        try {
            String path = "D:/development_documents/datafeed/test/all_item.csv";
            List<String[]> list = getCsvContent(path);
            for (String[] arr : list) {
                System.out.println(arr[0] + ":" + arr[1]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
