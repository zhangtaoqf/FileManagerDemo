package com.zt.web.servelt;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long FILE_MAX_SIZE=10<<20;
    private String FILE_SAVE_PATH;
	public void init() throws ServletException {
		File file = new File(this.getServletContext().getRealPath("/mfile"));
		if(!file.exists())
		{
			file.mkdirs();
		}
		FILE_SAVE_PATH = file.getAbsolutePath();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		upload(request);
		response.setContentType("text/html/charset=UTF-8");
		//给客户机写数据，然后三秒钟之后跳转到首页
		response.addHeader("refresh", "3;url="+this.getServletContext().getContextPath());
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		outputStream.write("上传成功".getBytes());
		
	}

	private void upload(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		
		RequestContext req = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setFileSizeMax(FILE_MAX_SIZE);
			List<FileItem> items = new ArrayList<FileItem>();
			try {
				items = fileUpload.parseRequest(req);
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {
						System.out.println(fileItem.getFieldName() + " " + fileItem.getName() + " "
								+ fileItem.getString());
					} else {
						System.out.println(fileItem.getFieldName() + " " + fileItem.getName() + " " + fileItem.isInMemory()
								+ " " + fileItem.getContentType() + " " + fileItem.getSize());
						if (fileItem.getName() != null && fileItem.getSize() != 0) {
							File fullFile = new File(fileItem.getName());
							File newFile = new File(FILE_SAVE_PATH , System.currentTimeMillis()+fullFile.getName());
							try {
								fileItem.write(newFile);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							System.out.println("no file choosen or empty file");
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
