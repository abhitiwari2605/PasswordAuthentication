import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Authenticate {
	DBConnection C;
	Statement S;
	Connection Con;
	
	Authenticate(){
		C=new DBConnection();
		Con=C.returnConnection();
		
	}
	public boolean AuthenticatePassword(String password, String username) throws NoSuchAlgorithmException{
		String passwordDB="";
		try {
			
			S=Con.createStatement();
			String query="select password from user where username = '"+username+" '";
			ResultSet rs= S.executeQuery(query);
			while(rs.next()){
				passwordDB=rs.getString("password");
				System.out.println(passwordDB);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				Con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		PasswordAuthntication pw = new PasswordAuthntication();
		if(PasswordAuthntication.isExpectedPassword(password, passwordDB)){
			return true;
		}
		else return false;
	}

}
