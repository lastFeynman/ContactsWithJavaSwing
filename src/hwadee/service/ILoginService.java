package hwadee.service;

import hwadee.entity.User;

public interface ILoginService {
	public User verifyUser(String userNickName,char[] password);
}
