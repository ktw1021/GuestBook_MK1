package himedia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestBookDao {

	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "himedia";
	private static final String DB_PASS = "himedia";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Oracle JDBC driver not found",e);
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	
	public List<String[]> getList() {
		
		List<String[]> dataList = new ArrayList<>();
		String sql = "SELECT no, name, TO_CHAR(reg_date, 'YYYY-MM-DD HH24:Mi') AS reg_date, content FROM GuestBook ORDER BY no";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String[] entry = new String[5];
				entry[0] = rs.getString("no");
				entry[1] = rs.getString("name");
				entry[2] = rs.getString("reg_date");
				entry[3] = rs.getString("content");
				dataList.add(entry);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dataList;
		
	}
	
	public void insert(String name, String password, String content) {
		String sql = "INSERT INTO GUESTBOOK (name, password, content) "
				+ "VALUES(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean deleteWithPasswordCheck(int no, String password) {
		String selectSql = "SELECT password from GUESTBOOK WHERE no = ?";
		String deleteSql = "DELETE FROM GUESTBOOK WHERE no = ?";
		
		Connection conn = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			selectStmt = conn.prepareStatement(selectSql);
			selectStmt.setInt(1, no);
			try {
				rs = selectStmt.executeQuery(); 
					if (rs.next()) {
						String dbPassword = rs.getString("password");
						if (dbPassword.equals(password)) {
							try {
								PreparedStatement deleteStmt = conn.prepareStatement(deleteSql); 
									deleteStmt.setInt(1, no);
									deleteStmt.executeUpdate();
								return true;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Map<String, String> getEntry(int no) {
	    String sql = "SELECT no, name, content FROM GuestBook WHERE no = ?";
	    Map<String, String> entry = new HashMap<>();

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, no);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                entry.put("no", rs.getString("no"));
	                entry.put("name", rs.getString("name"));
	                entry.put("content", rs.getString("content"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return entry;
	}
	
	public boolean updateEntry(int no, String name, String password, String content) {
		
		String sql = "UPDATE GUESTBOOK SET name = ?, content = ? WHERE no = ? AND password = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try { conn = getConnection();
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, name);
			  pstmt.setString(2, content);
			  pstmt.setInt(3, no);
			  pstmt.setString(4, password);
			  
			  int affectedRows = pstmt.executeUpdate();
			  return affectedRows >0;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
