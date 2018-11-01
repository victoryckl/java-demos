package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;

import dao.HeroDAO;

public class HeroListServlet extends HttpServlet {
	private static final long serialVersionUID = 5508837640475921891L;
	
	private HeroDAO dao = new HeroDAO();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		List<Hero> heros = dao.list();
		StringBuffer sb = new StringBuffer();
		sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
		sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td></tr>\r\n");
		
		String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%s</td></tr>\r\n";
		for (Hero hero : heros) {
			String tr = String.format(trFormat, hero.getId(), hero.getName(), hero.getHp(), hero.getDamage());
			sb.append(tr);
		}
		sb.append("</table>\r\n");
		resp.getWriter().write(sb.toString());
	}
}
