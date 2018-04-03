package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hwadee.service.IUserIndexService;
import hwadee.service.impl.UserIndexServiceImpl;

public class BirthDayHintPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TABLE_ROW_NUM = 12;
	
	private JLabel countLabel;
	private JLabel[] tableColName;
	private JTable contactTable;
	private JLabel currentPageLabel;
	private JTextField pageJump;
	private JButton prePage,nextPage,jumpTo;
	
	private int currentPage = 1;
	private Object[][] objects;
	private int maxPage;
	private String[] cols = {"联系人姓名","性别","生日","号码","QQ","地址","添加日期"};
	
	public BirthDayHintPane() {
		setLayout(null);
		
		getContacts();
		
		countLabel = new JLabel("共有"+objects.length+"人今天生日");
		countLabel.setBounds(0,0,200,25);
		this.add(countLabel);
		
		tableColName = new JLabel[7];
		for(int i=0;i<7;i++) {
			tableColName[i] = new JLabel(cols[i]);
			tableColName[i].setBounds(0+i*128, 30, 100, 25);
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
		contactTable.setBounds(0, 50, 900, 360);
		this.add(contactTable);
		
		currentPageLabel = new JLabel("当前位于第"+currentPage+"/"+maxPage+"页");
		currentPageLabel.setBounds(0, 410, 100, 25);
		this.add(currentPageLabel);
		
		prePage = new JButton("上一页");
		prePage.setBounds(100, 410, 100, 25);
		prePage.addActionListener(this);
		this.add(prePage);
		
		nextPage = new JButton("下一页");
		nextPage.setBounds(300, 410, 100, 25);
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
		if(e.getActionCommand() == "上一页") {
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
		}else{
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
		contactTable.setBounds(0, 50, 900, 360);
		this.add(contactTable);
		
		currentPageLabel.setText("当前位于第"+currentPage+"/"+maxPage+"页");
	}
	
	public void getContacts() {
		IUserIndexService userIndexService = new UserIndexServiceImpl();
		objects = userIndexService.getBirthdayContacts(UIStatus.current_user);
		
		if(objects.length%TABLE_ROW_NUM==0 && objects.length/TABLE_ROW_NUM!=0)
			maxPage = objects.length/TABLE_ROW_NUM;
		else
			maxPage = objects.length/TABLE_ROW_NUM+1;
		}
}
