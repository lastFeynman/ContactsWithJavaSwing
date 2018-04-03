package hwadee.service;

import hwadee.entity.User;

public interface IUserManageService {
	public Object[][] getAllUser(User user);
	public void deleteAll(User user);
	public Object[][] searchUser(User user,String searchContent,String method);
	public boolean exportUser();
	public boolean importUser(String path);
	public void deleteUser(String userStr);
	public User getUser(String userStr);
}
