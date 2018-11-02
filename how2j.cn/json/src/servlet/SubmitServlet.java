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
		System.out.println("服务端接收到的数据是："+data);
		
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("转换为JSON对象后是："+json);
		
		Hero hero = (Hero)JSONObject.toBean(json, Hero.class);
		System.out.println("转换为Hero对象后是："+hero);
	}
}
