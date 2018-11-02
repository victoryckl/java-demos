package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;

import dao.HeroDAO;

public class HeroEditServlet extends HttpServlet {
	private static final long serialVersionUID = 5339469255601415723L;

	private HeroDAO dao = new HeroDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(req.getParameter("id"));
		Hero hero = dao.get(id);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\r\n");
		sb.append("<form action='updateHero' method='post'>\r\n");
		sb.append("名字：<input type='text' name='name' value='%s'><br>\r\n");
		sb.append("血量：<input type='text' name='hp' value='%f'><br>\r\n");
		sb.append("伤害：<input type='text' name='damage' value='%d'><br>\r\n");
		sb.append("<input type='hidden' name='id' value='%d'><br>\r\n");
		sb.append("<input type='submit' value='更新'>\r\n");
		sb.append("</form>");
		String html = String.format(sb.toString(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId());
		
		resp.getWriter().write(html);
	}
}
