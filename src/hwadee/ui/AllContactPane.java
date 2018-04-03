package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hwadee.entity.Contact;
import hwadee.service.IUserIndexService;
import hwadee.service.impl.UserIndexServiceImpl;
import hwadee.util.UIUtil;

public class AllContactPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TABLE_ROW_NUM = 12;
	
	private JLabel[] tableColName;
	private JButton[][] deleteButtons=null,editbuttons=null;
	private JLabel userNameLabel,usageLabel = null;
	private JButton order;
	private JButton addButton,exportButton,importButton,clearAll;
	private JTable contactTable;
	private JLabel currentPageLabel;
	private JTextField pageJump;
	private JButton prePage,nextPage,jumpTo;
	private JFileChooser importChooser;
	
	private int currentPage = 1;
	private Object[][] objects;
	private int maxPage;
	private String[] cols = {"联系人姓名","性别","生日","号码","QQ","地址","添加日期"};
	
	private JFrame frame;
	
	public AllContactPane(JFrame frame) {
		this();
		this.frame = frame;
	}
	
	public AllContactPane() {
		getContacts();
			
		this.setLayout(null);
		
		userNameLabel = new JLabel("用户名："+UIStatus.current_user.getUserNickName());
		userNameLabel.setBounds(0,0,100,25);
		this.add(userNameLabel);
		
		order = new JButton("按号码排序");
		order.setBounds(100, 0, 100, 25);
		order.addActionListener(this);
		this.add(order);
		
		clearAll = new JButton("清空");
		clearAll.setBounds(800, 0, 75, 25);
		clearAll.addActionListener(this);
		this.add(clearAll);
		
		addButton = new JButton("添加");
		addButton.setBounds(400, 0, 75, 25);
		addButton.addActionListener(this);
		this.add(addButton);
		
		exportButton = new JButton("导出联系人");
		exportButton.setBounds(500, 0, 100, 25);
		exportButton.addActionListener(this);
		this.add(exportButton);
		
		importButton = new JButton("导入联系人");
		importButton.setBounds(650, 0, 100, 25);
		importButton.addActionListener(this);
		this.add(importButton);
		
		tableColName = new JLabel[7];
		for(int i=0;i<7;i++) {
			tableColName[i] = new JLabel(cols[i]);
			tableColName[i].setBounds(0+i*100, 30, 100, 25);
			this.add(tableColName[i]);
		}
		
		Object[][] showObjs;
		if(objects.length-(currentPage-1)*TABLE_ROW_NUM >= TABLE_ROW_NUM)
			showObjs = new Object[TABLE_ROW_NUM][9];
		else
			showObjs = new Object[objects.length-(currentPage-1)*TABLE_ROW_NUM][9];
		
		for(int i=0;i<showObjs.length;i++) {
			showObjs[i] = objects[(currentPage-1)*TABLE_ROW_NUM+i];
		}
			
		contactTable = new JTable();
		contactTable.setModel(new DefaultTableModel(showObjs,cols){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				return false;
			}
			
		});
		contactTable.setRowHeight(30);
		contactTable.setBounds(0, 50, 700, 360);
		this.add(contactTable);
		if(objects.length!=0) {
			for(int i=0;i<deleteButtons[currentPage-1].length;i++) {
				deleteButtons[currentPage-1][i].setBounds(720, 50+i*30, 60, 30);
				editbuttons[currentPage-1][i].setBounds(820, 50+i*30, 60, 30);
			}
		}
		
		currentPageLabel = new JLabel("当前位于第"+currentPage+"/"+maxPage+"页");
		currentPageLabel.setBounds(0, 410, 150, 25);
		this.add(currentPageLabel);
		
		prePage = new JButton("上一页");
		prePage.setBounds(150, 410, 100, 25);
		prePage.addActionListener(this);
		this.add(prePage);
		
		nextPage = new JButton("下一页");
		nextPage.setBounds(350, 410, 100, 25);
		nextPage.addActionListener(this);
		this.add(nextPage);
		
		pageJump = new JTextField();
		pageJump.setBounds(500, 410, 50, 25);
		this.add(pageJump);
		
		jumpTo = new JButton("跳转");
		jumpTo.setBounds(600, 410, 100, 25);
		jumpTo.addActionListener(this);
		this.add(jumpTo);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "添加") {
			if(UIStatus.current_user.isAdmin() || objects.length<1000) {
				frame.dispose();
				UIUtil.run(new EditContactJFrame("添加",null), EditContactJFrame.FRAME_WIDTH, EditContactJFrame.FRAME_HEIGHT);
			}else {
				JOptionPane.showMessageDialog(null, "您的默认存储条数已达上限", "消息提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getActionCommand() == "导出联系人") {
			IUserIndexService userIndexService = new UserIndexServiceImpl();
			userIndexService.exportAllContact(UIStatus.current_user);
			JOptionPane.showMessageDialog(null, "导出成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand() == "导入联系人") {
			if(UIStatus.current_user.isAdmin() || objects.length<1000) {
				importChooser = new JFileChooser();
				importChooser.setDialogTitle("选择备份文件进行导入");
				importChooser.setApproveButtonText("确定");
				importChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				this.add(importChooser);
				if(importChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					IUserIndexService userIndexService = new UserIndexServiceImpl();
					if(userIndexService.importContact(importChooser.getSelectedFile().getPath(),objects)) {
						JOptionPane.showMessageDialog(null, "导入成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
						getContacts();
						currentPage = 1;
						getTable();
					}else {
						JOptionPane.showMessageDialog(null, "导入失败", "消息提示", JOptionPane.WARNING_MESSAGE);
					}
					importChooser.setVisible(false);
				}
			}else {
				JOptionPane.showMessageDialog(null, "您的默认存储条数已达上限", "消息提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getActionCommand() == "删除") {
			IUserIndexService userIndexService = new UserIndexServiceImpl();
			userIndexService.deleteContact(e.getSource().toString());
			getContacts();
			currentPage = 1;
			getTable();
			JOptionPane.showMessageDialog(null, "删除成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand() == "编辑") {
			frame.dispose();
			IUserIndexService userIndexService = new UserIndexServiceImpl();
			Contact contact = userIndexService.getContact(e.getSource().toString());
			UIUtil.run(new EditContactJFrame("编辑",contact), EditContactJFrame.FRAME_WIDTH, EditContactJFrame.FRAME_HEIGHT);
		}else if(e.getActionCommand() == "上一页") {
			if(currentPage<=1) {
				currentPage = 1;
			}else {
				currentPage--;
			}
			getTable();
		}else if(e.getActionCommand() == "下一页") {
			if(currentPage>=maxPage) {
				currentPage = maxPage;
			}else {
				currentPage++;
			}
			getTable();
		}else if(e.getActionCommand() == "跳转"){
			if(pageJump.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "请指定跳转页码", "消息提示", JOptionPane.WARNING_MESSAGE);
			}else {
				IUserIndexService userIndexService = new UserIndexServiceImpl();
				if(userIndexService.isValidPageNum(pageJump.getText(), maxPage)) {
					currentPage = Integer.parseInt(pageJump.getText());
					getTable();
				}else {
					JOptionPane.showMessageDialog(null, "页码不正确", "消息提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else if(e.getActionCommand() == "清空") {
			IUserIndexService userIndexService = new UserIndexServiceImpl();
			userIndexService.deleteAll(UIStatus.current_user);
			getContacts();
			currentPage = 1;
			getTable();
			JOptionPane.showMessageDialog(null, "清空成功", "消息提示", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand() == "按号码排序") {
			order.setText("按姓名排序");
			orderedByNumber();
			getTable();
		}else  if(e.getActionCommand() == "按姓名排序") {
			order.setText("按号码排序");
			orderedByName();
			getTable();
		}
	}

	public void getTable() {
		Object[][] showObjs;
		if(objects.length-(currentPage-1)*TABLE_ROW_NUM >= TABLE_ROW_NUM)
			showObjs = new Object[TABLE_ROW_NUM][9];
		else
			showObjs = new Object[objects.length-(currentPage-1)*TABLE_ROW_NUM][9];
		
		for(int i=0;i<showObjs.length;i++) {
			showObjs[i] = objects[(currentPage-1)*TABLE_ROW_NUM+i];
		}
		
		contactTable.setModel(new DefaultTableModel(showObjs,cols){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				return false;
			}
			
		});
		contactTable.setRowHeight(30);
		contactTable.setBounds(0, 50, 700, 360);
		this.add(contactTable);
		if(objects.length!=0) {
			for(int i=0;i<maxPage;i++) {
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j].setBounds(1000, 1000, 60, 30);
					editbuttons[i][j].setBounds(1000, 1000, 60, 30);
				}
			}
		
			for(int i=0;i<deleteButtons[currentPage-1].length;i++) {
				deleteButtons[currentPage-1][i].setBounds(720, 50+i*30, 60, 30);
				editbuttons[currentPage-1][i].setBounds(820, 50+i*30, 60, 30);
			}
		}
		
		currentPageLabel.setText("当前位于第"+currentPage+"/"+maxPage+"页");
	}
	
	public void getContacts() {
		if(usageLabel!=null)
			usageLabel.setVisible(false);
		if(deleteButtons!=null) {
			for(int i=0;i<maxPage;i++) {
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j].setVisible(false);
					editbuttons[i][j].setVisible(false);
				}
			}
		}
		
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.getAllContacts(UIStatus.current_user);
		
		if(!UIStatus.current_user.isAdmin()) {
			usageLabel = new JLabel("已保存条数 / 总条数："+objects.length+" / 1000");
			usageLabel.setBounds(200, 0, 500, 25);
			this.add(usageLabel);
		}
		
		if(objects.length%TABLE_ROW_NUM==0 && objects.length/TABLE_ROW_NUM!=0)
			maxPage = objects.length/TABLE_ROW_NUM;
		else
			maxPage = objects.length/TABLE_ROW_NUM+1;
		
		if(objects.length!=0){
			deleteButtons = new JButton[maxPage][];
			editbuttons = new JButton[maxPage][];
			for(int i=0;i<maxPage;i++) {
				if(objects.length%TABLE_ROW_NUM != 0 && i==maxPage-1) {
					deleteButtons[i] = new JButton[objects.length%TABLE_ROW_NUM];
					editbuttons[i] =  new JButton[objects.length%TABLE_ROW_NUM];
				}else {
					deleteButtons[i] = new JButton[TABLE_ROW_NUM];
					editbuttons[i] =  new JButton[TABLE_ROW_NUM];
				}
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j] = (JButton)objects[i*TABLE_ROW_NUM+j][7];
					deleteButtons[i][j].setBounds(1000, 1000, 60, 30);
					deleteButtons[i][j].addActionListener(this);
					this.add(deleteButtons[i][j]);
				
					editbuttons[i][j] = (JButton)objects[i*TABLE_ROW_NUM+j][8];
					editbuttons[i][j].setBounds(1000, 1000, 60, 30);
					editbuttons[i][j].addActionListener(this);
					this.add(editbuttons[i][j]);
				}
			}
		}
	}
	
	public void orderedByNumber(){
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.orderedByNumber(objects);
	}
	
	public void orderedByName(){
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.orderedByName(objects);
	}
}
