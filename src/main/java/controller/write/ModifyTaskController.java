package controller.write;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/board/modify-task")
public class ModifyTaskController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		
		
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");                 
		String content = req.getParameter("content"); 
		String userId = req.getParameter("userId"); 
		String userPass = req.getParameter("userPass"); 
		
		
		Map<String,Object> map = new HashMap<>();
		 map.put("id", id);
		 map.put("title", title);
		 map.put("content", content);
		 map.put("userId", userId);
		 map.put("userPass", userPass);
		
		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		
		sqlSession.update("writes.updateWriting", map);
		
		sqlSession.commit();
		sqlSession.close();
		
	
		resp.sendRedirect("/board/modify?id="+id);
		return;
	}
}
