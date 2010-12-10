package com.cathywu.report.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class QuerySourceBounceBean {

	private long visitForAllSite;
	private long bounceForAllSite;
	private List<SourceBounceBean> list;
	
	private long totalVisit;
	private long totalBounce;
	
	public QuerySourceBounceBean(long visit, long bounce, List<SourceBounceBean> list) {
		this.bounceForAllSite = bounce;
		this.visitForAllSite = visit;
		this.list = list;
		
		// calculate total data
		this.totalVisit = 0;
		this.totalBounce = 0;
		for (SourceBounceBean bean : list) {
			this.totalBounce += bean.getBounceCount();
			this.totalVisit += bean.getVisitCount();
		}
	}
	
	public long getVisitForAllSite() {
		return visitForAllSite;
	}

	public long getBounceForAllSite() {
		return bounceForAllSite;
	}

	public List<SourceBounceBean> getList() {
		return list;
	}
	
	public BigDecimal getTotalBounceRate() {
		return new BigDecimal(this.totalBounce).divide(
				new BigDecimal(this.totalVisit), RoundingMode.HALF_UP)
				.setScale(4, RoundingMode.HALF_UP);
	}
	
	public long getTotalVisit() {
		return totalVisit;
	}
	
	public long getTotalBounce() {
		return totalBounce;
	}
}
