package hwadee.service;

import hwadee.entity.Contact;
import hwadee.entity.User;

public interface IUserIndexService {
	public Object[][] getAllContacts(User user);
	public boolean isValidPageNum(String page,int maxPage);
	public void exportAllContact(User user);
	public boolean importContact(String filePath,Object[][] objects);
	public void deleteContact(String conIdStr);
	public void deleteAll(User user);
	public Contact getContact(String conIdStr);
	public Object[][] getBirthdayContacts(User user);
	public Object[][] searchContacts(User user,String searchContent,String method);
	public Object[][] orderedByNumber(Object[][] objects);
	public Object[][] orderedByName(Object[][] objects);
}
