package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.impl.DBClientsDAOImpl;
import model.Client;

public class ClientsDAOTest {
	private static DBClientsDAOImpl DBClientsDAOImpl;
	
	@BeforeClass
	public static void initData(){
		DBClientsDAOImpl = new DBClientsDAOImpl();
		System.out.println("1. DBClientsDAOImpl initialization");
	}
	
	@Test
	public void getClientById1(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(null == DBClientsDAOImpl.getClientById(new Client(-1)));
		System.out.println("3. VIEW RESULTS");
	}
	
	@Test
	public void getClientById2(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(null != DBClientsDAOImpl.getClientById(new Client(25)));
		System.out.println("3. VIEW RESULTS");
	}
	
	@Test(expected = NullPointerException.class)
	public void getClientById3(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(null == DBClientsDAOImpl.getClientById(null));
		System.out.println("3. VIEW RESULTS");
	}
	
	
	@Test
	public void insertClients1(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(DBClientsDAOImpl.insertClient(new Client("BOB", "BOBSON", "M", "USA", 52, new Date())));
		System.out.println("3. VIEW RESULTS");
	}
	
	@Test(expected = NullPointerException.class)
	public void insertClients2(){
		System.out.println("2. RUN DBClientsDAOImpl");
		assertTrue(DBClientsDAOImpl.insertClient(new Client()));
		System.out.println("3. VIEW RESULTS");
	}
	
	
	@AfterClass // 
	public static void destrData(){
		DBClientsDAOImpl = null;
		System.out.println("4. DBClientsDAOImpl finalization");
	}
	
}
