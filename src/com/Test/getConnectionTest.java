package com.Test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;


import com.Dao.CustomerDao;
import com.Utils.JDBCUtils;

public class getConnectionTest {
	@Test
	public void test1() throws Exception {
		CustomerDao dao=new CustomerDao();
		try {
			JDBCUtils.beginTransaction();
			System.out.println("hihihihi");
			Connection connection=JDBCUtils.getConnection();
			System.out.println(connection);
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	
	}
}
