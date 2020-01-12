package poi;

import java.util.Collections;
import java.util.List;

import model.Client;

public class POITester {

	public static void main(String[] args) {
		XLSXReader reader = new XLSXReader("resources\\file_example_XLS_1000.xlsx");
		try {
			List<Client> clients = reader.getClients();
			Collections.sort(clients);
			for (Client client : clients) {
				System.out.println(client);
			}
			System.out.println(clients.size());
			
			XLSXWriter write = new XLSXWriter("resources\\exit.xlsx");
			List<Client> cl = write.setClients(reader.getClients());
			for (Client client : cl) {
				System.out.println(client);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
