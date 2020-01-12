package dao;

import java.time.LocalDate;
import java.util.List;

import model.Client;

public interface ClientsDAO {
	
	Client getClientById(Client client);
	List<Client> getAllClients();
	boolean insertClient(Client client);
	boolean updateClient(Client client);
	boolean deleteClient(Client client);
	Client getClientByCountry(Client client);
	public boolean insertClientRestore(Client client);
	
	// NEW
	boolean deleteClientsByCountry(String country);
	List<Client> getAllClientRegistredBefore(LocalDate data);
	boolean updateOrInsertClient(Client client);
	
	//***
	boolean restoreClient(Client client);
}
