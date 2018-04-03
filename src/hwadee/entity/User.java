package hwadee.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userNickName = null;
	private String userRealName = null;
	private String userGender = null;
	private String userKey = null;
	private String userEmail = null;
	private boolean isAdmin = false;
	private Date userBirthday = null;
	private String userNumber = null;
	private String userAddress = null;
	private Date registerDate = null;
	
	public User() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userNickName=" + userNickName + ", userRealName=" + userRealName
				+ ", userGender=" + userGender + ", userKey=" + userKey + ", userEmail=" + userEmail + ", isAdmin="
				+ isAdmin + ", userBirthday=" + userBirthday + ", userNumber=" + userNumber + ", userAddress="
				+ userAddress + ", registerDate=" + registerDate + "]";
	}
	
	
}
