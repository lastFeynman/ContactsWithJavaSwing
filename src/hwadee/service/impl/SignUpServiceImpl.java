package hwadee.service.impl;

import java.util.Calendar;
import java.util.Date;

import hwadee.dao.IUserDao;
import hwadee.dao.factory.DaoFactory;
import hwadee.entity.User;
import hwadee.service.ISignUpService;
import hwadee.util.EncryptUtil;
import hwadee.util.RegUtil;

public class SignUpServiceImpl implements ISignUpService{

	@Override
	public boolean isValidNickName(String nickName) {
		String reg = "^[0-9a-zA-Z_]{1,50}$";
		return RegUtil.isFind(reg, nickName);
	}

	@Override
	public boolean isRepeatedNickName(String nickName) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		if(userDao.findUserByUserNickName(nickName) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isRepeatedNickName(String nickName, int userId) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		User user = userDao.findUserByUserNickName(nickName);
		
		if(user != null && user.getUserId() != userId)
			return true;
		else
			return false;
	}

	@Override
	public boolean isValidRealName(String realName) {
		String reg = "^[\\u4e00-\\u9fa5]{1,10}$|^[A-Za-z ]{0,50}$";
		return RegUtil.isFind(reg, realName);
	}

	@Override
	public boolean isCorrectOldPassword(char[] oldPassword, User user) {
		return EncryptUtil.verifyPassword(new String(oldPassword), user.getUserNickName(), user.getUserKey());
	}

	@Override
	public boolean isCorrectPassword(char[] password) {
		String reg = "^[0-9a-zA-Z@#$%^&*_]{6,18}$";
		return RegUtil.isFind(reg, new String(password));
	}

	@Override
	public boolean isSameRepassword(char[] password, char[] repassword) {
		return new String(password).equals(new String(repassword));
	}

	@Override
	public boolean isValidEmail(String email) {
		String reg = "^[a-zA-Z0-9-_]{1,30}@[a-zA-Z0-9-_]*\\.[a-zA-Z]{2,4}$";
		return RegUtil.isFind(reg, email);
	}

	@Override
	public boolean isRepeatedEmail(String email) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		if(userDao.findUserByUserEmail(email) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isRepeatedEmail(String email, int userId) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		User user = userDao.findUserByUserEmail(email);
		
		if(user != null && user.getUserId() != userId)
			return true;
		else
			return false;
	}

	@Override
	public boolean isValidBirthday(String birthday) {
		if(birthday.equals("请选择出生日期"))
			return true;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int nowYear = calendar.get(Calendar.YEAR);
		int birthYear = Integer.parseInt(birthday.split("-")[0]);
		
		if(nowYear-birthYear <= 120)
			return true;
		else
			return false;
	}

	@Override
	public boolean isValidNumber(String number) {
		String reg = "^1[0-9]{10}|0[0-9]{10}$";
		return RegUtil.isFind(reg, number);
	}

	@Override
	public boolean isRepeatedNumber(String number) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		if(userDao.findUserByUserNumber(number) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isRepeatedNumber(String number, int userId) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		User user = userDao.findUserByUserNumber(number);
		
		if(user != null && user.getUserId() != userId)
			return true;
		else
			return false;
	}

	@Override
	public boolean isValidAddress(String address) {
		if(address.length() <= 250)
			return true;
		else
			return false;
	}

	@Override
	public User getUser(int userId) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		return userDao.findUserById(userId);
	}

	@Override
	public boolean insertUser(String nickName, String realName, String gender, char[] password, String email,
			String birthday, String number, String address) {
		User user = new User();
		user.setUserNickName(nickName);
		user.setUserRealName(realName);
		user.setUserGender(gender);
		user.setUserKey(EncryptUtil.encrypt(new String(password), nickName));
		user.setUserEmail(email);
		user.setAdmin(false);
		if(!birthday.equals("请选择出生日期"))
			user.setUserBirthday(java.sql.Date.valueOf(birthday));
		user.setUserNumber(number);
		user.setUserAddress(address);
		user.setRegisterDate(new java.sql.Date(new Date().getTime()));
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		if(userDao.insert(user))
			return true;
		else
			return false;
	}

	@Override
	public boolean editUser(User currentUser, String nickName, String realName, String gender, char[] password, String email,
			String birthday, String number, String address, java.sql.Date registerDate) {
		User user = new User();
		user.setUserNickName(nickName);
		user.setUserRealName(realName);
		user.setUserGender(gender);
		user.setUserKey(EncryptUtil.encrypt(new String(password), nickName));
		user.setUserEmail(email);
		user.setAdmin(currentUser.isAdmin());
		if(!birthday.equals("请选择出生日期"))
			user.setUserBirthday(java.sql.Date.valueOf(birthday));
		user.setUserNumber(number);
		user.setUserAddress(address);
		user.setRegisterDate(registerDate);
		
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		
		return userDao.update(currentUser.getUserId(), user);
	}

}
