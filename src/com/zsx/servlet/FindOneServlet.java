package com.zsx.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.zsx.bean.User;
import com.zsx.utils.DBUtils;

/**
 * Servlet implementation class FindOneServlet
 */
@WebServlet("/FindOneServlet")
public class FindOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询单个用户
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");// 获得前端传来的id;
		Map<String, User> map = new HashMap<>();
		User user = new User();
		// 获得数据库连接
		Connection conn = DBUtils.open();
		String sql = "select id,user_name,password from user where id = ?";
		try {
			PreparedStatement prtmt = conn.prepareStatement(sql);
			prtmt.setString(1, id); // 设置?的值
			ResultSet rs = prtmt.executeQuery(); /// 执行sql
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("user_name"));
				user.setPasswrod(rs.getString("password"));
				map.put("user", user);
			}
			request.setAttribute("user", user);
			System.out.println(user.getPasswrod() + "," + user.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(); // 关闭数据库连接
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
