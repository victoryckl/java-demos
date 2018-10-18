import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 3613835604998207857L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
 
        System.out.println("��ȡ��ֵ����name:" + request.getParameter("name"));
 
        String[] hobits = request.getParameterValues("hobits");
        System.out.println("��ȡ���ж�ֵ�Ĳ���hobits: " + Arrays.asList(hobits));
 
        System.out.println("ͨ�� getParameterMap �������еĲ����� ");
        Map<String, String[]> parameters = request.getParameterMap();
 
        Set<String> paramNames = parameters.keySet();
        for (String param : paramNames) {
            String[] value = parameters.get(param);
            System.out.println(param + ":" + Arrays.asList(value));
        }
        
        response.getWriter().println("ok");
    }
}