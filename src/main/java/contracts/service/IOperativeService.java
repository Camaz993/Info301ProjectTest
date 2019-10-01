package contracts.service;

import contracts.domain.Operative;

public interface IOperativeService {

	public void addOperative(Operative operative);

	public Operative update(Operative newOperative);
}
