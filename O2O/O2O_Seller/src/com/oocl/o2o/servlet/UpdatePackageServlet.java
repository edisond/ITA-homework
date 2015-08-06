package com.oocl.o2o.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodPackageDaoImpl;
import com.oocl.o2o.dao.impl.PackageDaoImpl;
import com.oocl.o2o.pojo.FoodPackage;
import com.oocl.o2o.pojo.Package;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;

/**
 * Servlet implementation class UpdatePackageServlet
 */
public class UpdatePackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePackageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PackageDaoImpl daoImpl = new PackageDaoImpl();
		Package pkg = daoImpl.getById(id);

		FoodPackageDaoImpl foodPackageDaoImpl = new FoodPackageDaoImpl();
		List<FoodPackage> foodPackages = foodPackageDaoImpl.findByPackageId(pkg.getPackageId());

		request.setAttribute("pkg", pkg);
		request.setAttribute("fpkgs", foodPackages);
		request.getRequestDispatcher("/main/updatePackage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String[] foods = request.getParameter("foods").split(",");
		String price = request.getParameter("price");

		Package pack = new Package();
		User user = (User) request.getSession().getAttribute("user");

		pack.setPackageId(id);
		pack.setPackageName(name);
		pack.setPrice(Double.parseDouble(price));
		pack.setStatusId(Constants.STSTUS_APPROVING);
		pack.setUserId(user.getUserId());

		PackageDaoImpl packageDaoImpl = new PackageDaoImpl();
		FoodPackageDaoImpl foodPackageDaoImpl = new FoodPackageDaoImpl();

		packageDaoImpl.updatePackage(pack);
		foodPackageDaoImpl.deleteByPackageId(pack.getPackageId());
		for (String f : foods) {
			try{
				Integer foodId = Integer.parseInt(f);
				FoodPackage foodPackage = new FoodPackage(foodId, pack.getPackageId());
				foodPackageDaoImpl.add(foodPackage);
			} catch(Exception e){
				e.printStackTrace();
			}
		}

		response.sendRedirect("/O2O_Seller/main/package.jsp");
	}

}
