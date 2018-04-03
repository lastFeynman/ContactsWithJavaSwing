package hwadee.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;

import hwadee.dao.IContactDao;
import hwadee.dao.factory.DaoFactory;
import hwadee.entity.Contact;
import hwadee.entity.User;
import hwadee.service.IUserIndexService;
import hwadee.util.RegUtil;

public class UserIndexServiceImpl implements IUserIndexService{

	@Override
	public Object[][] getAllContacts(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		List<Contact> contacts;
		if(!user.isAdmin()) {
			contacts = contactDao.findContactByUserId(user.getUserId());
		}else {
			contacts = contactDao.findAll();
		}
		Object[][] objects = new Object[contacts.size()][9];
		JButton delete,edit;
		for(int i=0;i<contacts.size();i++) {
			objects[i][0] = contacts.get(i).getContactName();
			objects[i][1] = contacts.get(i).getContactGender();
			if(contacts.get(i).getContactBirthday()!=null)
				objects[i][2] = contacts.get(i).getContactBirthday().toString();
			else
				objects[i][2] = contacts.get(i).getContactBirthday();
			objects[i][3] = contacts.get(i).getContactNumber();
			objects[i][4] = contacts.get(i).getContactQQ();
			objects[i][5] = contacts.get(i).getContactAddress();
			if(contacts.get(i).getContactAddDate()!=null)
				objects[i][6] = contacts.get(i).getContactAddDate().toString();
			else
				objects[i][6] = contacts.get(i).getContactAddDate();
			delete = new JButton("É¾³ý");
			delete.setName(contacts.get(i).getContactId()+"");
			objects[i][7] = delete;
			edit = new JButton("±à¼­");
			edit.setName(contacts.get(i).getContactId()+"");
			objects[i][8] = edit;
		}
		return objects;
	}

