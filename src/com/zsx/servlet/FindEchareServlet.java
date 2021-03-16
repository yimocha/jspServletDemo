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

import com.zsx.bean.User;
import com.zsx.utils.DBUtils;

/**
 * Servlet implementation class FindAllServlet
 */
@WebServlet("/findEchartServlet")
public class FindEchareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询所有用户
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = new ArrayList<User>(); // 创建一个list集合,用于装user集合
		Connection conn = DBUtils.open(); // 打开数据库连接
		String sql = " SELECT grades FROM `user` ";
		
		List<Integer> gradesList = new ArrayList<Integer>();

		try {
			PreparedStatement prtmt = conn.prepareStatement(sql);
			ResultSet rs = prtmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("grades");
				gradesList.add(id);
			}
			request.setAttribute("gradesList", gradesList);
			request.getRequestDispatcher("/user_echart.jsp").forward(request, response);
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
