package com.oocl.jee.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadTest
 */
public class UploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setRepository(new File(this.getServletContext().getRealPath("\\upload")));
		System.out.println(this.getServletContext().getRealPath(""));
		diskFileItemFactory.setSizeThreshold(10240);

		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

		List<FileItem> items;
		try {
			items = servletFileUpload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fn = item.getFieldName();
					String value = item.getString();
					System.out.println(fn + ": " + value);
				} else {
					String pic = item.getFieldName();
					System.out.println(pic);
					item.write(new File(this.getServletContext().getRealPath("\\upload\\xxxx.jpg")));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
