package impData;

import java.sql.*;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;

public class DataBaseBasic {
	
	private final String JDBC_URL ="jdbc:oracle:thin:@127.0.0.1:1521:oradb";
	private final String USER	   ="iwant";
	
	private final String PWD	   = "tobuy";
	
	private final String DRIVER  = "oracle.jdbc.driver.OracleDriver";
	
	private ResultSet rs = null;
	private Connection cn=null; 
	private Statement stm=null;
	   
	
	private void initDB(){
		System.out.println("initDB");
		try{
			Class.forName(DRIVER).newInstance();
			cn= DriverManager.getConnection(JDBC_URL,USER,PWD);
			stm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(Exception e){
			System.out.print("DataBaseBasic:"+e.getMessage());
		}

	}
	
	
	public int execute(String sql) throws SQLException{
		int rowcount=0;
		initDB();
		stm.execute(sql);
		rowcount = stm.getUpdateCount();
		closeDB();
		return rowcount;
	}
	
	public void groupExecute(List<String> sqls) throws SQLException{
		initDB();
		if(stm != null){
			stm.close();
			stm = null;
		}
		System.out.println("start");
		for(int i = 0; i < sqls.size(); i++){
			stm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println(i + ":" + sqls.get(i));
			stm.execute(sqls.get(i));
			stm.close();
		}
		closeDB();
	}
	
	
	public int executeUpdate(String sql) throws SQLException{
		int rowcount=0;
		initDB();
		stm.executeUpdate(sql);
		rowcount = stm.getUpdateCount();
		closeDB();
		return rowcount;
	}
	
	
	public ResultSet executQuery(String sql) throws SQLException{
		initDB();
		rs=stm.executeQuery(sql);
		return rs;
	}
	
	
	
	public void closeDB() throws SQLException{
		if(stm!=null){
			stm.close();
			stm=null;
			
		}
		if(cn!=null){
			cn.close();
			cn=null;
		}
	}
	
	public static void main(String[] args){
		String sql = "select count(*) as cnt from tb_country_list";
		DataBaseBasic db = new DataBaseBasic();
		try {
			db.executQuery(sql);
			while(db.rs.next()){
				System.out.println(db.rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

