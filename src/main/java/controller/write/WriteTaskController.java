package controller.write;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;

@WebServlet("/board/write-task")
public class WriteTaskController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 혹은 비로그인에 따라 글쓰기에서 받는 정보를 처리하는 곳
		req.setCharacterEncoding("utf-8");
		SqlSessionFactory factory = (SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
		
		HttpSession session = req.getSession();
		User logonUser = (User)session.getAttribute("logonUser");
		
		String id = UUID.randomUUID().toString().split("-")[0]; // 게시글 고유id
		String title = req.getParameter("title");                 
		String content = req.getParameter("content");

		Map<String, Object> map = new HashMap<>(); 
		
		//로그인 상태에서 등록처리
		if(logonUser != null) {
			map.put("id", id);                     //게시글 고유 코드 
			map.put("userId", logonUser.getId()); 
			map.put("title", title);
			map.put("content", content);
			
			 sqlSession.insert("writes.create", map);
			resp.sendRedirect("/"); //등록시 인덱스로 보냄
			
			
		} else{
			//비로그인시 아이디와 비번을 받아서 받아서 처리, 나머진 위와 동일
	
			String userId = req.getParameter("userId");
			String userPass = req.getParameter("userPass");
			
			map.put("id", id);
			map.put("userId", userId);
			map.put("userPass", userPass);
			map.put("title", title);
			map.put("content", content);
			
			 sqlSession.insert("writes.noncreate", map);
			resp.sendRedirect("/"); //위와 같은 곳으로 보냄
			
		}
			sqlSession.commit();
			sqlSession.close();
			
		
		
	}
}
