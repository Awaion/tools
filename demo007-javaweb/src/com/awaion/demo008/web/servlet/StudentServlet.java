package com.awaion.demo008.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awaion.demo008.dao.IStudentDAO;
import com.awaion.demo008.dao.impl.StudentDAOImpl;
import com.awaion.demo008.domain.Student;

@WebServlet("/student/list")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStudentDAO dao = new StudentDAOImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// 接收请求参数
		// 调用业务方法处理请求
		List<Student> list = dao.getList();

		// 控制界面跳转
		// 在请求作用域中将list这个[数据]共享到request作用域中
		req.setAttribute("list", list);
		// 使用请求转发-->jsp页面
		req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(
				req, resp);
		

	}
}