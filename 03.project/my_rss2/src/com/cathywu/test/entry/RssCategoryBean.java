package com.cathywu.test.entry;

import java.util.List;

public class RssCategoryBean {

	private String category;
	private String catName;
	
	private List<RssBean> childTypeList;
	
	public List<RssBean> getChildTypeList() {
		return childTypeList;
	}
	public void setChildTypeList(List<RssBean> childTypeList) {
		this.childTypeList = childTypeList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
}
