package hwadee.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UserIndexJFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 500;
	
	private JTabbedPane userTabbedPane;
	private JPanel allContactPane,searchPane,personalInfoPane,birthdayHintPane;
	
	public UserIndexJFrame() {
		this.setTitle("电话簿");
		this.setLayout(null);
		this.setBounds(500,300,UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		userTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		userTabbedPane.setBounds(0, 0, UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.add(userTabbedPane);
		
		allContactPane = new AllContactPane(this);
		userTabbedPane.addTab("所有联系人", allContactPane);
		
		searchPane = new SearchPane(this);
		userTabbedPane.addTab("搜索联系人", searchPane);
		
		personalInfoPane = new PersonalCenterPane();
		userTabbedPane.addTab("个人中心", personalInfoPane);
		
		birthdayHintPane = new BirthDayHintPane();
		userTabbedPane.addTab("联系人生日提醒", birthdayHintPane);
	}	
}
