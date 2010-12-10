package com.cathywu.report.common;

import java.util.HashMap;
import java.util.Map;

import com.google.gdata.data.analytics.DataFeed;

public class DataSourcePool {
	
	private static String currentRangeDesc;

	private static Map<String, DataFeed> dataFeedMap;
	static {
		dataFeedMap = new HashMap<String, DataFeed>();
	}
	
	public static void clear() {
		if (dataFeedMap != null) {
			dataFeedMap.clear();
		}
	}
}
