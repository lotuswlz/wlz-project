package com.cathywu.report.dao.common;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IbatisBaseUtils {
	/**
	 * SqlMapClient instances are thread safe, so you only need one.
	 * In this case, we'll use a static singleton.  So sue me.  ;-)
	 */
	private static SqlMapClient sqlMapper;
	
	private static Logger log = Logger.getLogger(IbatisBaseUtils.class);

	/**
	 * It's not a good idea to put code that can fail in a class initializer,
	 * but for sake of argument, here's how you configure an SQL Map.
	 */
	static {
		try {
			Reader reader = Resources.getResourceAsReader("sql_resources/SqlMapConfig.xml");
			sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
			log.info("sql map already exsit...");
			reader.close(); 
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static List queryForList(String sqlId) throws SQLException{
		return sqlMapper.queryForList(sqlId);
	}
	
	public static List queryForList (String sqlId, Map<String, Object> map) throws SQLException {
		return sqlMapper.queryForList(sqlId, map);
	}
	
	public static List queryForListFromObj (String sqlId, Object obj) throws SQLException{
		return sqlMapper.queryForList(sqlId, obj);
	}

	public static Object queryForObject (String sqlId, Map<String, Object> map) throws SQLException {
		return sqlMapper.queryForObject(sqlId, map);
	}
	
	public static Object queryForObjectFromObj (String sqlId, Object obj) throws SQLException {
        return sqlMapper.queryForObject(sqlId, obj);
    }

	public static void insertObject (String sqlId, Object obj) throws SQLException {
		sqlMapper.insert(sqlId, obj);
	}

	public static void updateObject (String sqlId, Object obj) throws SQLException {
		sqlMapper.update(sqlId, obj);
	}

	public static void deleteObject (String sqlId, Object obj) throws SQLException {
		sqlMapper.delete(sqlId, obj);
	}
}
