package contracts.service;

import java.util.List;

import contracts.domain.User;

public interface IAccountService {
	
	public void addAccount(User newAccount);
	
	public boolean userExists(String username);
	
	public boolean validate(String password);
	
	public User findUser(String username);
	
	public List<String> getUserRoles();
	
	public void update(User user);

}
