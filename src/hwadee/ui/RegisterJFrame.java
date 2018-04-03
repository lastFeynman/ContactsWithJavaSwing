package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.entity.User;
import hwadee.service.ISignUpService;
import hwadee.service.impl.SignUpServiceImpl;
import hwadee.util.UIUtil;

public class RegisterJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 650;
	
	private JLabel nickNameLabel, realNameLabel, genderLabel, passwordLabel, repasswordLabel;
	private JLabel emailLabel, birthdayLabel, numberLabel, addressLabel, hintLabel;
	private JTextField nickName,realName,email,number;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private JPasswordField password,repassword;
	private DateObserver birthday;
	private JTextArea address;
	private JButton signUp,reset;
	
	private JLabel nickNameHint,realNameHint,passwordHint,emailHint,numberHint,addressHint;
	
	private String command;
	@SuppressWarnings("unused")
	private User editedUser;
	
	public RegisterJFrame(String command,User editedUser) {
		this.command = command;
		this.editedUser = editedUser;
		
		this.setLayout(null);
		this.setBounds(100, 100, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle(this.command+"用户");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		hintLabel = new JLabel("带\" * \"的项为必填项");
		hintLabel.setBounds(150, 10, 200, 25);
		hintLabel.setForeground(Color.RED);
		this.add(hintLabel);
		
		nickNameHint = new JLabel("包含数字、大小写字母及下划线，不超过50位");
		nickNameHint.setBounds(100, 30, 300, 25);
		nickNameHint.setForeground(Color.BLUE);
		this.add(nickNameHint);

		nickNameLabel = new JLabel("*用户名");
		nickNameLabel.setBounds(50, 50, 80, 25);
		this.add(nickNameLabel);
		
		nickName = new JTextField();
		nickName.setBounds(130, 50, 220, 25);
		nickName.setName("nickName");
		this.add(nickName);
		
		realNameHint = new JLabel("中文名不超过10个字，英文名不超过50个字母");
		realNameHint.setBounds(100, 80, 300, 25);
		realNameHint.setForeground(Color.BLUE);
		this.add(realNameHint);
		
		realNameLabel = new JLabel("真实姓名");
		realNameLabel.setBounds(50,100,80,25);
		this.add(realNameLabel);
		
		realName = new JTextField();
		realName.setBounds(130,100,220,25);
		realName.setName("realName");
		this.add(realName);
		
		genderLabel = new JLabel("*性别");
		genderLabel.setBounds(50, 150, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("男",true);
		male.setBounds(130, 150, 110, 25);
		this.add(male);
		
		female = new JRadioButton("女");
		female.setBounds(240, 150, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		passwordHint = new JLabel("6-18位包含数字、大小写字母、特殊字符的串");
		passwordHint.setBounds(100, 180, 300, 25);
		passwordHint.setForeground(Color.BLUE);
		this.add(passwordHint);
		
		passwordLabel = new JLabel("*密码");
		passwordLabel.setBounds(50, 200, 80, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 200, 220, 25);
		password.setName("password");
		this.add(password);
		
		repasswordLabel = new JLabel("*重复密码");
		repasswordLabel.setBounds(50, 250, 80, 25);
		this.add(repasswordLabel);
		
		repassword = new JPasswordField();
		repassword.setBounds(130, 250, 220, 25);
		repassword.setName("repassword");
		this.add(repassword);
		
		emailHint = new JLabel("abc@ab.cd格式，不超过50个字符");
		emailHint.setBounds(100, 280, 300, 25);
		emailHint.setForeground(Color.BLUE);
		this.add(emailHint);
		
		emailLabel = new JLabel("*邮箱");
		emailLabel.setBounds(50, 300, 80, 25);
		this.add(emailLabel);
		
		email = new JTextField();
		email.setBounds(130, 300, 220, 25);
		email.setName("email");
		this.add(email);
		
		birthdayLabel = new JLabel("生日");
		birthdayLabel.setBounds(50, 350, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("请选择出生日期");
		birthday.setBounds(130, 350, 220, 25);
		birthday.setName("birthday");
		this.add(birthday);
		
		numberHint = new JLabel("11纯数字电话或手机号码");
		numberHint.setBounds(100, 380, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*电话");
		numberLabel.setBounds(50, 400, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 400, 220, 25);
		number.setName("number");
		this.add(number);
		
		addressHint = new JLabel("地址，250字以内");
		addressHint.setBounds(100, 430, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("地址");
		addressLabel.setBounds(50, 450, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 450, 220, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		address.setName("address");
		this.add(address);
		
		signUp = new JButton(this.command);
		signUp.setBounds(50, 550, 120, 25);
		signUp.setName("signUp");
		signUp.addActionListener(this);
		this.add(signUp);
		
		reset = new JButton("重置");
		reset.setBounds(230, 550, 120, 25);
		reset.setName("reset");
		reset.addActionListener(this);
		this.add(reset);
		
		if(this.command.equals("编辑")) {
			nickName.setText(editedUser.getUserNickName());
			realName.setText(editedUser.getUserRealName());
			if(editedUser.getUserGender().equals("男"))
				male.setSelected(true);
			else
				female.setSelected(true);
			email.setText(editedUser.getUserEmail());
			if(editedUser.getUserBirthday()!=null && !editedUser.getUserBirthday().equals(""))
				birthday.setText(editedUser.getUserBirthday().toString());
			number.setText(editedUser.getUserNumber());
			address.setText(editedUser.getUserAddress());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "注册" || e.getActionCommand() == "添加" || e.getActionCommand() == "编辑") {
			if(nickName.getText().length() != 0 && password.getPassword().length != 0 && repassword.getPassword().length != 0 && email.getText().length() != 0 && number.getText().length() != 0) {
				ISignUpService signUpService = new SignUpServiceImpl();
				if(signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText())
						&& signUpService.isValidAddress(address.getText())
						&& (this.command.equals("添加") || this.command.equals("注册"))
						|| this.command.equals("编辑")
						&& signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText(),editedUser.getUserId()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText(),editedUser.getUserId()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText(),editedUser.getUserId())
						&& signUpService.isValidAddress(address.getText())) {
					if(this.command.equals("添加") || this.command.equals("注册")) {
						if(!signUpService.insertUser(nickName.getText(), 
								realName.getText(), 
								male.isSelected()?"男":"女", 
								password.getPassword(), 
								email.getText(), 
								birthday.getText(), 
								number.getText(), 
								address.getText()))
							JOptionPane.showMessageDialog(null, this.command+"失败", "消息提示", JOptionPane.WARNING_MESSAGE);
						else {
							this.dispose();
							JOptionPane.showMessageDialog(null, this.command+"成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						}
						if(this.command.equals("注册"))
							UIUtil.run(new LoginJFrame(), LoginJFrame.FRAME_WIDTH, LoginJFrame.FRAME_HEIGHT);
						else
							UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}else {
						if(!signUpService.editUser(this.editedUser,
								nickName.getText(), 
								realName.getText(), 
								male.isSelected()?"男":"女", 
								password.getPassword(), 
								email.getText(), 
								birthday.getText(), 
								number.getText(), 
								address.getText(),
								this.editedUser.getRegisterDate()))
							JOptionPane.showMessageDialog(null, "编辑失败", "消息提示", JOptionPane.WARNING_MESSAGE);
						else {
							this.dispose();
							JOptionPane.showMessageDialog(null, "编辑成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						}
						UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}
				}else if(!signUpService.isValidNickName(nickName.getText())){
					JOptionPane.showMessageDialog(null, "用户名无效，注意用户名字符与长度限制", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNickName(nickName.getText())) {
					JOptionPane.showMessageDialog(null, "用户名已存在", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidRealName(realName.getText())) {
					JOptionPane.showMessageDialog(null, "姓名无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectPassword(password.getPassword())) {
					JOptionPane.showMessageDialog(null, "密码无效，注意密码的字符限制与长度限制", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一样", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "无效的邮箱地址", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "邮箱地址已被注册", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "生日无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "号码无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "号码已被注册", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "无效的地址", "消息提示", JOptionPane.WARNING_MESSAGE);
				}
				
			}else if(nickName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请输入密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(repassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请确认密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(email.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入邮箱", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(number.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入号码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			if(this.command.equals("编辑")) {
				nickName.setText(editedUser.getUserNickName());
				realName.setText(editedUser.getUserRealName());
				if(editedUser.getUserGender().equals("男"))
					male.setSelected(true);
				else
					female.setSelected(true);
				email.setText(editedUser.getUserEmail());
				if(editedUser.getUserBirthday()!=null && !editedUser.getUserBirthday().equals(""))
					birthday.setText(editedUser.getUserBirthday().toString());
				number.setText(editedUser.getUserNumber());
				address.setText(editedUser.getUserAddress());
			}else {
				nickName.setText("");
				realName.setText("");
				male.setSelected(true);
				password.setText("");
				repassword.setText("");
				email.setText("");
				birthday.setText("请选择出生日期");
				number.setText("");
				address.setText("");
			}
		}
	}

}
