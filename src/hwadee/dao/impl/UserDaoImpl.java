package hwadee.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hwadee.dao.IUserDao;
import hwadee.entity.User;
import hwadee.util.DBUtil;

public class UserDaoImpl implements IUserDao{

	@Override
	public boolean insert(User user) {
		String sql = "insert into "
				+ "user(userNickName,userRealName,userGender,userKey,userEmail,isAdmin,userBirthday,userNumber,userAddress,registerDate)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		
		return DBUtil.executeUpdate(sql, user.getUserNickName(),user.getUserRealName(),user.getUserGender(),
				user.getUserKey(),user.getUserEmail(),user.isAdmin(),user.getUserBirthday(),user.getUserNumber(),
				user.getUserAddress(),user.getRegisterDate());
	}

	@Override
	public boolean delete(int userId) {
		String sql = "delete from user where userId = ?";
		return DBUtil.executeUpdate(sql, userId);
	}

	@Override
	public boolean update(int userId, User user) {
		String sql = "update user set userNickName = ?,userRealName = ?,userGender = ?,userKey = ?,"
				+ "userEmail = ?,isAdmin = ?,userBirthday = ?,userNumber = ?,userAddress = ?,registerDate = ? "
				+ "where userId = ?";
		return DBUtil.executeUpdate(sql, user.getUserNickName(),user.getUserRealName(),user.getUserGender(),
				user.getUserKey(),user.getUserEmail(),user.isAdmin(),user.getUserBirthday(),user.getUserNumber(),
				user.getUserAddress(),user.getRegisterDate(),userId);
	}

	@Override
	public User findUserById(int userId) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user where userId = ?";
		resources = DBUtil.executeQuery(sql, userId);
		rs = (ResultSet)resources[0];
		User user = null;
		
		try {
			if(rs.next()) {
				user = new User();
				user.setUserId(userId);
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user";
		resources = DBUtil.executeQueryAll(sql);
		rs = (ResultSet)resources[0];
		User user = null;
		List<User> userList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return userList;
	}

	@Override
	public User findUserByUserNickName(String userNickName) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user where userNickName = ?";
		resources = DBUtil.executeQuery(sql, userNickName);
		rs = (ResultSet)resources[0];
		User user = null;
		
		try {
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return user;
	}

	@Override
	public List<User> findUserByUserRealName(String userRealName) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user where userRealName = ?";
		resources = DBUtil.executeQuery(sql, userRealName);
		rs = (ResultSet)resources[0];
		User user = null;
		List<User> userList = new ArrayList<>();
		
		try {
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return userList;
	}

	@Override
	public User findUserByUserEmail(String userEmail) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user where userEmail = ?";
		resources = DBUtil.executeQuery(sql, userEmail);
		rs = (ResultSet)resources[0];
		User user = null;
		
		try {
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return user;
	}

	@Override
	public User findUserByUserNumber(String userNumber) {
		Object[] resources = new Object[3];
		ResultSet rs = null;
		String sql = "select * from user where userNumber = ?";
		resources = DBUtil.executeQuery(sql, userNumber);
		rs = (ResultSet)resources[0];
		User user = null;
		
		try {
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserNickName(rs.getString("userNickName"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserKey(rs.getString("userKey"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setAdmin(rs.getInt("isAdmin")==1);
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserNumber(rs.getString("userNumber"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setRegisterDate(rs.getDate("registerDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs,(Statement)resources[1],(Connection)resources[2]);
		}
		
		return user;
	}

}
