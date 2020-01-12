package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClientsDAO;
import model.Client;
import poi.XLSXReader;
import util.JDBCUtils;

public class DBClientsDAOImpl implements ClientsDAO {

	@Override
	public Client getClientById(Client client) {
		// 1.Create connection
		Connection conn = JDBCUtils.createConnection();
		Statement stmt = null;
		Client result = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+JDBCUtils.DB_NAME+".CLIENTS WHERE ID = " + client.getId());
			if(rs.next()){
				// Strings
				result = new Client();
				result.setId(client.getId());
				result.setFirstName(rs.getString("FIRST_NAME"));
				result.setLastName(rs.getString("LAST_NAME"));
				result.setCountry(rs.getString("COUNTRY"));
				result.setGender(rs.getString("GENDER"));
				// INTs
				result.setAge(rs.getInt("AGE"));
				//DATEs
				result.setDate(rs.getDate("REG_DATE"));
				result.setCreatedTs(rs.getTimestamp("CREATED_TS"));
				result.setCreatedTs(rs.getTimestamp("UPDATED_TS"));
			} else {
				System.out.println("No such client found with ID = " + client.getId());
			}
			
			JDBCUtils.release(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Client getClientByCountry(Client client) {
		Connection conn = JDBCUtils.createConnection();
		Statement stmt = null;
		Client result = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+JDBCUtils.DB_NAME+".CLIENTS WHERE ID = " + client.getCountry());
			if(rs.next()){
				// Strings
				result = new Client();
				result.setCountry(client.getCountry());
				result.setFirstName(rs.getString("FIRST_NAME"));
				result.setLastName(rs.getString("LAST_NAME"));
				result.setGender(rs.getString("GENDER"));
				// INTs
				result.setId(rs.getInt("ID"));
				result.setAge(rs.getInt("AGE"));
				//DATEs
				result.setDate(rs.getDate("REG_DATE"));
				result.setCreatedTs(rs.getTimestamp("CREATED_TS"));
				result.setCreatedTs(rs.getTimestamp("UPDATED_TS"));
			} else {
				System.out.println("No such client found with COUNTRY = " + client.getCountry());
			}
			
			JDBCUtils.release(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> resultCli = new ArrayList<>();
        Client client = null;
        String selectSQL = "SELECT * FROM "+JDBCUtils.DB_NAME+".Clients";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = JDBCUtils.createConnection();
            pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("ID"));
                client.setFirstName(rs.getString("FIRST_NAME"));
                client.setLastName(rs.getString("LAST_NAME"));
                client.setGender(rs.getString("GENDER"));
                client.setCountry(rs.getString("COUNTRY"));
                client.setAge(rs.getInt("AGE"));
                client.setDate(rs.getDate("REG_DATE"));
                client.setCreatedTs(rs.getTimestamp("CREATED_TS"));
                client.setUpdatedTs(rs.getTimestamp("UPDATED_TS"));
                resultCli.add(client);
            }
            JDBCUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultCli;
	}

	@Override
	public boolean insertClient(Client client) {
		String insertSQL = "INSERT INTO "+JDBCUtils.DB_NAME+".clients (FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE, CREATED_TS) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isInserted = false;
		try {
			conn = JDBCUtils.createConnection();
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getGender());
			pstmt.setString(4, client.getCountry());
			pstmt.setInt(5, client.getAge());
			pstmt.setDate(6, new java.sql.Date(client.getDate().getTime()));
			isInserted = pstmt.executeUpdate() == 1;
			
			JDBCUtils.release(null, null, pstmt);
			// New for History
			pstmt = conn.prepareStatement("INSERT INTO "+JDBCUtils.DB_NAME+".clients_HISTORY (ACTION, ID, FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE) VALUES ('I', (SELECT MAX(ID) FROM clientsdb.clients), ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getGender());
			pstmt.setString(4, client.getCountry());
			pstmt.setInt(5, client.getAge());
			pstmt.setDate(6, new java.sql.Date(client.getDate().getTime()));
			pstmt.executeUpdate();
			
			JDBCUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isInserted;
	}
	
	@Override
	public boolean insertClientRestore(Client client) {
		String insertSQL = "INSERT INTO "+JDBCUtils.DB_NAME+".clients (FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE, CREATED_TS) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isInserted = false;
		try {
			conn = JDBCUtils.createConnection();
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getGender());
			pstmt.setString(4, client.getCountry());
			pstmt.setInt(5, client.getAge());
			pstmt.setDate(6, new java.sql.Date(client.getDate().getTime()));
			isInserted = pstmt.executeUpdate() == 1;
			JDBCUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isInserted;
	}

	@Override
	public boolean updateClient(Client client) {
		//String updateSQL = "UPDATE ClientsDB.Clients SET FIRST_NAME = '"+client.getFirstName()+"', LAST_NAME = 'Johnson1', GENDER = 'Ma', COUNTRY = 'BY1' WHERE ID = 1";
		
		String updateSQL = "UPDATE "+JDBCUtils.DB_NAME+".clients SET FIRST_NAME = ?, LAST_NAME = ?, GENDER = ?, COUNTRY = ?, AGE = ?, REG_DATE = ?, UPDATED_TS = CURRENT_TIMESTAMP WHERE ID = ?";
		String updateHistorySQL = "INSERT INTO "+JDBCUtils.DB_NAME+".clients_HISTORY (ACTION, ID, FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE) VALUES ('U', ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;
		try {
			conn = JDBCUtils.createConnection();
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getGender());
			pstmt.setString(4, client.getCountry());
			pstmt.setInt(5, client.getAge());
			pstmt.setDate(6, new java.sql.Date(client.getDate().getTime()));
			pstmt.setInt(7, client.getId());
			
			isUpdated = pstmt.executeUpdate() == 1;
			
			JDBCUtils.release(null, null, pstmt);
			// Create for History
			if(isUpdated){
				JDBCUtils.release(null, null, pstmt);
				pstmt = conn.prepareStatement(updateHistorySQL);
				pstmt.setInt(1, client.getId());
				pstmt.setString(2, client.getFirstName());
				pstmt.setString(3, client.getLastName());
				pstmt.setString(4, client.getGender());
				pstmt.setString(5, client.getCountry());
				pstmt.setInt(6, client.getAge());
				pstmt.setDate(7, new java.sql.Date(client.getDate().getTime()));
				pstmt.executeUpdate();

			JDBCUtils.release(conn, null, pstmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUpdated;
	}

	@Override
	public boolean deleteClient(Client client) {
		String deleteSQL = "DELETE FROM "+JDBCUtils.DB_NAME+".clients WHERE ID = ?";
		String deleteHistorySQL = "INSERT INTO "+JDBCUtils.DB_NAME+".clients_HISTORY (ACTION, ID, FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE) VALUES ('D', ?, ?, ?, ?, ?, ?, ?)";
		// to hold before deletion
		client = getClientById(client);
		// removing ...
		
		//history inserting ...
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isDelited = false;
		try {
			conn = JDBCUtils.createConnection();
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, client.getId());
			
			isDelited = pstmt.executeUpdate() == 1;
			JDBCUtils.release(null, null, pstmt);
			if(isDelited){
				JDBCUtils.release(null, null, pstmt);
				pstmt = conn.prepareStatement(deleteHistorySQL);
				pstmt.setInt(1, client.getId());
				pstmt.setString(2, client.getFirstName());
				pstmt.setString(3, client.getLastName());
				pstmt.setString(4, client.getGender());
				pstmt.setString(5, client.getCountry());
				pstmt.setInt(6, client.getAge());
				pstmt.setDate(7, new java.sql.Date(client.getDate().getTime()));
				pstmt.executeUpdate();
			JDBCUtils.release(conn, null, pstmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return isDelited;
	}
	

	@Override
	public boolean deleteClientsByCountry(String country) {
		String deleteSQL = "DELETE FROM " +JDBCUtils.DB_NAME+".clients WHERE COUNTRY = ?";
		//String deleteHistorySQL = "INSERT INTO clientsdb.clients_HISTORY (ACTION, ID, FIRST_NAME, LAST_NAME, GENDER, COUNTRY, AGE, REG_DATE) VALUES ('D', ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isDelited = false;
		try {
			conn = JDBCUtils.createConnection();
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, country);
			isDelited = pstmt.executeUpdate() == 1;
			JDBCUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return isDelited;
	}
	
	@Override
	public List<Client> getAllClientRegistredBefore(LocalDate data) {
		List<Client> resultCli = new ArrayList<>();
        Client client = null;
        String selectSQL = "SELECT * FROM ClientsDB.Clients WHERE REG_DATE <'" + data + "'";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = JDBCUtils.createConnection();
            pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("ID"));
                client.setFirstName(rs.getString("FIRST_NAME"));
                client.setLastName(rs.getString("LAST_NAME"));
                client.setGender(rs.getString("GENDER"));
                client.setCountry(rs.getString("COUNTRY"));
                client.setAge(rs.getInt("AGE"));
                client.setDate(rs.getDate("REG_DATE"));
                client.setCreatedTs(rs.getTimestamp("CREATED_TS"));
                client.setUpdatedTs(rs.getTimestamp("UPDATED_TS"));
                resultCli.add(client);
            }
            JDBCUtils.release(conn, null, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultCli;
	}


	@Override
	public boolean updateOrInsertClient(Client client) {
		if(updateClient(client)||insertClient(client)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean restoreClient(Client client) {
		boolean isRestore = false;
		if (getClientById(client) == null) {
			String getClientFromHistorySQL = "SELECT * FROM " + JDBCUtils.DB_NAME + ".clients_history WHERE ID="+ client.getId();
			Connection conn = null;
			Statement stmt = null;
			Client tempClient = null;
			try {
				conn = JDBCUtils.createConnection();
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getClientFromHistorySQL);
				if (rs.next()) {
					tempClient = new Client();
					tempClient.setFirstName(rs.getString("FIRST_NAME"));
					tempClient.setLastName(rs.getString("LAST_NAME"));
					tempClient.setCountry(rs.getString("COUNTRY"));
					tempClient.setGender(rs.getString("GENDER"));
					tempClient.setAge(rs.getInt("AGE"));
					tempClient.setDate(rs.getDate("REG_DATE"));
				}else {
					System.out.println("No such client found ID = " + client.getId());
				}
				JDBCUtils.release(conn, stmt, null);
				
				isRestore = insertClient(tempClient);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			System.out.println("NO RESTORE!");
		}
		return isRestore;
	}
	
	public static void main(String[] args) {
		DBClientsDAOImpl dao = new DBClientsDAOImpl();
		Client key = new Client(1528);
		//dao.deleteClientsByCountry("USA");
		
		
		Client dbClient = dao.getClientById(key);
		System.out.println(dbClient);
//		dao.deleteClient(dbClient);
//		dbClient.setAge(dbClient.getAge()+1);
//		dbClient.setCountry("BY");
		
//		boolean isUpdated = dao.updateClient(dbClient);
//		System.out.println(isUpdated);
//		
//		Client newClient = new Client("BOB", "BOBSON", "M", "USA", 52, new Date());
//		dao.insertClient(newClient);
		
//		try {
//			List<Client> clients = new XLSXReader("resources\\file_example_XLS_1000.xlsx").getClients();
//			for (Client client : clients) {
//				dao.insertClient(client);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		LocalDate date = LocalDate.of(2016, 07, 15);
//		System.out.println(date);
//		System.out.println(dao.getAllClientRegistredBefore(date));
		
		//System.out.println(dao.restoreClient(key));

		
		
		
		
		
	}

}
