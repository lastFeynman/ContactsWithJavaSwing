package hwadee.service.impl;

import hwadee.dao.IUserDao;
import hwadee.dao.factory.DaoFactory;
import hwadee.entity.User;
import hwadee.service.ILoginService;
import hwadee.util.EncryptUtil;

public class LoginServiceImpl implements ILoginService{
	@Override
	public User verifyUser(String userNickName, char[] password) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IUserDao userDao = daoFactory.getUserDao();
		User user = userDao.findUserByUserNickName(userNickName);

		if(user != null && EncryptUtil.verifyPassword(new String(password), user.getUserNickName(), user.getUserKey())) {
			return user;
		}else {
			return null;
		}
	}

}
