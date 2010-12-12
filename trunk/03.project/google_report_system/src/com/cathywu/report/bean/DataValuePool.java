package com.cathywu.report.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cathywu.report.InstanceInvalidException;
import com.cathywu.report.common.GlobalAccountService;
import com.cathywu.report.util.DataSourceUtils;
import com.cathywu.report.util.TimeRangeUtils;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;

public class DataValuePool {

    private static Map<String, DataFeed> valueMap;
    static {
        valueMap = new HashMap<String, DataFeed>();
    }
    
    private static GlobalAccountService globalAccountService;
    
    public static void main(String[] args) {
        
    }

    public synchronized static Map<String, DataFeed> getValueMap() {
    	if (valueMap.isEmpty()) {
    		try {
				updateDataPool();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
    	}
        return valueMap;
    }

	public synchronized static void updateDataPool() throws InstanceInvalidException {
    	if (globalAccountService == null || !GlobalAccountService.isServiceAvailable()) {
    		throw new InstanceInvalidException("Please execute method \"setGlobalAccountService\" first.");
    	}
		String[] timeRange = TimeRangeUtils.latestRangeDesc();
		
		Map<String, QueryParam> map = DataSourceUtils.getQueryParamMap();
		
		QueryParam query = null;
		
		for (Iterator<Entry<String, QueryParam>> it = map.entrySet().iterator(); it.hasNext();) {
			Entry<String, QueryParam> e = it.next();
			query = DataSourceUtils.getQueryParamMap().get(e.getKey());
			query.setStartDate(timeRange[0]);
			query.setEndDate(timeRange[1]);
			DataFeed dataFeed = null;
			if (!e.getKey().equals("4") && !e.getKey().equals("3")) {
				dataFeed = globalAccountService.getDataFeed(query);
			} else {
				dataFeed = globalAccountService.getDataFeed(query);
				int maxResult = dataFeed.getTotalResults();
				int pageSize = new BigDecimal(maxResult).divide(new BigDecimal(2000), RoundingMode.UP).intValue();
				dataFeed.setEntries(pagedList(query, pageSize));
			}
			valueMap.put(timeRange[0] + "-" + timeRange[1] + "-"
					+ e.getKey(), dataFeed);
		}
	}
	
	private static List<DataEntry> pagedList(QueryParam query, int pageSize) throws InstanceInvalidException {
		List<DataEntry> list = new ArrayList<DataEntry>();
		query.setMaxResults(2000);
		DataFeed dataFeed = null;
		for (int i = 0; i < pageSize; i++) {
			query.setStartIndex(i * 2000 + 1);
			dataFeed = globalAccountService.getDataFeed(query);
			list.addAll(dataFeed.getEntries());
		}
		return list;
	}

	public static void setGlobalAccountService(
			GlobalAccountService globalAccountService) {
		DataValuePool.globalAccountService = globalAccountService;
	}
}
