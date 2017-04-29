import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordAuthntication {
	
	private final static int ITERATIONS =1000;
	private final static int KEY_LENGTH=256;
	
	public static byte[] getNextSalt(){
		byte[] salt ={(byte) 11010110,00000110};
		return salt;
	}
	
	public static byte[] hashPassword(char[] password, byte[] salt){
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
	    try {
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        return skf.generateSecret(spec).getEncoded();
	      } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	        throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	      } finally {
	        spec.clearPassword();
	      }
	}

	
	  public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
		    byte[] pwdHash = hashPassword(password, salt);
		    Arrays.fill(password, Character.MIN_VALUE);
		    if (pwdHash.length != expectedHash.length) return false;
		    for (int i = 0; i < pwdHash.length; i++) {
		      if (pwdHash[i] != expectedHash[i]) return false;
		    }
		    return true;
		  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password="nothingelsematters";
		byte[] salt=getNextSalt();
		byte[] hashPassword=hashPassword(password.toCharArray(), salt);
		if(isExpectedPassword(password.toCharArray(),salt,hashPassword)){
			System.out.println("password confirmed");
			//System.out.println(hashPassword);
		}
		password="yallllaaaaaaaa";
		if(isExpectedPassword(password.toCharArray(),salt,hashPassword)){
			System.out.println("password confirmed");
			//System.out.println(hashPassword);
		}
		else	System.out.println("wrong password");
		
	}

}
