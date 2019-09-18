package contracts.service;

import contracts.domain.User;

public interface IAccountService {
	
	public void addAccount(User newAccount);
	
	public boolean userExists(String username);
	
	public boolean validate(String password);

}
