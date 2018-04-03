package hwadee.service.impl;

import java.sql.Date;

import hwadee.dao.IContactDao;
import hwadee.dao.factory.DaoFactory;
import hwadee.entity.Contact;
import hwadee.service.IEditContactService;
import hwadee.service.ISignUpService;
import hwadee.util.RegUtil;

public class EditContactServiceImpl implements IEditContactService{

	@Override
	public boolean isValidContactName(String name) {
		ISignUpService signUpService = new SignUpServiceImpl();
		return signUpService.isValidRealName(name);
	}

	@Override
	public boolean isValidBirthday(String birthday) {
		ISignUpService signUpService = new SignUpServiceImpl();
		return signUpService.isValidBirthday(birthday);
	}

	@Override
	public boolean isValidNumber(String number) {
		ISignUpService signUpService = new SignUpServiceImpl();
		return signUpService.isValidNumber(number);
	}

	@Override
	public boolean isValidQQ(String qq) {
		if(qq.length() == 0)
			return true;
		String reg = "^[0-9]{5,12}$";
		return RegUtil.isFind(reg, qq);
	}

	@Override
	public boolean isValidAddress(String address) {
		ISignUpService signUpService = new SignUpServiceImpl();
		return signUpService.isValidAddress(address);
	}

	@Override
	public boolean updateContact(int id,int userId,String name, String gender, String birthday, String number, String qq,
			String address,Date addDate) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactName(name);
		contact.setContactGender(gender);
		if(!birthday.equals("请选择出生日期"))
			contact.setContactBirthday(Date.valueOf(birthday));
		contact.setContactNumber(number);
		contact.setContactQQ(qq);
		contact.setContactAddress(address);
		contact.setContactAddDate(addDate);
		
		return contactDao.update(id, contact);
	}

	@Override
	public boolean insertContact(int userId,String name, String gender, String birthday, String number, String qq,
			String address) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		IContactDao contactDao = daoFactory.getContactDao();
		
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactName(name);
		contact.setContactGender(gender);
		if(!birthday.equals("请选择出生日期"))
			contact.setContactBirthday(Date.valueOf(birthday));
		contact.setContactNumber(number);
		contact.setContactQQ(qq);
		contact.setContactAddress(address);
		contact.setContactAddDate(new Date(new java.util.Date().getTime()));
		
		return contactDao.insert(contact);
	}

}
