package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;

import dao.HeroDAO;

public class HeroUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 6517386720656183440L;

	private HeroDAO dao = new HeroDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Hero hero = new Hero();
		hero.setId(Integer.parseInt(req.getParameter("id")));
		hero.setName(req.getParameter("name"));
		hero.setHp(Float.parseFloat(req.getParameter("hp")));
		hero.setDamage(Integer.parseInt(req.getParameter("damage")));
		
		dao.update(hero);
		resp.sendRedirect("/crud/listHero");
	}
}
