package hwadee.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptUtil {
	private static final String METHOD = "SHA";
	
	public static String encrypt(String pwd,String nickName) {
		BigInteger sha = null;
		byte[] pwdByte = pwd.getBytes();
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(METHOD);
			messageDigest.update(pwdByte);
			sha = new BigInteger(messageDigest.digest());
			messageDigest.update((sha.toString(32)+nickName).getBytes());
			sha = new BigInteger(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha.toString(32);
	}
	
	public static boolean verifyPassword(String pwd,String nickName,String key) {
		BigInteger sha = null;
		byte[] pwdByte = pwd.getBytes();
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(METHOD);
			messageDigest.update(pwdByte);
			sha = new BigInteger(messageDigest.digest());
			messageDigest.update((sha.toString(32)+nickName).getBytes());
			sha = new BigInteger(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sha.toString(32).equals(key))
			return true;
		else
			return false;
	}
}
