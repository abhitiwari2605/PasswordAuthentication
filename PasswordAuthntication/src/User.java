import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	private String firstName, lastName, dob,userName,password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void createUser(String firstName,String lastName,String dob,String password,String userName){
		DBConnection C;
		Connection Con;
		String hashedPassword= PasswordAuthntication.hashPassword(password);
		C=new DBConnection();
		Con=C.returnConnection();
		String query="insert into user values (?,?,?,?,?,?)";
		try {
			PreparedStatement Pstmt=Con.prepareStatement(query);
			Pstmt.setString(1,"1");
			Pstmt.setString(2,firstName);
			Pstmt.setString(3,lastName);
			Pstmt.setString(4,dob);
			Pstmt.setString(5,userName);
			Pstmt.setString(6,hashedPassword);
			Pstmt.executeUpdate();
			
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
	}
}
