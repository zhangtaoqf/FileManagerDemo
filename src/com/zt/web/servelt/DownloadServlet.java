package com.zt.web.servelt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get请求是无法对请求数据进行编码的
		//request.setCharacterEncoding("UTF-8");//这种方式只能用在post请求中
		String filename = request.getParameter("filename");
		//让数据以UTF-8的形式显示
		String fn = new String(filename.getBytes("ISO8859-1"),"UTF-8");
		
		System.out.println(fn);
		
		File file = new File(this.getServletContext().getRealPath("/mfile"),fn);
		
		FileInputStream fileInputStream = new FileInputStream(file);
		
		response.setHeader("content-type", "application/force-download;charset=UTF-8");
		
		response.addHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
		
		response.addHeader("content-length", file.length()+"");
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		int len=-1;
		
		byte[] bs = new byte[1024];
		
		while((len = fileInputStream.read(bs))>0)
		{
			outputStream.write(bs, 0, len);
		}
		
		fileInputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
