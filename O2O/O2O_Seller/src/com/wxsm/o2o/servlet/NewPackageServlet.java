package com.wxsm.o2o.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.o2o.dao.impl.FoodPackageDao;
import com.oocl.o2o.dao.impl.PackageDao;
import com.oocl.o2o.pojo.FoodPackage;
import com.oocl.o2o.pojo.Package;
import com.oocl.o2o.pojo.User;
import com.oocl.o2o.util.Constants;
import com.wxsm.o2o.util.JmsUtil;

/**
 * Servlet implementation class NewPackageServlet
 */
public class NewPackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String[] foods = request.getParameter("foods").split(",");
		String price = request.getParameter("price");

		Package pack = new Package();
		User user = (User) request.getSession().getAttribute("user");

		pack.setPackageName(name);
		pack.setPrice(Double.parseDouble(price));
		pack.setStatusId(Constants.STATUS_APPROVING);
		pack.setUserId(user.getUserId());

		PackageDao packageDao = new PackageDao();
		FoodPackageDao foodPackageDao = new FoodPackageDao();

		Integer packId = packageDao.insert(pack);

		for (String f : foods) {
			try {
				Integer foodId = Integer.parseInt(f);
				FoodPackage foodPackage = new FoodPackage(foodId, packId);
				foodPackageDao.insert(foodPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect("/O2O_Seller/main/package.jsp");
		JmsUtil.produce("msg");
	}

}
