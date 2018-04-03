package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminIndexJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 500;
	
	private JTabbedPane adminTabbedPane;
	private JPanel allContactPane,searchPane,personalInfoPane,allUserPane;
	
	public AdminIndexJFrame() {
		this.setTitle("电话簿后台");
		this.setLayout(null);
		this.setBounds(500,300,UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		adminTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		adminTabbedPane.setBounds(0, 0, UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.add(adminTabbedPane);
		
		allContactPane = new AllContactPane(this);
		adminTabbedPane.addTab("所有联系人", allContactPane);
		
		searchPane = new SearchPane(this);
		adminTabbedPane.addTab("搜索联系人", searchPane);
		
		personalInfoPane = new PersonalCenterPane();
		adminTabbedPane.addTab("个人中心", personalInfoPane);
		
		allUserPane = new ManageUserPane(this);
		adminTabbedPane.addTab("管理所有用户", allUserPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
