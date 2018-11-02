package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import bean.Hero;

public class GetManyServlet extends HttpServlet {
	private static final long serialVersionUID = -9136827110328271396L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		List<Hero> heros = new ArrayList<Hero>();
		for (int i=0; i<10; i++) {
			Hero hero = new Hero();
			hero.setName("гЂал "+i);
			hero.setHp(500+i);
			hero.setDamage(300-i);
			heros.add(hero);
		}
		
		String result = JSONSerializer.toJSON(heros).toString();
		resp.getWriter().print(result);
	}
}
