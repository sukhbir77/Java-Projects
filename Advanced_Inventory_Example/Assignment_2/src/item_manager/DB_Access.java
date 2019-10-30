package item_manager;

import java.sql.*;
import java.util.ArrayList;

public class DB_Access implements DB_Vars {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs1;
	private ResultSetMetaData rsmd1;
	
	public DB_Access() {
		MakeTable();
	}
	
	public void MakeTable() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, pass);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int checkLogin(String uname, String upass) {
		int uid = -1; // login is not successfull
		ResultSet rs;
		String sql = "select uid from t_users where LoginName = '"+uname+"' and LoginPass = '"+upass+"'";
		
		try {
			rs = st.executeQuery(sql);
			rs.beforeFirst();
			if(rs.next()) {
				uid = rs.getInt("uid");
			}
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	public String getUserName(int uid) {
		String uname = "";
		
		String sql = "select name from t_users where uid = " + uid;
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				uname = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uname;
	}
	
	public ArrayList<Item> getAllUserItems(int uid) {
		ArrayList<Item> all = new ArrayList<Item>();
		
		String sql = "select iid, itemname, qty, uid from t_items " +
					"where uid = " + uid;
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Item i = new Item(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4));
				all.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	public boolean addNewItem(String itemName, int itemQty, int uid) {
		boolean success = true;
		
		String sql = "insert into t_items(itemname, qty, uid) " +
						"values('"+itemName+"', "+itemQty+", "+uid+")";
		
		try {
			int res = st.executeUpdate(sql);
			if(res == 0) success = false;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	
	public boolean EditItem(String itemName, int itemQty, int iid) {
		boolean success = true;
		
		String sql = "UPDATE `t_items` SET `ItemName` = '"+itemName+"', `Qty` = '"+itemQty+"' WHERE `t_items`.`iid` = "+iid;		
		try {
			int res = st.executeUpdate(sql);
			if(res == 0) success = false;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	public int ModifyAccount(String uname1, String upass1,int uid) {
		try {	
			String sql = "UPDATE `t_users` SET `LoginName` = ?, `LoginPass` = ? WHERE `t_users`.`uid` = "+uid;
			PreparedStatement ps = con.prepareStatement(sql);	           		
			ps.setString(1, uname1);
			ps.setString(2, upass1);
			ps.executeUpdate();
		return 1;
	}
		catch (SQLException e) {
		e.printStackTrace();
		return -1;
	}
		
}

	public int createUser(String uname, String name, String upass) {
		try {	
			String sql = "INSERT INTO `t_users` (`uid`, `LoginName`, `Name`, `LoginPass`) VALUES (NULL, ?, ?,?);";
			PreparedStatement ps = con.prepareStatement(sql);	           		
			ps.setString(1, uname);
			ps.setString(2, name);
			ps.setString(3, upass);
			ps.executeUpdate();
		return 1;
	}
		catch (SQLException e) {
		e.printStackTrace();
		return -1;
	}
		
	}
	public void DeleteItem(int iid) {

		
		String sql = "DELETE FROM `t_items` WHERE `t_items`.`iid` = "+iid;		
		try {
			int res = st.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public ArrayList<Item> specificUser(int iid) {
		ArrayList<Item> all = new ArrayList<Item>();
		
		String sql = "select iid, itemname, qty, uid from t_items " +
					"where iid = " + iid;
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Item i = new Item(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4));
				all.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
		}
}







