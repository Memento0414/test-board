package controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Write;

@WebServlet("/index")
public class IndexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SqlSessionFactory factory = (SqlSessionFactory) req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();

		// ================================================
		// 글 목록 및 페이징 처리
		int p;
		if (req.getParameter("page") == null) {
			p = 1;
		} else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		int total = sqlSession.selectOne("writes.countDatas"); // DB에 저장된 전체 글 개수
		int totalPage = total / 10 + (total % 10 > 0 ? 1 : 0); // 전체글 마지막 페이지 
		
		
		

		Map<String, Object> map = new HashMap<>(); 
		map.put("a", p * 10 - 9);
		map.put("b", 10 * p);

		int last = (int) Math.ceil(p / 5.0) * 5; // 화면 보여지는 페이지 끝 번호
		int start = last - 4; // 화면에 보여지는 페이지 시작 번호
		last = last > totalPage ? totalPage : last; // 마지막 페이지에서 넘어가지 못하게 막음.	
		
		List<Write> list = sqlSession.selectList("writes.findSomeByAtoB", map); 
		req.setAttribute("list", list);
		req.setAttribute("start", start);
		req.setAttribute("last", last);

		boolean existPrev = start > 1 ? true : false;
		boolean existNext = last < totalPage-1 ? true : false;
		int writeNum = p * 10;
		
		req.setAttribute("writeNum", writeNum);
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);

		sqlSession.close();
	
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		
		
	}

}
