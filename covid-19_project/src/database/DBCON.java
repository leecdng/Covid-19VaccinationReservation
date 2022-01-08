package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBCON {
	//����̺� �ε�
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
			System.out.println("jdbc ����̺� �ε� ����");
			e.printStackTrace();
		}
	}
	
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	String url = "jdbc:oracle:thin:@bitcamp4.iptime.org:1521:xe";
	String name = "c##Class03";
	String pwd = "winner03";
	
	public DBCON() {
		
	}
	
	public void dbConn() {
		try {
			conn  =DriverManager.getConnection(url,name,pwd);
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		catch (Exception e) {
			System.out.println("�����ͺ��̽� �ݱ� ����");
			e.printStackTrace();
		}
	}

}
