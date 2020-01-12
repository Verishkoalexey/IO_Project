package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	
	public static String DB_NAME = null;
	static{
		DB_NAME = AppSettings.getInstance().getValue("db_name");
	}
	// FOR JUNIT
	public static void switchDBName(String key){
		DB_NAME = AppSettings.getInstance().getValue(key);
	}
	
    
    static String DB_USER = "root";
    static String DB_USER_PSW = "";
    static String DB_URL = "jdbc:mysql://localhost:3306/TEST";
    
    public static Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_USER_PSW);
            if(conn == null ) {
                System.out.println(String.format("Can't connect to %s", DB_URL));
                
            } else {
                System.out.println(String.format("Opening Connection ID: %s", conn));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void release(Connection conn, Statement stmt, PreparedStatement pstmt) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println(String.format("Closing Connection ID: %s", conn));
            }
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println(String.format("Closing Statement ID: %s", stmt));
            }
            if(pstmt != null && !pstmt.isClosed()) {
            	pstmt.close();
                System.out.println(String.format("Closing PreparedStatement ID: %s", pstmt));
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
