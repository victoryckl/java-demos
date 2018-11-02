package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import bean.Hero;

public class GetOneServlet extends HttpServlet {
	private static final long serialVersionUID = 6632112112669143490L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Hero hero = new Hero();
		hero.setName("¸ÇÂ×");
		hero.setHp(562);
		hero.setDamage(326);
		
		JSONObject json = new JSONObject();
		json.put("hero", JSONObject.fromObject(hero));

		resp.getWriter().println(json);
	}
}
