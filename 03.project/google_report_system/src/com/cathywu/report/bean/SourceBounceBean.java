package com.cathywu.report.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SourceBounceBean {

	private String source;
	private long visitCount;
	private long bounceCount;
	
	public SourceBounceBean(String source, long visit, long bounce) {
		this.source = source;
		this.visitCount = visit;
		this.bounceCount = bounce;
	}
	
	public long getVisitCount() {
		return visitCount;
	}
	public long getBounceCount() {
		return bounceCount;
	}
	
	public BigDecimal getBounceRate() {
		BigDecimal v = new BigDecimal(this.visitCount);
		BigDecimal b = new BigDecimal(this.bounceCount);
		return b.divide(v, RoundingMode.HALF_UP).setScale(4, RoundingMode.HALF_UP);
	}

	public String getSource() {
		return source;
	}
}
