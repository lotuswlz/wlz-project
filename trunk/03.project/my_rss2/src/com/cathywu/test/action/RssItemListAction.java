package com.cathywu.test.action;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

import com.cathywu.test.RssUtil;
import com.cathywu.test.entry.RssItemBean;
import com.opensymphony.xwork2.ActionSupport;

public class RssItemListAction extends ActionSupport {
	
	private static final long serialVersionUID = -8823654060486230468L;
	// input params
	private String catId;

	// output params
	private List<RssItemBean> list;
	private String typeName;
	
	public List<RssItemBean> getList() {
		return list;
	}

	@Override
	public String execute() throws Exception {
		try {
			this.list = RssUtil.getRssItemListByType(catId);
			this.typeName = RssUtil.getRssMap().get(catId).getTypeName();
			return SUCCESS;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getTypeName() {
		return typeName;
	}
}
