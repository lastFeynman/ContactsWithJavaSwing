package hwadee.entity;

import java.io.Serializable;
import java.sql.Date;

public class Contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contactId;
	private int userId;
	private String contactName = null;
	private String contactGender = null;
	private Date contactBirthday = null;
	private String contactNumber = null;
	private String contactQQ = null;
	private String contactAddress = null;
	private Date contactAddDate = null;
	
	public Contact() {}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactGender() {
		return contactGender;
	}

	public void setContactGender(String contactGender) {
		this.contactGender = contactGender;
	}

	public Date getContactBirthday() {
		return contactBirthday;
	}

	public void setContactBirthday(Date contactBirthday) {
		this.contactBirthday = contactBirthday;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Date getContactAddDate() {
		return contactAddDate;
	}

	public void setContactAddDate(Date contactAddDate) {
		this.contactAddDate = contactAddDate;
	}

	@Override
	public String toString() {
		return "ContactEntity [contactId=" + contactId + ", userId=" + userId + ", contactName=" + contactName
				+ ", contactGender=" + contactGender + ", contactBirthday=" + contactBirthday + ", contactNumber="
				+ contactNumber + ", contactQQ=" + contactQQ + ", contactAddress=" + contactAddress
				+ ", contactAddDate=" + contactAddDate + "]";
	}
	
	
}
