package controller.write;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Like;
import data.User;
import data.Write;

@WebServlet("/board/detail")
public class WriteDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		SqlSessionFactory factory=(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();

		// index.jsp에서 a태그로 게시글아이디(코드)를 파라미터로 받아야 할 것
		String id = req.getParameter("id");
		
			
		
		// 로그인과 비로그인 상태 구현
		HttpSession session = req.getSession();
		boolean logon = (boolean) session.getAttribute("logon");
		System.out.println("logon ===" +logon);
		
		if(logon) {
			sqlSession.update("writes.likeUp", id);
		}
		
		//디테일로 이동했을때 조회수 증가
		sqlSession.update("writes.viewsCountUp", id);
			
		User logonUser = (User)session.getAttribute("logonUser");
		Write found = sqlSession.selectOne("writes.findByWrite", id);
		req.setAttribute("findWriting", found);
		
		
		List<Like> list = sqlSession.selectList("findByLikeId", id);
		boolean likeId =false;
		if(logonUser != null) {
			for(Like i : list ) {
				if(i.getId().equals(logonUser.getId())) {
					
					 likeId = true; 
					 sqlSession.insert("likeUp", list);
				}
				
			}
			
		}
		req.setAttribute("like", list);
		req.setAttribute("likeCnt", list.size());
		req.setAttribute("likeId", likeId);
	
		sqlSession.commit();
		sqlSession.close();
		
		
		
		req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(req, resp);
	}
}
