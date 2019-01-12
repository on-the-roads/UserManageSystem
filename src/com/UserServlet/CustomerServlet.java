package com.UserServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

import com.Bean.Customer;
import com.Service.CustomerService;
import com.Utils.JDBCUtils;


public class CustomerServlet extends BaseServlet {

	/**
	 * 添加功能
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//依赖service层
		CustomerService service=new CustomerService();
		//封装表单数据
		Customer customer=CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customer.setId(CommonUtils.uuid());
		service.add(customer);
		
		request.setAttribute("msg", "添加用户信息成功！");
		return "f:/msg.jsp";//转发
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//依赖service层
		CustomerService service=new CustomerService();
		List<Customer> customerlist=service.findAll();
		
		request.setAttribute("customerlist",customerlist);
		return "f:/list.jsp";//转发
	}
	
	/**
	 * 预编辑功能
	 */
	public String preedit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//依赖service层
		CustomerService service=new CustomerService();
		
		//获取id查询用户
		String id=request.getParameter("id");
		Customer customer=service.findById(id);
		request.setAttribute("customer", customer);
		return "f:/edit.jsp";//转发
	}
	
	/**
	 * 编辑功能
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//依赖service层
		CustomerService service=new CustomerService();
		
		Customer customer=CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		customer.setId(CommonUtils.uuid());
		service.edit(customer);
		
		request.setAttribute("msg", "编辑用户信息成功！");
		return "f:/msg.jsp";//转发
	}
	
	/**
	 * 多条件查询
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//依赖service层
		CustomerService service=new CustomerService();
		
		Customer customer=CommonUtils.toBean(request.getParameterMap(), Customer.class);
		List<Customer> cuList=service.query(customer);
		
		request.setAttribute("customerlist", cuList);
		return "f:/list.jsp";//转发
	}
	
	}
