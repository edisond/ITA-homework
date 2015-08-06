package com.oocl.o2o.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodPackageDaoImpl;
import com.oocl.o2o.dao.impl.PackageDaoImpl;
import com.oocl.o2o.pojo.Package;

/**
 * Servlet implementation class DeletePackageServlet
 */
public class DeletePackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PackageDaoImpl daoImpl = new PackageDaoImpl();
		FoodPackageDaoImpl daoImpl2 = new FoodPackageDaoImpl();
		Package pkg = new Package();
		pkg.setPackageId(id);
		daoImpl.deletePackage(pkg);
		daoImpl2.deleteByPackageId(id);
		response.sendRedirect("/O2O_Seller/main/package.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
