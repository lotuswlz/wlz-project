package com.cathywu.report.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class QueryParam {
	
	private String queryId;
	private String desc;
	
	private String dimensions;
	private String metrics;
	private String startDate;
	private String endDate;
	private String filters;
	private String sort;
	private int maxResults;
	
	private int startIndex = 1;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public String getMetrics() {
		return metrics;
	}
	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(12342);
		BigDecimal b = new BigDecimal(1000);
		System.out.println(a.divide(b, RoundingMode.UP));
		System.out.println(a.divide(b, RoundingMode.DOWN));
	}
}
