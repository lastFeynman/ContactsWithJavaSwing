package hwadee.test;

import javax.swing.JFrame;

import org.junit.Test;

import hwadee.util.UIUtil;

public class UIUtilTest {

	@Test
	public void testRun() {
		UIUtil.run(new JFrame("qeq"), 100, 100);
	}

}
