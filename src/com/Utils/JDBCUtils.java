package com.Utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tL=new ThreadLocal<Connection>();

	
	public static DataSource getDataSource() {
		
		return ds;
	}
	
	
	
	/**
	 * ---------------获得连接connection对象-------------------
	 * 判断con是否为null，如果为null说明没有事务，那么从连接池获取一个连接返回；
	 * 如果不为null，说明已经开始了事务，那么返回con属性返回,这说明在con不为null时，无论
	 * 调用多少次getConnection()方法，返回的都是同个Connection对象。
	 */
	public static Connection getConnection() {
		Connection conn=tL.get();
		if (conn == null) {
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conn;
	}

	/**
	 * ----------开始事务--------------- 
	 * 判断con是否为null，如果不为null，就抛出异常！
	 * 如果con为null，那么从连接池中获取一个Connection对象，赋值给con！然后设置它为“手动提交”。
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection conn=tL.get();
		if (conn != null) {
			throw new SQLException("事务已经开启，在当前事务没有结束之前，不能再开启事务");
		}
		conn = ds.getConnection();
		conn.setAutoCommit(false);
		tL.set(conn);
	}

	/**
	 * ------------提交事务------------- 
	 * 判断con是否为null，如果为null，说明没有开启事务就提交事务，那么抛出异常；
	 * 如果con不为null，那么调用con的commit()方法来提交事务； 调用con.close()方法关闭连接； con
	 * =null，这表示事务已经结束！
	 * @throws SQLException 
	 * 
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn=tL.get();
		if (conn == null) {
			throw new SQLException("当前没有事务");
		}
		conn.commit();
		conn.close();
		conn=null;
		tL.remove();
	}

	/**
	 * -----------------回滚事务-------------------
	 * 判断con是否为null，如果为null，说明没有开启事务就回滚事务，那么抛出异常；
	 * 如果con不为null，那么调用con的rollback()方法来回滚事务； 调用con.close()方法关闭连接； con =
	 * null，这表示事务已经结束！
	 * @throws SQLException 
	 * 
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection conn=tL.get();
		if (conn == null) {
			throw new SQLException("当前没有事务");
		}
		conn.rollback();
		conn.close();
		conn=null;
		tL.remove();
	}
}
