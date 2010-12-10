package com.cathywu.report.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.cathywu.report.InstanceInvalidException;
import com.cathywu.report.common.GlobalAccountService;
import com.cathywu.report.util.DataSourceUtils;
import com.cathywu.report.util.TimeRangeUtils;
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
			DataFeed dataFeed = globalAccountService.getDataFeed(query);
			valueMap.put(timeRange[0] + "-" + timeRange[1] + "-" + e.getKey(), dataFeed);
		}
	}

	public static void setGlobalAccountService(
			GlobalAccountService globalAccountService) {
		DataValuePool.globalAccountService = globalAccountService;
	}
}
