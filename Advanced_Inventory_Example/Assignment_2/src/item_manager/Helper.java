package item_manager;

public class Helper {
	static DB_Access db = new DB_Access();
	public static boolean isValValid(String val) {
		boolean res= true;
		
		if(val.trim().equalsIgnoreCase("")) res = false;
		if(val.trim().length() < 5) res = false;
		
		
		return res;
	}
	
	public static int checkUserLogin(String uname, String upass) throws NullPointerException{
		
		try{
			int uid = db.checkLogin(uname, upass);
			return uid;
			}
		catch (NullPointerException e) {
			System.out.print("This is not working");
			return -1;
		}
		

	}
	
	public static boolean isItemNameValid(String val) {
		boolean res= true;
		
		if(val.trim().equalsIgnoreCase("")) res = false;
		
			for(int i= 1 ;i<val.length();i++) {
				if(!(Character.isAlphabetic(val.charAt(i)))){
					res = false;
				}
		}
		return res;
	}
	
	public static boolean isItemQtyValid(String val) {
		boolean res= true;
		
		if(val.trim().equalsIgnoreCase("")) res = false;
		for(int i= 1 ;i<val.length();i++) {
			if(!(Character.isDigit(val.charAt(i)))){
				res = false;
			}
		}
		try {
			Integer.parseInt(val);
		}catch(Exception e) {
			res = false;
		}
		return res;
	}
	public static int changeUserVal(String uname,String upass,int uid) throws NullPointerException {
		
		
		
		try{
			int uid1 = db.ModifyAccount(uname, upass,uid);
			return uid1;
			}
		catch (NullPointerException e) {
			System.out.print("This is not working too");
			return -1;
		}
		
		
	}
public static int createUser(String uname,String name,String upass) throws NullPointerException {
		
		
		
		try{
			int uid2 = db.createUser(uname,name, upass);
			return uid2;
			}
		catch (NullPointerException e) {
			System.out.print("This is not working too");
			return -1;
		}
}
}











