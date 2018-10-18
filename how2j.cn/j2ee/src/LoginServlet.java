import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 9211568140687018262L;
	
	public LoginServlet(){
        System.out.println("LoginServlet 构造方法 被调用");
    }
	
	@Override
	public void init(ServletConfig config) {
        System.out.println("init(ServletConfig)");
    }
	
	public void destroy() {
        System.out.println("destroy() 333");
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		System.out.println("name:"+name);
		System.out.println("password:"+password);
		
		//直接回复Html内容
//		String html = null;
//		if ("admin".equals(name) && "123".equals(password)) {
//			html = "<div style='color:green'>登陆成功</div>";
//		} else {
//			html = "<div style='color:red'>登陆失败</div>";
//		}
//		PrintWriter pw = resp.getWriter();
//		pw.println(html);
		
		if ("admin".equals(name) && "123".equals(password)) {
            req.getRequestDispatcher("success.html").forward(req, resp);//服务器跳转页面
        } else {
        	resp.sendRedirect("fail.html");//客户端跳转页面
        }
	}
}
