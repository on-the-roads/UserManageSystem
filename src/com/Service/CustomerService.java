package com.Service;

import java.sql.SQLException;
import java.util.List;


import com.Bean.Customer;
import com.Dao.CustomerDao;
import com.Utils.JDBCUtils;

public class CustomerService {
	CustomerDao dao=new CustomerDao();
	
	//添加客户
	public void add(Customer customer){
		try {
			JDBCUtils.beginTransaction();
			dao.add(customer);
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}
	
	//查询客户
	public  List<Customer> findAll(){
		List<Customer> customerlist=null;
		try {
			JDBCUtils.beginTransaction();
		 customerlist=dao.findAll();
			JDBCUtils.commitTransaction();
			
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return customerlist;
	} 
	//高级检索
	public  Customer findById(String id){
		Customer customer=null;
		try {
			JDBCUtils.beginTransaction();
			 customer=dao.findByid(id);
			JDBCUtils.commitTransaction();
			
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return customer;
	} 
	
	//编辑
	public  void edit(Customer customer){
		try {
			JDBCUtils.beginTransaction();
			dao.edit(customer);
			JDBCUtils.commitTransaction();
			
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		}
	
	//多条件查询
	public  List<Customer> query(Customer customer){
		List<Customer> cusList=null;
		try {
			JDBCUtils.beginTransaction();
			cusList=dao.query(customer);
			JDBCUtils.commitTransaction();
			
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return cusList;
	}
	} 
