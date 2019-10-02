package contracts.service;

import contracts.domain.InNegotiation;

public interface IInNegotiationService {

	public void addInNegotiation(InNegotiation inNegotiation);

	public InNegotiation update(InNegotiation in_negotiation);
}
