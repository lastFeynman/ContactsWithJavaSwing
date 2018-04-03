package hwadee.dao.factory;

import hwadee.dao.IContactDao;
import hwadee.dao.IUserDao;
import hwadee.dao.impl.ContactDaoImpl;
import hwadee.dao.impl.UserDaoImpl;

public class DaoFactory {
	private static DaoFactory daoFactory = null;
	
	private DaoFactory() {}
	
	public static DaoFactory getDaoFactory() {
		if(daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	
	public IUserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	public IContactDao getContactDao() {
		return new ContactDaoImpl();
	}
}
