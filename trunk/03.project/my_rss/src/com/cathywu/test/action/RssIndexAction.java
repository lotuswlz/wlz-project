/*
 * History
 *   Version     Update Date 　　        Updater　　　　       Details
 *   1.0.00  2010-3-26      Cathy Wu        Create
 */

package com.cathywu.test.action;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

import com.cathywu.test.RssUtil;
import com.cathywu.test.entry.RssBean;
import com.opensymphony.xwork2.ActionSupport;

public class RssIndexAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<RssBean> list;
    
    public List<RssBean> getList() {
        return list;
    }

    @Override
    public String execute() throws Exception {
        try {
            this.list = RssUtil.getRssBeanByCategory("news");
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
