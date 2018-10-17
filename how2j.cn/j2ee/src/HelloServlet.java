import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = -4439637135600892326L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.getWriter().println("<h1>Hello Servlet!</h1>");
			resp.getWriter().println(new Date().toLocaleString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
