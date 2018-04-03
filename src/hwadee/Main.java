package hwadee;

import hwadee.ui.LoginJFrame;
import hwadee.util.UIUtil;

public class Main {

	public static void main(String[] args) {
		UIUtil.run(new LoginJFrame(),LoginJFrame.FRAME_WIDTH,LoginJFrame.FRAME_HEIGHT);
	}

}
