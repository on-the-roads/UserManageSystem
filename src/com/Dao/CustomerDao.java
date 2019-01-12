package com.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;


import com.Bean.Customer;
import com.Utils.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CustomerDao {
	
	//添加客户
	public  void add(Customer customer){
		try {
			Connection connection=JDBCUtils.getConnection();
			QueryRunner qr=new QueryRunner();
			String sql="insert into t_customer values(?,?,?,?,?,?,?)";
			String id=customer.getId();
			String name=customer.getName();
			String gender=customer.getGender();
			String birthday=customer.getBirthday();
			String cellphone=customer.getCellphone();
			String email=customer.getCellphone();
			String description=customer.getDescription();
			qr.update(connection, sql, id,name,gender,birthday,cellphone,email,description);	
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//查询所有客户
	public List<Customer> findAll(){
		Connection connection=JDBCUtils.getConnection();
		QueryRunner qr=new QueryRunner();
		
		String sql="select * from t_customer";
		try {
			List<Customer> customerlist=qr.query(connection, sql, new BeanListHandler<Customer>(Customer.class));
			return customerlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//根据id查询客户
	public Customer findByid(String id){
		Connection connection=JDBCUtils.getConnection();
		QueryRunner qr=new QueryRunner();
		Customer customer=null;
		String sql="select * from t_customer where id=?";
		try {
		 customer=qr.query(connection, sql, id, new BeanHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return customer;
	}
	//编辑客户
	public void edit(Customer customer){
		try{
		Connection connection=JDBCUtils.getConnection();
		QueryRunner qr=new QueryRunner();
		String sql="update t_customer set name=?,gender=?,birthday=?,cellphone=?,email=?,description=? where id=?";
		String id=customer.getId();
		String name=customer.getName();
		String gender=customer.getGender();
		String birthday=customer.getBirthday();
		String cellphone=customer.getCellphone();
		String email=customer.getCellphone();
		String description=customer.getDescription();
		qr.update(connection, sql, name,gender,birthday,cellphone,email,description,id);	
	}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//编辑客户
	public List<Customer> query(Customer customer){
		try{
			Connection connection=JDBCUtils.getConnection();
			QueryRunner qr=new QueryRunner();
			List<Object> params=new ArrayList<Object>();
		
			StringBuilder sql=new StringBuilder("select * from t_customer where 1=1");
			String name=customer.getName();
			String gender=customer.getGender();
			String cellphone=customer.getCellphone();
			String email=customer.getCellphone();
			
			if(name!=null&&name.trim().length()!=0){
				sql.append(" and name like ?");
				params.add("%"+name+"%");
			}
			if(gender!=null&&gender.trim().length()!=0){
				sql.append(" and gender=?");
				params.add(gender);
			}
			if(cellphone!=null&&cellphone.trim().length()!=0){
				sql.append(" and cellphone like ?");
				params.add("%"+cellphone+"%");
			}
			if(email!=null&&email.trim().length()!=0){
				sql.append(" and email like ?");
				params.add("%"+email+"%");
			}
			List<Customer> custlist=qr.query(connection, sql.toString(), params.toArray(), new BeanListHandler<Customer>(Customer.class));
			System.out.println(custlist);
			return custlist;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
