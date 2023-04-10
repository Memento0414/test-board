package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import data.User;

@WebServlet("/user/login-task")
public class LoginTaskController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();

		// 이때 암호화를 따로 안하기 때문에 비밀번호도 체크를 해야함.
		User findUser = sqlSession.selectOne("users.findById", id);

		sqlSession.close();
		if (findUser != null) {

			if (findUser.getId().equals(id) &&pass.equals(findUser.getPass())) {
				HttpSession session = req.getSession();

				session.setAttribute("logon", true);
				session.setAttribute("logonUser", findUser);

				resp.sendRedirect("/");
				

			} else {
				resp.sendRedirect("/user/login?cause=error");

			}
		} else {
			resp.sendRedirect("/user/login?cause=error");
		}
		
		

	}
}
