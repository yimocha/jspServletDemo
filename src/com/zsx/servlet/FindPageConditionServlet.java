package com.zsx.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zsx.bean.Page;
import com.zsx.bean.User;
import com.zsx.utils.DBUtils;

/**
 * Servlet implementation class FindAllServlet
 */
@WebServlet("/findPageConditionServlet")
public class FindPageConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userName = null;
	private Integer currentPage = 1;
	private Integer pageSize = 10;

	/**
	 * 按条件查询用户
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = new ArrayList<User>(); // 创建一个list集合,用于装user集合
		Connection conn = DBUtils.open(); // 打开数据库连接
		String sql = " select id,user_name,password from user where 1=1 ";
		String sqlCount = " select count(id) count from user where 1=1 ";

		// 获取姓名
		userName = request.getParameter("userName");
		// 分页
		if (request.getParameter("currentPage") != null && !"".equals(request.getParameter("currentPage"))) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if (request.getParameter("pageSize") != null && !"".equals(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
		if (null != userName) {
			String whereString = " and user_name like " + "concat('%','" + userName + "','%')";
			sql += whereString;
			sqlCount += whereString;
		}

		currentPage = currentPage != null ? currentPage : 1;
		pageSize = pageSize != null ? pageSize : 20;
		long idx = (currentPage - 1) * pageSize;
		if (pageSize != -1) {
			// 分页sql
			sql += " limit " + idx + "," + pageSize;
		}

		try {
			PreparedStatement prtmt = conn.prepareStatement(sql);
			ResultSet rs = prtmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("user_name");
				String password = rs.getString("password");
				User user = new User(); /// 实例化user
				user.setId(id);
				user.setUserName(username);
				user.setPasswrod(password);
				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(); // 关闭数据库连接
		}

		Connection connCount = DBUtils.open(); // 打开数据库连接
		// 查询数量
		try {
			PreparedStatement prtmtCount = connCount.prepareStatement(sqlCount);
			ResultSet countResultSet = prtmtCount.executeQuery();
			
			countResultSet.next();
			Page<User> page = new Page<User>(currentPage, pageSize, countResultSet.getInt(1));
			page.setData(userList);
			
			request.setAttribute("pageInfo", page);
			request.getRequestDispatcher("/user_list.jsp").forward(request, response);
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
