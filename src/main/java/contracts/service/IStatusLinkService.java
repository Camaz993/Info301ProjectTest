package contracts.service;

import java.util.List;

import contracts.domain.StatusLink;

public interface IStatusLinkService {

	public void addStatusLink(StatusLink statuslink);

	public StatusLink update(StatusLink status);

	public List<StatusLink> getAllStatus();
}
