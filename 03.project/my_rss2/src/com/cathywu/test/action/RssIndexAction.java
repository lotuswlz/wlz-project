/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.JDOMException;

import com.cathywu.test.RssUtil;
import com.cathywu.test.entry.RssBean;
import com.cathywu.test.entry.RssCategoryBean;
import com.opensymphony.xwork2.ActionSupport;

public class RssIndexAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<RssBean> list;
    private List<RssCategoryBean> rootList;
    
    public List<RssCategoryBean> getRootList() {
		return rootList;
	}

	public void setRootList(List<RssCategoryBean> rootList) {
		this.rootList = rootList;
	}

	public List<RssBean> getList() {
        return list;
    }

    @Override
    public String execute() throws Exception {
        try {
            this.list = RssUtil.getRssBeanByCategory("news");
            Map<String, RssCategoryBean> map = RssUtil.getRssCategoryMap();
            this.rootList = new ArrayList<RssCategoryBean>();
            for (Iterator<Entry<String, RssCategoryBean>> it = map.entrySet().iterator(); it.hasNext();) {
            	Entry<String, RssCategoryBean> e = it.next();
            	rootList.add(e.getValue());
            }
            Collections.sort(rootList);
            return SUCCESS;
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ERROR;
    }
}
