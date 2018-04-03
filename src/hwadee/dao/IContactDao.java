package hwadee.dao;

import java.util.List;

import hwadee.entity.Contact;

public interface IContactDao {
	//插入联系人
	public boolean insert(Contact contact);
	//删除联系人
	public boolean delete(int contactId);
	//修改联系人
	public boolean update(int contactId,Contact contact);
	//通过联系人id查找联系人
	public Contact findContactById(int contactId);
	//通过用户id查找联系人
	public List<Contact> findContactByUserId(int userId);
	//通过联系人姓名查找联系人
	public List<Contact> findContactByContactName(String contactName);
	//通过联系人号码查找联系人
	public List<Contact> findContactByContactNumber(String contactNumber);
	//通过联系人qq查找联系人
	public List<Contact> findContactByContactQQ(String contactQQ);
	//通过联系人姓名和用户Id查找联系人
	public List<Contact> findContactByContactName(String contactName,int userId);
	//通过联系人号码和用户Id查找联系人
	public List<Contact> findContactByContactNumber(String contactNumber,int userId);
	//通过联系人qq和用户Id查找联系人
	public List<Contact> findContactByContactQQ(String contactQQ,int userId);
	//查找所有联系人
	public List<Contact> findAll();
}
