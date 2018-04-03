package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hwadee.entity.User;
import hwadee.service.ILoginService;
import hwadee.service.impl.LoginServiceImpl;
import hwadee.util.UIUtil;

public class LoginJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 250;
	
	private JLabel userNameLabel;
	private JTextField userName;
	private JLabel passwordLabel;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private JButton signUp;
	
	public LoginJFrame() {
		this.setLayout(null);
		this.setBounds(100, 100, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle("登录电话簿");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		userNameLabel = new JLabel("用户名");
		userNameLabel.setBounds(50, 50, 50, 20);
		this.add(userNameLabel);
		
		userName = new JTextField();
		userName.setBounds(130, 50, 220, 25);
		this.add(userName);
		userName.setName("userName");
		
		passwordLabel = new JLabel("密码");
		passwordLabel.setBounds(50, 100, 50, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 100, 220, 25);
		this.add(password);
		password.setName("password");
		
		login = new JButton("登录");
		login.setBounds(50, 150, 70, 25);
		login.addActionListener(this);
		this.add(login);
		login.setName("login");
		
		reset = new JButton("重置");
		reset.setBounds(165, 150, 70, 25);
		reset.addActionListener(this);
		this.add(reset);
		reset.setName("reset");
		
		signUp = new JButton("注册");
		signUp.setBounds(280, 150, 70, 25);
		signUp.addActionListener(this);
		this.add(signUp);
		signUp.setName("signUp");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "登录") {
			if(userName.getText().length() != 0 && password.getPassword().length != 0) {
				ILoginService userService = new LoginServiceImpl();
				User user = null;
				if((user = userService.verifyUser(userName.getText(),password.getPassword())) != null) {
					UIStatus.current_user = user;
					if(user.isAdmin()) {
						//转到管理员界面
						this.dispose();
						JOptionPane.showMessageDialog(null, "管理员登录成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}else {
						//转到普通用户界面
						this.dispose();
						JOptionPane.showMessageDialog(null, "普通用户登录成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
					}
				}else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "消息提示", JOptionPane.WARNING_MESSAGE);
				}
			}else if(userName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0)
				JOptionPane.showMessageDialog(null, "请输入密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			
		}else if(e.getActionCommand() == "重置") {
			userName.setText("");
			password.setText("");
		}else {
			//转到注册界面
			this.dispose();
			UIUtil.run(new RegisterJFrame("注册",null), RegisterJFrame.FRAME_WIDTH, RegisterJFrame.FRAME_HEIGHT);
		}
	}
	
}
