package contracts.service;

import contracts.domain.Expired;

public interface IExpiredService {

	public void addExpired(Expired expired);

	public Expired update(Expired newExpired);
}
