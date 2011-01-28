package com.cathywu.report.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cathywu.report.bean.DataValuePool;
import com.cathywu.report.common.GlobalAccountService;
import com.cathywu.report.form.NowTestForm;
import com.cathywu.report.util.TimeRangeUtils;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;
import com.google.gdata.data.analytics.Metric;
import com.opensymphony.xwork2.ActionSupport;

public class NowTestAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -822998030799095453L;
	private String username;
	private String password;
	private String timeRange;
	private String groupbuyIds;
	private List<String> groupbuyVisitList;
	
	private Map<String, DataFeed> dataMap;
	
	private NowTestForm form;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTimeRange() {
		return timeRange;
	}

	@Override
	public String execute() throws Exception {
		try {
			if (!GlobalAccountService.isServiceAvailable()
					&& this.username != null && this.password != null
					&& !this.username.trim().equals("")
					&& !this.password.trim().equals("")) {
				GlobalAccountService.getInstance(username, password);
			} else if (!GlobalAccountService.isServiceAvailable()
					&& (this.username == null || this.password == null
							|| this.username.trim().equals("") || this.password
							.trim().equals(""))) {
				return ERROR;
			}

			dataMap = DataValuePool.getValueMap();

			String[] times = TimeRangeUtils.latestRangeDesc();
			this.timeRange = times[0] + " ~ " + times[1];
			fillData();
			printVisitToEachDeal();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/** each value */
	
	public String getTotalVisitorCount() {
		return null;
	}
	
	private void fillData() {
		String[] times = TimeRangeUtils.latestRangeDesc();
		System.out.println(times[0] + "-" + times[1]);
		form = new NowTestForm();
		// direct value
		for (Metric m : dataMap.get(times[0] + "-" + times[1] + "-" + "2").getAggregates().getMetrics()) {
			if (m.getName().equals("ga:visits")) {
				form.setTotalVisits(m.getValue());
			} else if (m.getName().equals("ga:visitors")) {
				form.setTotalVisitors(m.getValue());
			} else if (m.getName().equals("ga:newVisits")) {
				form.setTotalNewVisits(m.getValue());
			}
		}
		// bounce / return visits rate
		long top10Visits = 0;
		long top10Bounces = 0;
		long top10NewVisits = 0;
		for (DataEntry de : dataMap.get(times[0] + "-" + times[1] + "-" + "1").getEntries()) {
			top10Visits += Long.parseLong(de.stringValueOf("ga:visits"));
			top10Bounces += Long.parseLong(de.stringValueOf("ga:bounces"));
			top10NewVisits += Long.parseLong(de.stringValueOf("ga:newVisits"));
		}
		form.setBounceCount(String.valueOf(top10Bounces));
		form.setVisits(String.valueOf(top10Visits));
		form.setNewVisits(String.valueOf(top10NewVisits));
		
		// time/visits on groupbuy deal page
		for (Metric m : dataMap.get(times[0] + "-" + times[1] + "-" + "3").getAggregates().getMetrics()) {
			if (m.getName().equals("ga:pageviews")) {
				form.setTotalGBViewCount(m.getValue());
			} else if (m.getName().equals("ga:timeOnPage")) {
				form.setTotalGBTimeOnPage(m.getValue());
			} else if (m.getName().equals("ga:uniquePageviews")) {
				form.setTotalGBUniqVisits(m.getValue());
			}
		}
		String regex = "^/groupbuy/[^/]+/([0-9]+)\\??.*$";
		String path = null;
		Integer uniqPageViews = 0;
		form.setGbVisitmap(new HashMap<String, Integer>());
		System.out.println("GB in total: " + dataMap.get(times[0] + "-" + times[1] + "-" + "3").getEntries().size());
		System.out.println("GB in total: " + dataMap.get(times[0] + "-" + times[1] + "-" + "4").getEntries().size());
		for (DataEntry de : dataMap.get(times[0] + "-" + times[1] + "-" + "3").getEntries()) {
			path = de.stringValueOf("ga:pagePath");
			if (!path.matches(regex)) {
				continue;
			}
			path = path.replaceAll(regex, "$1");
			uniqPageViews = form.getGbVisitmap().get(path);
			if (uniqPageViews == null) {
				uniqPageViews = 0;
			}
			uniqPageViews += Integer.parseInt(de.stringValueOf("ga:uniquePageviews"));
			form.getGbVisitmap().put(path, uniqPageViews);
		}
		
		// time/visits on groupbuy deal page
		for (Metric m : dataMap.get(times[0] + "-" + times[1] + "-" + "4").getAggregates().getMetrics()) {
			if (m.getName().equals("ga:pageviews")) {
				form.setTotalGBCViewCount(m.getValue());
			} else if (m.getName().equals("ga:timeOnPage")) {
				form.setTotalGBCTimeOnPage(m.getValue());
			} else if (m.getName().equals("ga:uniquePageviews")) {
				form.setTotalGBCUniqVisits(m.getValue());
			}
		}
		
		uniqPageViews = 0;
		regex = "^/groupserve/[^/]+/([0-9]+)\\??.*$";
		form.setGbcVisitmap(new HashMap<String, Integer>());
		for (DataEntry de : dataMap.get(times[0] + "-" + times[1] + "-" + "4").getEntries()) {
			path = de.stringValueOf("ga:pagePath");
			if (!path.matches(regex)) {
				continue;
			}
			path = path.replaceAll(regex, "$1");
			uniqPageViews = form.getGbcVisitmap().get(path);
			if (uniqPageViews == null) {
				uniqPageViews = 0;
			}
			uniqPageViews += Integer.parseInt(de.stringValueOf("ga:uniquePageviews"));
			form.getGbcVisitmap().put(path, uniqPageViews);
		}
		
		// new visit time on deal page
		for (Metric m : dataMap.get(times[0] + "-" + times[1] + "-" + "5").getAggregates().getMetrics()) {
			if (m.getName().equals("ga:pageviews")) {
				form.setNewVisitViewCount(m.getValue());
			} else if (m.getName().equals("ga:timeOnPage")) {
				form.setNewVisitTimeOnPage(m.getValue());
			}
		}
		System.out.println(form.getAvgGBCTimeOnPage());
	}
	
	private void printVisitToEachDeal() {
		String[] arr = this.groupbuyIds.split("\n");
		int campaignId = 0;
		int temp1 = 0;
		int temp2 = 0;
		this.groupbuyVisitList = new ArrayList<String>();
		for (String str : arr) {
			str = str.trim();
			campaignId = Integer.parseInt(str);
			if (form.getGbVisitmap().get(str) != null) {
				temp1 = form.getGbVisitmap().get(str);
				//System.out.println("temp1=" + temp1);
			}
			if (form.getGbcVisitmap().get(str) != null) {
				temp2 = form.getGbcVisitmap().get(str);
				//System.out.println("temp2=" + temp2);
			}
			System.out.println(campaignId + ": " + (temp1 + temp2));
			this.groupbuyVisitList.add(campaignId + ": " + (temp1 + temp2));
		}
	}

	public NowTestForm getForm() {
		return form;
	}
	
	public static void main(String[] args) {
		NowTestAction action = new NowTestAction();
		action.setUsername("cathywu@iwanttobuy.com.au");
		action.setPassword("4666lotuswlz");
		try {
			action.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getGroupbuyIds() {
		return groupbuyIds;
	}

	public void setGroupbuyIds(String groupbuyIds) {
		this.groupbuyIds = groupbuyIds;
	}

	public List<String> getGroupbuyVisitList() {
		return groupbuyVisitList;
	}
}
