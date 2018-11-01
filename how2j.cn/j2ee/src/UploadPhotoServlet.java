import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadPhotoServlet extends HttpServlet {

	private static final long serialVersionUID = -3461788651837277866L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter pw= resp.getWriter();
		String filename = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			factory.setSizeThreshold(1024*1024);//设置上传文件大侠你现在为1MB
			
			List items = null;
			try {
				items = upload.parseRequest(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Iterator iter = items.iterator();
			while(iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					//根据时间戳创建头像文件
					filename = System.currentTimeMillis()+".jpg";
					//通过getRealPath获取上传文件夹，如果项目在e:/project/j2ee/web,那么获取到路径是e:/project/j2ee/web/uploaded
					String photoFolder = req.getServletContext().getRealPath("uploaded");
					File f = new File(photoFolder, filename);
					f.getParentFile().mkdirs();
					//通过item.getInputStream()获取浏览器上传的文件输入流
					InputStream is = item.getInputStream();
					//复制文件
					FileOutputStream fos = new FileOutputStream(f);
					byte[] b = new byte[1024*1024];
					int length = 0;
					while (-1 != (length = is.read(b))) {
						fos.write(b, 0, length);
					}
					fos.close();
				} else {
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println(item.getFieldName()+":"+value);
				}
			}
			
			String html = "<img width='200' height='150' src='uploaded/%s' />";
			pw.format(html, filename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
