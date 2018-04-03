package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hwadee.entity.Contact;
import hwadee.service.IUserIndexService;
import hwadee.service.impl.UserIndexServiceImpl;
import hwadee.util.UIUtil;

public class SearchPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TABLE_ROW_NUM = 12;
	
	private JTextField searchContent;
	private JButton search;
	private JRadioButton byName,byNumber,byQQ;
	private ButtonGroup methodGroup;
	private JLabel[] tableColName;
	private JButton[][] deleteButtons=null,editbuttons=null;
	private JTable contactTable;
	private JLabel currentPageLabel;
	private JTextField pageJump;
	private JButton prePage,nextPage,jumpTo;
	
	private int currentPage = 1;
	private Object[][] objects;
	private int maxPage;
	private String[] cols = {"联系人姓名","性别","生日","号码","QQ","地址","添加日期"};
	
	private JFrame frame;
	
	public SearchPane(JFrame frame) {
		this();
		this.frame = frame;
	}
	
	public SearchPane() {
		getInitContacts();
		getContacts();
			
		this.setLayout(null);
		
		searchContent = new JTextField();
		searchContent.setBounds(0, 0, 400, 25);
		this.add(searchContent);
		
		search = new JButton("搜索");
		search.setBounds(420, 0, 75, 25);
		search.addActionListener(this);
		this.add(search);
		
		byName = new JRadioButton("姓名",true);
		byName.setBounds(550, 0, 100, 25);
		this.add(byName);
		
		byNumber = new JRadioButton("号码");
		byNumber.setBounds(650, 0, 100, 25);
		this.add(byNumber);
		
		byQQ = new JRadioButton("QQ");
		byQQ.setBounds(750, 0, 100, 25);
		this.add(byQQ);
		
		methodGroup = new ButtonGroup();
		methodGroup.add(byName);
		methodGroup.add(byNumber);
		methodGroup.add(byQQ);
		
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
		if(e.getActionCommand() == "删除") {
			IUserIndexService userIndexService = new UserIndexServiceImpl();
			userIndexService.deleteContact(e.getSource().toString());
			getInitContacts();
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
		}else if(e.getActionCommand() == "搜索") {
			if(byName.isSelected()) {
				searchContact("byName");
			}else if (byNumber.isSelected()) {
				searchContact("byNumber");
			}else {
				searchContact("byQQ");
			}
			getContacts();
			getTable();
		}
	}

	public void getTable() {
		Object[][] showObjs;
		if(objects.length-(currentPage-1)*TABLE_ROW_NUM >= TABLE_ROW_NUM)
			showObjs = new Object[TABLE_ROW_NUM][9];
		else {
			try {
				showObjs = new Object[objects.length-(currentPage-1)*TABLE_ROW_NUM][9];
			}catch (Exception e) {
				showObjs = new Object[0][];
			}
		}
			
		
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
	
	public void getInitContacts() {
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.getAllContacts(UIStatus.current_user);
	}
	
	public void getContacts() {
		if(deleteButtons!=null) {
			for(int i=0;i<maxPage;i++) {
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j].setVisible(false);
					editbuttons[i][j].setVisible(false);
				}
			}
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
	
	public void searchContact(String method) {
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.searchContacts(UIStatus.current_user, searchContent.getText(), method);
	}
	
}
