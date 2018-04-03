package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.entity.Contact;
import hwadee.service.IEditContactService;
import hwadee.service.impl.EditContactServiceImpl;
import hwadee.util.UIUtil;

public class EditContactJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 500;
	
	private String command;
	private Contact editedContact = null;
	
	private JLabel hintLabel;
	private JLabel nameHint,numberHint,qqHint,addressHint;
	private JLabel nameLabel,genderLabel,birthdayLabel,numberLabel,qqLabel,addressLabel;
	private JTextField name,number,qq;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private DateObserver birthday;
	private JTextArea address;
	private JButton add,reset;
	
	
	public EditContactJFrame(String command,Contact editedContact) {
		this.command = command;
		this.editedContact = editedContact;
		
		this.setLayout(null);
		this.setBounds(500, 300, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle(command+"联系人");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		hintLabel = new JLabel("带\" * \"的项为必填项");
		hintLabel.setBounds(150, 10, 200, 25);
		hintLabel.setForeground(Color.RED);
		this.add(hintLabel);
		
		nameHint = new JLabel("中文名不超过10个字，英文名不超过50个字母");
		nameHint.setBounds(100, 30, 300, 25);
		nameHint.setForeground(Color.BLUE);
		this.add(nameHint);
		
		nameLabel = new JLabel("*姓名");
		nameLabel.setBounds(50,50,80,25);
		this.add(nameLabel);
		
		name = new JTextField();
		name.setBounds(130,50,220,25);
		this.add(name);
		
		genderLabel = new JLabel("*性别");
		genderLabel.setBounds(50, 100, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("男",true);
		male.setBounds(130, 100, 110, 25);
		this.add(male);
		
		female = new JRadioButton("女");
		female.setBounds(240, 100, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		birthdayLabel = new JLabel("生日");
		birthdayLabel.setBounds(50, 150, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("请选择出生日期");
		birthday.setBounds(130, 150, 220, 25);
		this.add(birthday);
		
		numberHint = new JLabel("11纯数字电话或手机号码");
		numberHint.setBounds(100, 180, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*电话");
		numberLabel.setBounds(50, 200, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 200, 220, 25);
		this.add(number);
		
		qqHint = new JLabel("5-12位纯数字");
		qqHint.setBounds(100, 230, 300, 25);
		qqHint.setForeground(Color.BLUE);
		this.add(qqHint);
		
		qqLabel = new JLabel("QQ");
		qqLabel.setBounds(50, 250, 80, 25);
		this.add(qqLabel);
		
		qq = new JTextField();
		qq.setBounds(130, 250, 220, 25);
		this.add(qq);
		
		addressHint = new JLabel("地址，250字以内");
		addressHint.setBounds(100, 280, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("地址");
		addressLabel.setBounds(50, 300, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 300, 220, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		this.add(address);
		
		add = new JButton(this.command);
		add.setBounds(50, 400, 120, 25);
		add.addActionListener(this);
		this.add(add);
		
		reset = new JButton("重置");
		reset.setBounds(230, 400, 120, 25);
		reset.addActionListener(this);
		this.add(reset);
		
		if(this.command.equals("编辑")) {
			name.setText(this.editedContact.getContactName());
			if(this.editedContact.getContactGender().equals("女"))
				female.setSelected(true);
			if(this.editedContact.getContactBirthday()!=null)
				birthday.setText(this.editedContact.getContactBirthday().toString());
			number.setText(this.editedContact.getContactNumber());
			qq.setText(this.editedContact.getContactQQ());
			address.setText(this.editedContact.getContactAddress());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "添加" || e.getActionCommand() == "编辑") {
			if(name.getText().length() != 0 && number.getText().length() != 0) {
				IEditContactService editContactService = new EditContactServiceImpl();
				if(editContactService.isValidContactName(name.getText()) 
						&& editContactService.isValidBirthday(birthday.getText()) 
						&& editContactService.isValidNumber(number.getText()) 
						&& editContactService.isValidQQ(qq.getText()) 
						&& editContactService.isValidAddress(address.getText())) {
					if(this.command.equals("编辑")) {
						if(editContactService.updateContact(this.editedContact.getContactId(),this.editedContact.getUserId(),name.getText(), male.isSelected()?"男":"女", birthday.getText(), number.getText(), qq.getText(), address.getText(),this.editedContact.getContactAddDate())) {
							JOptionPane.showMessageDialog(null, "编辑成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							if(UIStatus.current_user.isAdmin()) {
								UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
							}else {
								UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
							}
						}else {
							JOptionPane.showMessageDialog(null, "编辑失败", "消息提示", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						if(editContactService.insertContact(UIStatus.current_user.getUserId(),name.getText(), male.isSelected()?"男":"女", birthday.getText(), number.getText(), qq.getText(), address.getText())) {
							JOptionPane.showMessageDialog(null, "添加成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							if(UIStatus.current_user.isAdmin()) {
								UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
							}else {
								UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
							}
						}else {
							JOptionPane.showMessageDialog(null, "添加失败", "消息提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}else if(!editContactService.isValidContactName(name.getText())){
					JOptionPane.showMessageDialog(null, "联系人姓名无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "生日无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "电话号码无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidQQ(qq.getText())) {
					JOptionPane.showMessageDialog(null, "qq无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "地址无效", "消息提示", JOptionPane.WARNING_MESSAGE);
				}
			}else if(name.getText().length()==0){
				JOptionPane.showMessageDialog(null, "请输入联系人姓名", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "请输入号码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			if(this.command.equals("编辑")) {
				name.setText(this.editedContact.getContactName());
				if(this.editedContact.getContactGender().equals("女"))
					female.setSelected(true);
				if(this.editedContact.getContactBirthday()!=null)
					birthday.setText(this.editedContact.getContactBirthday().toString());
				else
					birthday.setText("请选择出生日期");
				number.setText(this.editedContact.getContactNumber());
				qq.setText(this.editedContact.getContactQQ());
				address.setText(this.editedContact.getContactAddress());
			}else {
				name.setText("");
				male.setSelected(true);
				birthday.setText("请选择出生日期");
				number.setText("");
				qq.setText("");
				address.setText("");
			}
		}
	}

}
