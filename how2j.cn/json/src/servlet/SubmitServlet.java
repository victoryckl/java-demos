package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;

import net.sf.json.JSONObject;

public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = -6918037386944348721L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String data = req.getParameter("data");
		System.out.println("����˽��յ��������ǣ�"+data);
		
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("ת��ΪJSON������ǣ�"+json);
		
		Hero hero = (Hero)JSONObject.toBean(json, Hero.class);
		System.out.println("ת��ΪHero������ǣ�"+hero);
	}
}
