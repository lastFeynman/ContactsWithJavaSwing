package hwadee.service;

import java.sql.Date;

public interface IEditContactService {
	public boolean isValidContactName(String name);
	public boolean isValidBirthday(String birthday);
	public boolean isValidNumber(String number);
	public boolean isValidQQ(String qq);
	public boolean isValidAddress(String address);
	public boolean updateContact(int id,int userId,String name,String gender,String birthday,String number,String qq,String address,Date addDate);
	public boolean insertContact(int userId,String name,String gender,String birthday,String number,String qq,String address);
}
