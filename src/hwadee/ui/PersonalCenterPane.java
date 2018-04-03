package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.service.ISignUpService;
import hwadee.service.impl.SignUpServiceImpl;

public class PersonalCenterPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nickNameLabel, realNameLabel, genderLabel, passwordLabel, repasswordLabel, oldPasswordLabel;
	private JLabel emailLabel, birthdayLabel, numberLabel, addressLabel, hintLabel;
	private JTextField nickName,realName,email,number;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private JPasswordField password,repassword,oldPassword;
	private DateObserver birthday;
	private JTextArea address;
	private JButton edit,confirm,reset;
	
	private JLabel nickNameHint,realNameHint,passwordHint,emailHint,numberHint,addressHint;
	
	public PersonalCenterPane() {
		this.setLayout(null);
		
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
		this.add(nickName);
		
		realNameHint = new JLabel("中文名不超过10个字，英文名不超过50个字母");
		realNameHint.setBounds(550, 30, 300, 25);
		realNameHint.setForeground(Color.BLUE);
		this.add(realNameHint);
		
		realNameLabel = new JLabel("真实姓名");
		realNameLabel.setBounds(500,50,80,25);
		this.add(realNameLabel);
		
		realName = new JTextField();
		realName.setBounds(580,50,220,25);
		this.add(realName);
		
		genderLabel = new JLabel("*性别");
		genderLabel.setBounds(50, 100, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("男");
		male.setBounds(130, 100, 110, 25);
		this.add(male);
		
		female = new JRadioButton("女");
		female.setBounds(240, 100, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		oldPasswordLabel = new JLabel("*旧密码：");
		oldPasswordLabel.setBounds(500, 100, 80, 25);
		this.add(oldPasswordLabel);
		
		oldPassword = new JPasswordField();
		oldPassword.setBounds(580, 100, 220, 25);
		this.add(oldPassword);
		
		passwordHint = new JLabel("6-18位包含数字、大小写字母、特殊字符的串");
		passwordHint.setBounds(100, 130, 300, 25);
		passwordHint.setForeground(Color.BLUE);
		this.add(passwordHint);
		
		passwordLabel = new JLabel("*新密码");
		passwordLabel.setBounds(50, 150, 80, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 150, 220, 25);
		this.add(password);
		
		repasswordLabel = new JLabel("*重复密码");
		repasswordLabel.setBounds(500, 150, 80, 25);
		this.add(repasswordLabel);
		
		repassword = new JPasswordField();
		repassword.setBounds(580, 150, 220, 25);
		this.add(repassword);
		
		emailHint = new JLabel("abc@ab.cd格式，不超过50个字符");
		emailHint.setBounds(100, 180, 300, 25);
		emailHint.setForeground(Color.BLUE);
		this.add(emailHint);
		
		emailLabel = new JLabel("*邮箱");
		emailLabel.setBounds(50, 200, 80, 25);
		this.add(emailLabel);
		
		email = new JTextField();
		email.setBounds(130, 200, 220, 25);
		this.add(email);
		
		birthdayLabel = new JLabel("生日");
		birthdayLabel.setBounds(500, 200, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("请选择出生日期");
		birthday.setBounds(580, 200, 220, 25);
		this.add(birthday);
		
		numberHint = new JLabel("11纯数字电话或手机号码");
		numberHint.setBounds(100, 230, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*电话");
		numberLabel.setBounds(50, 250, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 250, 220, 25);
		this.add(number);
		
		addressHint = new JLabel("地址，250字以内");
		addressHint.setBounds(100, 280, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("地址");
		addressLabel.setBounds(50, 300, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 300, 500, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		this.add(address);
		
		edit = new JButton("编辑");
		edit.setBounds(50, 370, 120, 25);
		edit.addActionListener(this);
		this.add(edit);
		
		confirm = new JButton("确认");
		confirm.setBounds(300, 370, 120, 25);
		confirm.addActionListener(this);
		this.add(confirm);
		
		reset = new JButton("重置");
		reset.setBounds(530, 370, 120, 25);
		reset.addActionListener(this);
		this.add(reset);
		
		setAllNotEnable();
		getDefaultValue();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="编辑") {
			setAllEnable();
		}else if(e.getActionCommand()=="确认") {
			if(nickName.getText().length() != 0 && oldPassword.getPassword().length != 0 && password.getPassword().length != 0 && repassword.getPassword().length != 0 && email.getText().length() != 0 && number.getText().length() != 0) {
				ISignUpService signUpService = new SignUpServiceImpl();
				if(signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText(),UIStatus.current_user.getUserId()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectOldPassword(oldPassword.getPassword(), UIStatus.current_user)
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText(),UIStatus.current_user.getUserId()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText(),UIStatus.current_user.getUserId())
						&& signUpService.isValidAddress(address.getText())) {
					if(!signUpService.editUser(UIStatus.current_user,
							nickName.getText(), 
							realName.getText(), 
							male.isSelected()?"男":"女", 
							password.getPassword(), 
							email.getText(), 
							birthday.getText(), 
							number.getText(), 
							address.getText(),
							UIStatus.current_user.getRegisterDate())) {
						JOptionPane.showMessageDialog(null, "更改失败", "消息提示", JOptionPane.WARNING_MESSAGE);
						setAllNotEnable();
						getDefaultValue();
					}
					else {
						JOptionPane.showMessageDialog(null, "更改成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						UIStatus.current_user = signUpService.getUser(UIStatus.current_user.getUserId());
						setAllNotEnable();
						getDefaultValue();
					}
				}else if(!signUpService.isValidNickName(nickName.getText())){
					JOptionPane.showMessageDialog(null, "用户名无效，注意用户名字符与长度限制", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNickName(nickName.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "用户名已存在", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidRealName(realName.getText())) {
					JOptionPane.showMessageDialog(null, "姓名无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectOldPassword(oldPassword.getPassword(), UIStatus.current_user)) {
					JOptionPane.showMessageDialog(null, "旧密码错误", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectPassword(password.getPassword())) {
					JOptionPane.showMessageDialog(null, "密码无效，注意密码的字符限制与长度限制", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一样", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "无效的邮箱地址", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedEmail(email.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "邮箱地址已被注册", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "生日无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "号码无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNumber(number.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "号码已被注册", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidAddress(address.getText())){
					JOptionPane.showMessageDialog(null, "无效的地址", "消息提示", JOptionPane.WARNING_MESSAGE);
				}
			}else if(nickName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(oldPassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请输入旧密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请输入新密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(repassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "请确认新密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else if(email.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入邮箱", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "请输入号码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			getDefaultValue();
		}
	}
	
	public void setAllEnable() {
		nickName.setEditable(true);
		realName.setEditable(true);
		male.setEnabled(true);
		female.setEnabled(true);
		oldPassword.setEditable(true);
		password.setEditable(true);
		repassword.setEditable(true);
		email.setEditable(true);
		birthday.setEnabled(true);
		number.setEditable(true);
		address.setEditable(true);
		edit.setEnabled(false);
		confirm.setEnabled(true);
		reset.setEnabled(true);
	}

	public void setAllNotEnable() {
		nickName.setEditable(false);
		realName.setEditable(false);
		male.setEnabled(false);
		female.setEnabled(false);
		oldPassword.setEditable(false);
		password.setEditable(false);
		repassword.setEditable(false);
		email.setEditable(false);
		birthday.setEnabled(false);
		number.setEditable(false);
		address.setEditable(false);
		edit.setEnabled(true);
		confirm.setEnabled(false);
		reset.setEnabled(false);
	}
	
	public void getDefaultValue() {
		nickName.setText(UIStatus.current_user.getUserNickName());
		realName.setText(UIStatus.current_user.getUserRealName());
		if(UIStatus.current_user.getUserGender().equals("男"))
			male.setSelected(true);
		else
			female.setSelected(true);
		email.setText(UIStatus.current_user.getUserEmail());
		if(UIStatus.current_user.getUserBirthday() != null)
			birthday.setText(UIStatus.current_user.getUserBirthday().toString());
		number.setText(UIStatus.current_user.getUserNumber());
		address.setText(UIStatus.current_user.getUserAddress());
		oldPassword.setText("");
		password.setText("");
		repassword.setText("");
	}
}
