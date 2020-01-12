package test.dao.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.impl.DBClientsDAOImpl;
import model.Client;
import poi.XLSXReader;
import util.JDBCUtils;

public class TestClientsDAO {
	
	private static DBClientsDAOImpl impl;
	
    @BeforeClass
    public static void init() {
        impl = new DBClientsDAOImpl();
        //switch to the TEST DB!
        JDBCUtils.switchDBName("db_test_name");
        
        //1. Create DB 
        String SQL_CREATE_DB = "CREATE DATABASE `"
                +JDBCUtils.DB_NAME+"`;";
        Connection conn = JDBCUtils.createConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL_CREATE_DB);
        
        JDBCUtils.release(null, stmt, null);
        //2. Create TABLES
        String SQL_CREATE_TABLE_CLIENT = "CREATE TABLE\r\n" + 
        "    "+JDBCUtils.DB_NAME+".Clients\r\n" + 
        "    (\r\n" + 
        "        ID INT NOT NULL AUTO_INCREMENT,\r\n" + 
        "        FIRST_NAME VARCHAR(32) NOT NULL,\r\n" + 
        "        LAST_NAME VARCHAR(32) NOT NULL,\r\n" + 
        "        GENDER VARCHAR(16) NOT NULL,\r\n" + 
        "        COUNTRY VARCHAR(32) NOT NULL,\r\n" + 
        "        AGE INT NOT NULL,\r\n" + 
        "        REG_DATE DATE NOT NULL,\r\n" + 
        "        CREATED_TS TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\r\n" + 
        "        UPDATED_TS TIMESTAMP NULL,\r\n" + 
        "        PRIMARY KEY (ID)\r\n" + 
        "    )\r\n" + 
        "    ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        
        stmt = conn.createStatement();
        stmt.execute(SQL_CREATE_TABLE_CLIENT);
        
        JDBCUtils.release(null, stmt, null);
        
        String SQL_CREATE_TABLE_CLIENT_HISTORY = "CREATE TABLE\r\n" + 
        "    "+JDBCUtils.DB_NAME+".Clients_HISTORY\r\n" + 
        "    (\r\n" + 
        "        CHANGE_ID INT NOT NULL AUTO_INCREMENT,\r\n" + 
        "        ACTION VARCHAR(8) NOT NULL,\r\n" + 
        "        ID INT NOT NULL,\r\n" + 
        "        FIRST_NAME VARCHAR(32) NOT NULL,\r\n" + 
        "        LAST_NAME VARCHAR(32) NOT NULL,\r\n" + 
        "        GENDER VARCHAR(16) NOT NULL,\r\n" + 
        "        COUNTRY VARCHAR(32) NOT NULL,\r\n" + 
        "        AGE INT NOT NULL,\r\n" + 
        "        REG_DATE DATE NOT NULL,\r\n" + 
        "        ACTION_TS TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\r\n" + 
        "        PRIMARY KEY (CHANGE_ID)\r\n" + 
        "    )\r\n" + 
        "    ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        
        stmt = conn.createStatement();
        stmt.execute(SQL_CREATE_TABLE_CLIENT_HISTORY);
        
        JDBCUtils.release(conn, stmt, null);

        List<Client> clients = new XLSXReader("resources/file_example_XLS_1000.xlsx")
                .getClients();
        for (Client client : clients) {
            impl.insertClient(client);
        }
    
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void destroy() {
        impl = null;
        
        //1. Create DB 
        String SQL_DROP_DB = "DROP DATABASE `"
                +JDBCUtils.DB_NAME+"`;";
        Connection conn = JDBCUtils.createConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(SQL_DROP_DB);
        } catch(Exception e) {
            
        }
        //switch back! ??!?
        JDBCUtils.switchDBName("db_name");
        
    }
    
    @Test
    public void getClientTest1(){
        Client client = new Client(2);
        client = impl.getClientById(client);
        assertNotNull("CLIENT DOES NOT EXIST", client);
        
    }
    
    @Test(timeout = 1000)
    public void getClientTest2(){
        Client client = new Client(-2);
        client = impl.getClientById(client);
        assertNull("CLIENT EXIST!", client);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void getClientTest3(){
        impl.getClientById(null);
    }


	
	
	@Test(timeout = 500)
	public void updateClient1(){
	Client client = new Client(35,35,"BOB", "BOBSON", "M", "USA", 52, new Date());
	boolean is =impl.updateClient(client);
	Assert.assertTrue(is);
	}
	
	
	@Test(timeout = 500)
	public void updateClient2(){
	Client client = new Client(-35,35,"BOB", "BOBSON", "M", "USA", 52, new Date());
	boolean is =impl.updateClient(client);
	Assert.assertFalse(is);
	}
	
	@Test(expected = NullPointerException.class)
	public void updateClient3(){
		Assert.assertTrue(impl.updateClient(new Client()));
	}
	
	@Test
	public void insertClients1(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(impl.insertClient(new Client("BOB", "BOBSON", "M", "USA", 52, new Date())));
		System.out.println("3. VIEW RESULTS");
	}
	
	@Test(expected = NullPointerException.class)
	public void insertClients2(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(impl.insertClient(new Client()));
		System.out.println("3. VIEW RESULTS");
	}
	
	@Test
	public void deleteClient1(){
		Assert.assertTrue(impl.deleteClient(new Client(32)));
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteClient2(){
		Assert.assertTrue(impl.deleteClient(new Client(-32)));
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteClient3(){
		Assert.assertTrue(impl.deleteClient(new Client()));
	}
	
	
	
	
}