	@Override
	public boolean isValidPageNum(String page, int maxPage) {
		String reg = "^[0-9]{1,10}$";
		if(RegUtil.isFind(reg, page) && Integer.parseInt(page)>0 && Integer.parseInt(page)<=maxPage) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void exportAllContact(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		List<Contact> contacts;
		if(!user.isAdmin()) {
			contacts = contactDao.findContactByUserId(user.getUserId());
		}else {
			contacts = contactDao.findAll();
		}
		
		File backFile = new File("./backUpContact.bak");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(backFile));
			
			oos.writeObject(contacts);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null)
					oos.close();
			}catch (Exception e) {}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean importContact(String filePath,Object[][] objects) {
		ObjectInputStream ois = null;
		List<Contact> contacts;
		File inFile = new File(filePath);
		
		try {
			ois = new ObjectInputStream(new FileInputStream(inFile));
			contacts = (List<Contact>)ois.readObject();
		} catch (Exception e) {
			return false;
		}finally {
			try {
				if(ois!=null) {
					ois.close();
				}
			} catch (Exception e2) {}
		}

		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		
		for(int i=objects.length;i<contacts.size()+objects.length && i<1000;i++) {
			contactDao.insert(contacts.get(i-objects.length));
		}
		return true;
	}

	@Override
	public void deleteContact(String conIdStr) {
		String reg = "^javax\\.swing\\.JButton\\[([0-9]*?),";
		int contactId = Integer.parseInt(RegUtil.getGroup(reg, conIdStr, 1));
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		
		contactDao.delete(contactId);
	}

	@Override
	public void deleteAll(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		List<Contact> contacts;
		if(!user.isAdmin()) {
			contacts = contactDao.findContactByUserId(user.getUserId());
		}else {
			contacts = contactDao.findAll();
		}
		
		for(int i=0;i<contacts.size();i++) {
			contactDao.delete(contacts.get(i).getContactId());
		}
	}

	@Override
	public Contact getContact(String conIdStr) {
		String reg = "^javax\\.swing\\.JButton\\[([0-9]*?),";
		int contactId = Integer.parseInt(RegUtil.getGroup(reg, conIdStr, 1));
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		
		return contactDao.findContactById(contactId);
	}

	@Override
	public Object[][] getBirthdayContacts(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		List<Contact> contacts = contactDao.findContactByUserId(user.getUserId());
		
		Date todayDate = new Date(new java.util.Date().getTime());
		String todayStr[] = todayDate.toString().split("-");
		int month = Integer.parseInt(todayStr[1]),day = Integer.parseInt(todayStr[2]);
		
		String[] birth;
		for(int i=0;i<contacts.size();) {
			if(contacts.get(i).getContactBirthday() != null && !contacts.get(i).getContactBirthday().toString().equals("")) {
				birth = contacts.get(i).getContactBirthday().toString().split("-");
				if(Integer.parseInt(birth[1]) == month && Integer.parseInt(birth[2]) == day)
					i++;
				else
					contacts.remove(i);
			}else {
				contacts.remove(i);
			}
		}
		
		Object[][] objects = new Object[contacts.size()][9];
		JButton delete,edit;
		for(int i=0;i<contacts.size();i++) {
			objects[i][0] = contacts.get(i).getContactName();
			objects[i][1] = contacts.get(i).getContactGender();
			if(contacts.get(i).getContactBirthday()!=null)
				objects[i][2] = contacts.get(i).getContactBirthday().toString();
			else
				objects[i][2] = contacts.get(i).getContactBirthday();
			objects[i][3] = contacts.get(i).getContactNumber();
			objects[i][4] = contacts.get(i).getContactQQ();
			objects[i][5] = contacts.get(i).getContactAddress();
			if(contacts.get(i).getContactAddDate()!=null)
				objects[i][6] = contacts.get(i).getContactAddDate().toString();
			else
				objects[i][6] = contacts.get(i).getContactAddDate();
			delete = new JButton("É¾³ý");
			delete.setName(contacts.get(i).getContactId()+"");
			objects[i][7] = delete;
			edit = new JButton("±à¼­");
			edit.setName(contacts.get(i).getContactId()+"");
			objects[i][8] = edit;
		}
		return objects;
	}

	@Override
	public Object[][] searchContacts(User user, String searchContent, String method) {
		if(searchContent.equals(""))
			return getAllContacts(user);
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		List<Contact> contacts;
		if(user.isAdmin()) {
			if(method.equals("byName")) {
				contacts = contactDao.findContactByContactName(searchContent);
			}else if(method.equals("byNumber")) {
				contacts = contactDao.findContactByContactNumber(searchContent);
			}else {
				contacts = contactDao.findContactByContactQQ(searchContent);
			}
		}else {
			if(method.equals("byName")) {
				contacts = contactDao.findContactByContactName(searchContent,user.getUserId());
			}else if(method.equals("byNumber")) {
				contacts = contactDao.findContactByContactNumber(searchContent,user.getUserId());
			}else {
				contacts = contactDao.findContactByContactQQ(searchContent,user.getUserId());
			}
		}
		Object[][] objects = new Object[contacts.size()][9];
		JButton delete,edit;
		for(int i=0;i<contacts.size();i++) {
			objects[i][0] = contacts.get(i).getContactName();
			objects[i][1] = contacts.get(i).getContactGender();
			if(contacts.get(i).getContactBirthday()!=null)
				objects[i][2] = contacts.get(i).getContactBirthday().toString();
			else
				objects[i][2] = contacts.get(i).getContactBirthday();
			objects[i][3] = contacts.get(i).getContactNumber();
			objects[i][4] = contacts.get(i).getContactQQ();
			objects[i][5] = contacts.get(i).getContactAddress();
			if(contacts.get(i).getContactAddDate()!=null)
				objects[i][6] = contacts.get(i).getContactAddDate().toString();
			else
				objects[i][6] = contacts.get(i).getContactAddDate();
			delete = new JButton("É¾³ý");
			delete.setName(contacts.get(i).getContactId()+"");
			objects[i][7] = delete;
			edit = new JButton("±à¼­");
			edit.setName(contacts.get(i).getContactId()+"");
			objects[i][8] = edit;
		}
		return objects;
	}

	@Override
	public Object[][] orderedByNumber(Object[][] objects) {
		Object[] t;
		for(int i=0;i<objects.length-1;i++) {
			for(int j=0;j<objects.length-1-i;j++) {
				if(((String)objects[j][3]).compareTo((String)objects[j+1][3])>0) {
					t = objects[j];
					objects[j] = objects[j+1];
					objects[j+1] = t;
				}		
			}
		}
		return objects;
	}

	@Override
	public Object[][] orderedByName(Object[][] objects) {
		Object[] t;
		for(int i=0;i<objects.length-1;i++) {
			for(int j=0;j<objects.length-1-i;j++) {
				if(((String)objects[j][0]).compareTo((String)objects[j+1][0])>0) {
					t = objects[j];
					objects[j] = objects[j+1];
					objects[j+1] = t;
				}		
			}
		}
		return objects;
	}

}
