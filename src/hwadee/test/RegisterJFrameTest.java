package hwadee.test;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hwadee.ui.RegisterJFrame;
import hwadee.util.UIUtil;

public class RegisterJFrameTest {
	private FrameFixture frame;
	@Before
	public void start() {
		RegisterJFrame registerFrame;
		UIUtil.run(registerFrame = new RegisterJFrame("зЂВс",null), RegisterJFrame.FRAME_WIDTH, RegisterJFrame.FRAME_HEIGHT);
		frame = new FrameFixture(registerFrame);
		frame.show();
	}
	
	@After
	public void stop() {
		frame.cleanUp();
	}
	
	@Test
	public void testRegisterJFrame() {
		frame.textBox("nickName").setText("123");
	}

}
