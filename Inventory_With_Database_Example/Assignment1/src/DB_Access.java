import java.sql.*;
import java.util.ArrayList;

public class DB_Access implements DB_Vars{
 
 //All database code will be written in this class
 
 private Connection con;
 private Statement st;
 
 public DB_Access() {
  
  try {
   
   Class.forName(driver);
   con = DriverManager.getConnection(url,username,pass);
   st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
  } 
  
  catch (Exception e) {
   e.printStackTrace();
  }
 }
 
 public  ArrayList<Phone> getAllProducts() 
 {
  ArrayList<Phone> list =new ArrayList<Phone>();
  String sql= "Select id,name,address,phone FROM phonebook";
  
  try {
   ResultSet rs=st.executeQuery(sql);
   while(rs.next())
   {
    Phone p=new Phone(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
    list.add(p);
   }
  } 
  
  catch (SQLException e) {
   e.printStackTrace();
  }
  return list;
 }
 //To insert using java.
 public boolean addProductV1(Phone p)
 {
  boolean success=true;
  String sql="SELECT id,name,address,phone FROM phonebook";
  
  try {
   ResultSet rs = st.executeQuery(sql);
   rs.moveToInsertRow();
   rs.updateString(2, p.getName());
   rs.updateString(3,p.getAddress());
   rs.updateString(4,p.getPhone());
   rs.insertRow();
  }
  
  catch (SQLException e) {
   success=false;
   e.printStackTrace();
  }
  
  return success;
 }
 
 public boolean delProduct(int id)
 {
  boolean success=true;
  String sql ="DELETE FROM phonebook WHERE id=" +id;
  try {
   int res= st.executeUpdate(sql);
  }
  
  catch(SQLException e)
  {
   success=false;
   e.printStackTrace();
  }
  return success;
 }
 
 public ArrayList<Phone> searchProduct(String val)
 {
  ArrayList<Phone> list =new ArrayList<Phone>();
  String sql= "Select id,name,address,phone FROM phonebook "+
        "WHERE name LIKE '%"+val+"%' OR phone LIKE '%"+val+"%'";
  try {
   ResultSet rs=st.executeQuery(sql);
   while(rs.next())
   {
    Phone p=new Phone(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
    list.add(p);
   }
  } 
  
  catch (SQLException e) {
   e.printStackTrace();
  }
  return list;
  
 }
 public boolean modifyProduct(int id,Phone p)
 {
  boolean success = true;
  String sql= "Select id,name,address,phone from phonebook where id = "+id;
  ResultSet rs;
  try {
   rs = st.executeQuery(sql); 
   //Move to the row
   rs.next();
   // Update values in the row
   rs.updateString(2, p.getName());
   rs.updateString(3, p.getAddress());
   rs.updateString(4, p.getPhone());
   //Commit The Changes
   rs.updateRow();
   
  } catch (SQLException e) {
   success=false;
   e.printStackTrace();
  }
  
  
  return success;
 }
}
