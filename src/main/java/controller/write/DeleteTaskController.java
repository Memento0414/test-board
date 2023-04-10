package controller.write;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.User;
import data.Write;

@WebServlet("/board/delete-task")
public class DeleteTaskController extends HttpServlet{
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
			     req.setCharacterEncoding("utf-8");
			     String id = req.getParameter("id");              //게시판 코드
			     String userPass = req.getParameter("userPass");  //비로그인시 비밀번호 확인
			     
			   
				SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
				SqlSession sqlSession = factory.openSession();
				
				Write found = sqlSession.selectOne("writes.findByWrite", id);
				User logonUser = (User)req.getSession().getAttribute("logonUser"); 
				
			
				if(found.getUserPass() == null || userPass == null) {
					if(found.getUserId().equals(logonUser.getId())) {
						sqlSession.delete("writes.deleteWrite", id);
						sqlSession.commit();
						sqlSession.close();
						resp.sendRedirect("/index");
						return;
					}
				} else if(found.getUserPass()!= null || userPass != null){
				
					if(found.getUserPass().equals(logonUser.getPass())) {
						sqlSession.delete("writes.deleteWrite", id);
						sqlSession.commit();
						sqlSession.close();
						resp.sendRedirect("/index");
						return;
					}
				}
				
				resp.sendRedirect("/board/delete?id="+id+"&error=1");
				return;
		}
}
