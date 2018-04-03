package hwadee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hwadee.dao.IContactDao;
import hwadee.entity.Contact;
import hwadee.util.DBUtil;

public class ContactDaoImpl implements IContactDao{

	@Override
	public boolean insert(Contact contact) {
		String sql = "insert into "
				+ "contact(userId,contactName,contactGender,contactBirthday,contactNumber,"
				+ "contactQQ,contactAddress,contactAddDate) values (?,?,?,?,?,?,?,?)";
		
		return DBUtil.executeUpdate(sql, contact.getUserId(),contact.getContactName(),contact.getContactGender(),
				contact.getContactBirthday(),contact.getContactNumber(),contact.getContactQQ(),
				contact.getContactAddress(),contact.getContactAddDate());
	}

	@Override
	public boolean delete(int contactId) {
		String sql = "delete from contact where contactId = ?";
		
		return DBUtil.executeUpdate(sql, contactId);
	}

	@Override
	public boolean update(int contactId, Contact contact) {
		String sql = "update contact set userId = ?,contactName = ?,"
				+ "contactGender = ?,contactBirthday = ?,contactNumber = ?,contactQQ = ?,"
				+ "contactAddress = ?,contactAddDate = ? where contactId = ?";
		
		return DBUtil.executeUpdate(sql, contact.getUserId(),contact.getContactName(),contact.getContactGender(),
				contact.getContactBirthday(),contact.getContactNumber(),contact.getContactQQ(),
				contact.getContactAddress(),contact.getContactAddDate(),contactId);
	}

	@Override
	public Contact findContactById(int contactId) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from contact where contactId = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactId);
		rs = (ResultSet)resources[0];
		
		try {
			if(rs.next()) {
				contact = new Contact();
				contact.setContactId(contactId);
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contact;
	}

	@Override
	public List<Contact> findAll() {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQueryAll(sql);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByUserId(int userId) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where userId = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, userId);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactName(String contactName) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactName = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactName);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactNumber(String contactNumber) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactNumber = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactNumber);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactQQ(String contactQQ) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactQQ = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactQQ);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactName(String contactName, int userId) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactName = ? and userId = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactName,userId);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactNumber(String contactNumber, int userId) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactNumber = ? and userId = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactNumber,userId);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

	@Override
	public List<Contact> findContactByContactQQ(String contactQQ, int userId) {
		Object[] resources = new Object[3];
		List<Contact> contactList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from contact where contactQQ = ? and userId = ? order by contactName";
		Contact contact = null;
		resources = DBUtil.executeQuery(sql, contactQQ,userId);
		rs = (ResultSet)resources[0];
		
		try {
			while(rs.next()) {
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setUserId(rs.getInt("userId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactGender(rs.getString("contactGender"));
				contact.setContactBirthday(rs.getDate("contactBirthday"));
				contact.setContactNumber(rs.getString("contactNumber"));
				contact.setContactQQ(rs.getString("contactQQ"));
				contact.setContactAddress(rs.getString("contactAddress"));
				contact.setContactAddDate(rs.getDate("contactAddDate"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, (Statement)resources[1], (Connection)resources[2]);
		}
		
		return contactList;
	}

}
