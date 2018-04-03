package hwadee.test;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hwadee.ui.LoginJFrame;
import hwadee.util.UIUtil;

public class LoginJFrameTest {
	private FrameFixture frame;
	@Before
	public void start() {
		LoginJFrame loginFrame;
		UIUtil.run(loginFrame = new LoginJFrame(), LoginJFrame.FRAME_WIDTH, LoginJFrame.FRAME_HEIGHT);
		frame = new FrameFixture(loginFrame);
		frame.show();
	}
	
	@After
	public void stop() {
		frame.cleanUp();
	}
	
	@Test
	public void TestLoginJFrameTest() {
		frame.textBox("userName").enterText("123");
		frame.textBox("password").enterText("123");
		frame.button("login").click();
	}

}
