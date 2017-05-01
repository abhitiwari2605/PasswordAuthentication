import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordAuthntication {
	
	private final static int WORKLOAD =12;
	private final static int KEY_LENGTH=256;
	
	public static byte[] getNextSalt() throws NoSuchAlgorithmException{
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
	}
	
	public static String hashPassword(String password){
		String salt = BCrypt.gensalt(WORKLOAD);
		String hashed_password = BCrypt.hashpw(password, salt);
		return(hashed_password);

	}

	
	  public static boolean isExpectedPassword(String password, String StoredPassword) {
		    String pwdHash = hashPassword(password);
			boolean password_verified = false;

			if(null == StoredPassword || !StoredPassword.startsWith("$2a$"))
				throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

			password_verified = BCrypt.checkpw(password, StoredPassword);

			return(password_verified);
		  }

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		String password="nothingelsematters";
		String hashPassword=hashPassword(password);
		if(isExpectedPassword(password,hashPassword)){
			System.out.println("password confirmed");
			System.out.println(hashPassword);
		}
		password="yallllaaaaaaaa";
		if(isExpectedPassword(password,hashPassword)){
			System.out.println("password confirmed");
			System.out.println(hashPassword);
		}
		else	System.out.println("wrong password");
		
		Authenticate au = new Authenticate();
		if(au.AuthenticatePassword("nothingelsematters", "abhitiwari2605")){
			System.out.println("password matched");
		}
		else
			System.out.println("ho hallla");
		
	}
	
	

}
