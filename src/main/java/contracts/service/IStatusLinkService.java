package contracts.service;

import contracts.domain.StatusLink;

public interface IStatusLinkService {

	public void addStatusLink(StatusLink statuslink);

	public StatusLink update(StatusLink status);
}
