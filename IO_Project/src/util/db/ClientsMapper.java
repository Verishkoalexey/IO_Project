package util.db;

import java.util.List;

import model.Client;

public interface ClientsMapper {
	void save(Client client);
	void update(Client client);
	void delete(Client client);
	void saveAll(List<Client> client);
	void deleteAll(List<Client> client);
	void updateAll(List<Client> client);
	Client load(Client client);
	List<Client> loadAll(List<Client> clients);
	List<Client> loadAll();
	
}
