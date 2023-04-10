package controller.write;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Write;

@WebServlet("/board/modify")
public class ModifyController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		Write found = sqlSession.selectOne("writes.findByWriting", id);
		
		sqlSession.commit();
		sqlSession.close();
		
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp?id="+id).forward(req, resp);
		
		
		
	}
	
}
