package hwadee.dao;

import java.util.List;

import hwadee.entity.User;

public interface IUserDao {
	//插入用户
	public boolean insert(User user);
	//删除用户
	public boolean delete(int userId);
	//修改用户
	public boolean update(int userId,User user);
	//通过用户Id查找用户
	public User findUserById(int userId);
	//通过用户名查找用户
	public User findUserByUserNickName(String userNickName);
	//通过用户真实姓名查找用户
	public List<User> findUserByUserRealName(String userRealName);
	//通过用户邮箱查找用户
	public User findUserByUserEmail(String userEmail);
	//通过用户手机号查找用户
	public User findUserByUserNumber(String userNumber);
	//查找所有用户
	public List<User> findAll();
}
