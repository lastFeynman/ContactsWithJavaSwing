package hwadee.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import hwadee.util.DBUtil;

public class DBUtilTest {

	@Test
	public void testGetConnection() {
		Connection conn = DBUtil.getConnection();
		assertNotEquals(null, conn);
		DBUtil.closeConnection(conn);
	}

	@Test
	public void testCloseResultSet() {
		Connection conn = DBUtil.getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select * from user");
			DBUtil.closeResultSet(rs);
			DBUtil.closeAll(rs, s, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCloseStatement() {
		Connection conn = DBUtil.getConnection();
		try {
			Statement s = conn.createStatement();
			DBUtil.closeStatement(s);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCloseConnection() {
		Connection conn = DBUtil.getConnection();
		DBUtil.closeConnection(conn);
	}

	@Test
	public void testCloseAll() {
		Connection conn = DBUtil.getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select * from user");
			DBUtil.closeAll(rs, s, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryAll() {
		Object[] objects = DBUtil.executeQueryAll("select * from contact order by contactName");
		ResultSet rs = (ResultSet)objects[0];
		
		try {
			while(rs.next())
				System.out.println(rs.getString("contactName"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
