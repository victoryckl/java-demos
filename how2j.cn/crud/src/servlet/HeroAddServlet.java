package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;

import dao.HeroDAO;

public class HeroAddServlet extends HttpServlet {
	private static final long serialVersionUID = 9046520987371027496L;
	
	private HeroDAO dao = new HeroDAO();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String name = req.getParameter("name");
		float hp = Float.parseFloat(req.getParameter("hp"));
		int damage = Integer.parseInt(req.getParameter("damage"));
		
		Hero hero = new Hero();
		hero.setName(name);
		hero.setHp(hp);
		hero.setDamage(damage);
		
		dao.add(hero);
		resp.sendRedirect("/crud/listHero");
	}
}
