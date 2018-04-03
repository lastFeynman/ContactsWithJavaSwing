package hwadee.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hwadee.util.EncryptUtil;

public class EncryptUtilTest {

	@Test
	public void testEncrypt() {
		String key = EncryptUtil.encrypt("123456", "1");
		System.out.println(key);
		assertNotEquals(null, key);
	}

	@Test
	public void testVerifyPassword() {
		boolean isRightPassword = EncryptUtil.verifyPassword("123456","1", "123456");
		assertEquals(false, isRightPassword);
	}

}
