package hwadee.service;

import java.sql.Date;

import hwadee.entity.User;

public interface ISignUpService {
	public boolean isRepeatedNickName(String nickName);
	public boolean isRepeatedNickName(String nickName,int userId);
	public boolean isValidNickName(String nickName);
	public boolean isValidRealName(String realName);
	public boolean isCorrectOldPassword(char[] oldPassword,User user);
	public boolean isCorrectPassword(char[] password);
	public boolean isSameRepassword(char[] password,char[] repassword);
	public boolean isValidEmail(String email);
	public boolean isRepeatedEmail(String email);
	public boolean isRepeatedEmail(String email,int userId);
	public boolean isValidBirthday(String birthday);
	public boolean isValidNumber(String number);
	public boolean isRepeatedNumber(String number);
	public boolean isRepeatedNumber(String number,int userId);
	public boolean isValidAddress(String address);
	public User getUser(int userId);
	public boolean insertUser(String nickName,String realName,String gender,char[] password,String email,String birthday,String number,String address);
	public boolean editUser(User currentUser,String nickName,String realName,String gender,char[] password,String email,String birthday,String number,String address,Date registerDate);
}
