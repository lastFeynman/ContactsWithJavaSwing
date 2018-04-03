package hwadee.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import hwadee.dao.IUserDao;
import hwadee.dao.factory.DaoFactory;
import hwadee.entity.User;
import hwadee.service.ISignUpService;
import hwadee.service.IUserManageService;
import hwadee.util.RegUtil;

public class UserManageServiceImpl implements IUserManageService{

	@Override
	public Object[][] getAllUser(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		List<User> users;
		
		users = userDao.findAll();
		
		Object[][] objects = new Object[users.size()-1][9];
		JButton delete,edit;
		if(objects.length==0)
			return objects;
		for(int i=0;i<users.size();i++) {
			if(user.getUserId() == users.get(i).getUserId())
				users.remove(i);
			objects[i][0] = users.get(i).getUserNickName();
			objects[i][1] = users.get(i).getUserRealName();
			objects[i][2] = users.get(i).getUserGender();
			objects[i][3] = users.get(i).getUserEmail();
			objects[i][4] = users.get(i).isAdmin()?"ÊÇ":"·ñ";
			if(users.get(i).getUserBirthday()!=null)
				objects[i][5] = users.get(i).getUserBirthday().toString();
			else
				objects[i][5] = users.get(i).getUserBirthday();
			objects[i][6] = users.get(i).getUserNumber();
			delete = new JButton("É¾³ý");
			delete.setName(users.get(i).getUserId()+"");
			objects[i][7] = delete;
			edit = new JButton("±à¼­");
			edit.setName(users.get(i).getUserId()+"");
			objects[i][8] = edit;
		}
		return objects;
	}

	@Override
	public void deleteAll(User user) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		List<User> users;
		
		users = userDao.findAll();
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUserId() == user.getUserId())
				continue;
			userDao.delete(users.get(i).getUserId());
		}
	}

	@Override
	public Object[][] searchUser(User user, String searchContent, String method) {
		if(searchContent.equals(""))
			return getAllUser(user);
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		List<User> users;
		User resultUser;
		
		if(method.equals("byNickName")) {
			users = new ArrayList<>();
			resultUser = userDao.findUserByUserNickName(searchContent);
			users.add(resultUser);
		}else if(method.equals("byRealName")){
			users = userDao.findUserByUserRealName(searchContent);
		}else if(method.equals("byEmail")) {
			users = new ArrayList<>();
			resultUser = userDao.findUserByUserEmail(searchContent);
			users.add(resultUser);
		}else {
			users = new ArrayList<>();
			resultUser = userDao.findUserByUserNumber(searchContent);
			users.add(resultUser);
		}
		Object[][] objects;
		if(users.size()==0 || users.get(0)!=null)
			objects = new Object[users.size()][9];
		else 
			objects = new Object[users.size()-1][9];
		JButton delete,edit;
		if(objects.length==0)
			return objects;
		for(int i=0;i<objects.length;i++) {
			if(user.getUserId() == users.get(i).getUserId())
				users.remove(i);
			objects[i][0] = users.get(i).getUserNickName();
			objects[i][1] = users.get(i).getUserRealName();
			objects[i][2] = users.get(i).getUserGender();
			objects[i][3] = users.get(i).getUserEmail();
			objects[i][4] = users.get(i).isAdmin()?"ÊÇ":"·ñ";
			if(users.get(i).getUserBirthday()!=null)
				objects[i][5] = users.get(i).getUserBirthday().toString();
			else
				objects[i][5] = users.get(i).getUserBirthday();
			objects[i][6] = users.get(i).getUserNumber();
			delete = new JButton("É¾³ý");
			delete.setName(users.get(i).getUserId()+"");
			objects[i][7] = delete;
			edit = new JButton("±à¼­");
			edit.setName(users.get(i).getUserId()+"");
			objects[i][8] = edit;
		}
		return objects;
	}

	@Override
	public boolean exportUser() {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		List<User> users;
		users = userDao.findAll();
		
		ObjectOutputStream oos = null;
		File bakFile = new File("backUpUser.bak");
		
		try {
			if(!bakFile.exists())
				bakFile.createNewFile();
			
			oos = new ObjectOutputStream(new FileOutputStream(bakFile));
			oos.writeObject(users);
			
		} catch (IOException e) {
			return false;
		}finally {
			if(oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean importUser(String path) {
		List<User> users;
		
		File bakFile = new File(path);
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(bakFile));
			users = (List<User>)ois.readObject();
		} catch (Exception e) {
			return false;
		}finally {
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		ISignUpService signUpService = new SignUpServiceImpl();
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		for(int i=0;i<users.size();i++) {
			if(!signUpService.isRepeatedNickName(users.get(i).getUserNickName()) && !signUpService.isRepeatedEmail(users.get(i).getUserEmail()) && !signUpService.isRepeatedNumber(users.get(i).getUserNumber())) {
				userDao.insert(users.get(i));
			}
		}
		
		return true;
	}

	@Override
	public void deleteUser(String userStr) {
		String reg = "^javax\\.swing\\.JButton\\[([0-9]*?),";
		int userId = Integer.parseInt(RegUtil.getGroup(reg, userStr, 1));
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		userDao.delete(userId);
	}

	@Override
	public User getUser(String userStr) {
		String reg = "^javax\\.swing\\.JButton\\[([0-9]*?),";
		int userId = Integer.parseInt(RegUtil.getGroup(reg, userStr, 1));
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		return userDao.findUserById(userId);
	}

}
