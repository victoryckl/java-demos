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
        System.out.println("LoginServlet ���췽�� ������");
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
		
		//ֱ�ӻظ�Html����
//		String html = null;
//		if ("admin".equals(name) && "123".equals(password)) {
//			html = "<div style='color:green'>��½�ɹ�</div>";
//		} else {
//			html = "<div style='color:red'>��½ʧ��</div>";
//		}
//		PrintWriter pw = resp.getWriter();
//		pw.println(html);
		
		if ("admin".equals(name) && "123".equals(password)) {
            req.getRequestDispatcher("success.html").forward(req, resp);//��������תҳ��
        } else {
        	resp.sendRedirect("fail.html");//�ͻ�����תҳ��
        }
	}
}
