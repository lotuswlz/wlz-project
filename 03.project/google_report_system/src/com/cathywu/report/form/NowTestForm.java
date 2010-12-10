package com.cathywu.report.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import com.cathywu.report.util.TimeRangeUtils;

public class NowTestForm {

	private String totalVisits;
	private String totalVisitors;
	private String totalNewVisits;
	
	// top 10 sources
	private String bounceCount;
	private String visits;
	
	// top 10 return visit
	private String newVisits;
	
	// groupbuy time on page
	private String totalGBTimeOnPage;
	private String totalGBViewCount;
	private String totalGBUniqVisits;
	
	// groupserve time on page
	private String totalGBCTimeOnPage;
	private String totalGBCViewCount;
	private String totalGBCUniqVisits;
	
	private Map<String, Integer> gbVisitmap;
	private Map<String, Integer> gbcVisitmap;
	
	//new visit, time on landing page
	private String newVisitViewCount;
	private String newVisitTimeOnPage;
	
	public String getNewVisitViewCount() {
		return newVisitViewCount;
	}

	public void setNewVisitViewCount(String newVisitViewCount) {
		this.newVisitViewCount = newVisitViewCount;
	}

	public String getNewVisitTimeOnPage() {
		long seconds = new BigDecimal(this.newVisitTimeOnPage).longValue();		
		return TimeRangeUtils.timeLengthDesc(seconds);
	}

	public void setNewVisitTimeOnPage(String newVisitTimeOnPage) {
		this.newVisitTimeOnPage = newVisitTimeOnPage;
	}

	public Map<String, Integer> getGbcVisitmap() {
		return gbcVisitmap;
	}

	public void setGbcVisitmap(Map<String, Integer> gbcVisitmap) {
		this.gbcVisitmap = gbcVisitmap;
	}

	public String getBounceCount() {
		return bounceCount;
	}

	public void setBounceCount(String bounceCount) {
		this.bounceCount = bounceCount;
	}

	public String getVisits() {
		return visits;
	}

	public void setVisits(String visits) {
		this.visits = visits;
	}

	public String getTotalReturnVisitsRate() {
		BigDecimal tv = new BigDecimal(totalVisits);
		BigDecimal tn = new BigDecimal(totalNewVisits);
		tn = (tv.subtract(tn)).multiply(new BigDecimal(100)).divide(tv, 2, RoundingMode.HALF_UP);
		tn = tn.setScale(2, RoundingMode.HALF_UP);
		return tn.toString() + "%";
	}
	
	public String getTopTenBounceRate() {
		BigDecimal tv = new BigDecimal(visits);
		BigDecimal tn = new BigDecimal(bounceCount);
		tn = tn.multiply(new BigDecimal(100)).divide(tv, 2, RoundingMode.HALF_UP);
		tn = tn.setScale(2, RoundingMode.HALF_UP);
		return tn.toString() + "%";
	}
	
	public String getTopTenReturnVisitRate() {
		BigDecimal tv = new BigDecimal(visits);
		BigDecimal tn = new BigDecimal(newVisits);
		tn = tv.subtract(tn);
		tn = tn.multiply(new BigDecimal(100)).divide(tv, 2, RoundingMode.HALF_UP);
		tn = tn.setScale(2, RoundingMode.HALF_UP);
		return tn.toString() + "%";
	}
	
	public static void main(String[] args) {
		NowTestForm f = new NowTestForm();
		f.setTotalVisits("10000");
		f.setTotalNewVisits("522");
		System.out.println(f.getTotalReturnVisitsRate());
	}

	public String getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(String totalVisits) {
		this.totalVisits = totalVisits;
	}

	public String getTotalVisitors() {
		return totalVisitors;
	}

	public void setTotalVisitors(String totalVisitors) {
		this.totalVisitors = totalVisitors;
	}

	public String getTotalNewVisits() {
		return totalNewVisits;
	}

	public void setTotalNewVisits(String totalNewVisits) {
		this.totalNewVisits = totalNewVisits;
	}

	public String getNewVisits() {
		return newVisits;
	}

	public void setNewVisits(String newVisits) {
		this.newVisits = newVisits;
	}

	public String getTotalGBTimeOnPage() {
		return totalGBTimeOnPage;
	}

	public void setTotalGBTimeOnPage(String totalGBTimeOnPage) {
		this.totalGBTimeOnPage = totalGBTimeOnPage;
	}

	public String getTotalGBViewCount() {
		return totalGBViewCount;
	}

	public void setTotalGBViewCount(String totalGBViewCount) {
		this.totalGBViewCount = totalGBViewCount;
	}

	public String getTotalGBCTimeOnPage() {
		return totalGBCTimeOnPage;
	}

	public void setTotalGBCTimeOnPage(String totalGBCTimeOnPage) {
		this.totalGBCTimeOnPage = totalGBCTimeOnPage;
	}

	public String getTotalGBCViewCount() {
		return totalGBCViewCount;
	}

	public void setTotalGBCViewCount(String totalGBCViewCount) {
		this.totalGBCViewCount = totalGBCViewCount;
	}
	
	public String getAvgGBTimeOnPage() {
		long visits = new BigDecimal(this.totalGBViewCount).longValue();
		long seconds = new BigDecimal(this.totalGBTimeOnPage).longValue();
		return TimeRangeUtils.timeLengthDesc(seconds/visits);
	}
	
	public String getAvgGBCTimeOnPage() {
		long visits = new BigDecimal(this.totalGBCViewCount).longValue();
		long seconds = new BigDecimal(this.totalGBCTimeOnPage).longValue();		
		return TimeRangeUtils.timeLengthDesc(seconds/visits);
	}

	public Map<String, Integer> getGbVisitmap() {
		return gbVisitmap;
	}

	public void setGbVisitmap(Map<String, Integer> gbVisitmap) {
		this.gbVisitmap = gbVisitmap;
	}

	public String getTotalGBUniqVisits() {
		return totalGBUniqVisits;
	}

	public void setTotalGBUniqVisits(String totalGBUniqVisits) {
		this.totalGBUniqVisits = totalGBUniqVisits;
	}

	public String getTotalGBCUniqVisits() {
		return totalGBCUniqVisits;
	}

	public void setTotalGBCUniqVisits(String totalGBCUniqVisits) {
		this.totalGBCUniqVisits = totalGBCUniqVisits;
	}
}
