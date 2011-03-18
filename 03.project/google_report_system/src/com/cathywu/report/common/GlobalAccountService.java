package com.cathywu.report.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.cathywu.report.InstanceInvalidException;
import com.cathywu.report.bean.DataValuePool;
import com.cathywu.report.bean.QueryParam;
import com.cathywu.report.util.DataSourceUtils;
import com.cathywu.report.util.TimeRangeUtils;
import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;
import com.google.gdata.data.analytics.DataSource;
import com.google.gdata.data.analytics.Metric;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class GlobalAccountService {
	
	public static final String ACCOUNTS_URL = "https://www.google.com/analytics/feeds/accounts/default";

	public static final String DATA_URL = "https://www.google.com/analytics/feeds/data";

	private static GlobalAccountService service;
	
	private static AccountFeed accountFeed;
	
	private AnalyticsService myService;
	
	private GlobalAccountService(String username, String password) throws AuthenticationException {
		this.myService = new AnalyticsService("google_analytics_report");
		this.myService.setUserCredentials(username, password);
	}
	
	public static synchronized GlobalAccountService getInstance(String username, String password) throws AuthenticationException {
		if (service == null) {
			service = new GlobalAccountService(username, password);
			try {
			    accountFeed = service.connectAccountFeed();
				DataValuePool.setGlobalAccountService(service);
				DataValuePool.updateDataPool();
			} catch (InstanceInvalidException e) {
				throw new AuthenticationException(e.getMessage());
			} catch (MalformedURLException e) {
			    System.out.println("Error in connecting ACCOUNT URL.");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
		return service;
	}
	
	public static synchronized GlobalAccountService getInstance() throws InstanceInvalidException {
		if (!isServiceAvailable()) {
			throw new InstanceInvalidException("Instance invalid, please use method \"getInstance(username, password)\" first.");
		} else {
			return service;
		}
	}
	
	public static boolean isServiceAvailable() {
		if (service == null || service.myService == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public synchronized DataFeed getDataFeed(QueryParam queryParam) throws InstanceInvalidException {
		try {
			Thread.sleep(100);
			DataQuery query = new DataQuery(new URL(DATA_URL));
			query.setIds(accountFeed.getEntries().get(0).getTableId().getValue());
			fillQueryParam(queryParam, query);
			
			DataFeed dataFeed = myService.getFeed(query, DataFeed.class);
			return dataFeed;
		} catch (MalformedURLException e) {
			throw new InstanceInvalidException("ACCOUNT URL IS WRONG");
		} catch (IOException e) {
			throw new InstanceInvalidException("ACCOUNT URL IS WRONG");
		} catch (ServiceException e) {
			throw new InstanceInvalidException("ACCOUNT URL IS WRONG");
		} catch (InterruptedException e) {
			throw new InstanceInvalidException("System is busy now, please try again later.");
		}
	}

    private AccountFeed connectAccountFeed() throws MalformedURLException,
            IOException, ServiceException, InstanceInvalidException {
        URL feedUrl = new URL(ACCOUNTS_URL);
        AccountFeed accountFeed = myService.getFeed(feedUrl, AccountFeed.class);
        if (accountFeed.getEntries().isEmpty()) {
        	throw new InstanceInvalidException("Account Feed Invalid");
        }
        return accountFeed;
    }

	private void fillQueryParam(QueryParam queryParam, DataQuery query) {
		query.setDimensions(queryParam.getDimensions());
		query.setMetrics(queryParam.getMetrics());
		if (queryParam.getMaxResults() >= 0) {
			query.setMaxResults(queryParam.getMaxResults());
		}
		query.setFilters(queryParam.getFilters());
		query.setSort(queryParam.getSort());
		query.setStartDate(queryParam.getStartDate());
		query.setEndDate(queryParam.getEndDate());
		query.setStartIndex(queryParam.getStartIndex());
	}
	
	public static void invalid() {
		service.disconnectService();
		service = null;
		System.gc();
	}
	
	private void disconnectService() {
		this.myService = null;
	}
	
	public static void main(String[] args) {
		try {
			GlobalAccountService s = GlobalAccountService.getInstance("cathywu@iwanttobuy.com.au", "4666lotuswlz");
			String[] timeRange = TimeRangeUtils.latestRangeDesc();
			
			Map<String, QueryParam> map = DataSourceUtils.getQueryParamMap();
			
			QueryParam query = null;
			
			for (Iterator<Entry<String, QueryParam>> it = map.entrySet().iterator(); it.hasNext();) {
				Entry<String, QueryParam> e = it.next();
				query = DataSourceUtils.getQueryParamMap().get(e.getKey());
				query.setStartDate(timeRange[0]);
				query.setEndDate(timeRange[1]);
				DataFeed dataFeed = s.getDataFeed(query);
				System.out.println("-----------" + e.getKey() + " (" + query.getDesc() + ")-----------");
				for (DataSource source : dataFeed.getDataSources()) {
					System.out.println("Table Id: " + source.getTableId());
					System.out.println("Table Name: " + source.getTableName());
				}
				System.out.println("From " + dataFeed.getStartDate().getValue() + " to " + dataFeed.getEndDate().getValue());
				System.out.println("Total Record count: " + dataFeed.getTotalResults() + "");
				
				for (Metric m : dataFeed.getAggregates().getMetrics()) {
					System.out.println(m.getName() + ": " + m.getValue());
				}
			}
			
			System.out.println("---------------------");
			DataFeed dataFeed = s.getDataFeed(map.get("5"));
			for (DataEntry entry : dataFeed.getEntries()) {
				System.out.println(entry.stringValueOf("ga:pagePath") + ":\t\t" + entry.stringValueOf("ga:uniquePageviews"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
