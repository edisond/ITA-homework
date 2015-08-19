package com.oocl.o2o.customer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.oocl.o2o.dao.impl.FoodDao;
import com.oocl.o2o.dto.FoodDTO;
import com.oocl.o2o.util.Constants;
import com.oocl.o2o.util.Criteria;
import com.oocl.o2o.util.SearchCriteria;

/**
 * Servlet implementation class GetFoodServlet
 */
public class GetFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");

		FoodDao dao = new FoodDao();
		SearchCriteria criteria = new SearchCriteria();
		criteria.getCriteria().add(new Criteria("statusId", Constants.STATUS_DELETED, Criteria.NOT_EQUAL));
		criteria.getCriteria().add(new Criteria("statusId", Constants.STATUS_APPROVING, Criteria.NOT_EQUAL));
		if (type != null && !type.trim().equals("")) {
			criteria.getCriteria().add(new Criteria("foodtypeid", type, Criteria.EQUAL));
		}

		List<FoodDTO> list = dao.findAllDTOByCriteria(criteria);
		BASE64Encoder encoder = new BASE64Encoder();
		for (FoodDTO f : list) {
			if (f.getPictureBody() != null) {
				f.setPictureBodyBase64(encoder.encode(f.getPictureBody()));
				f.setPictureBody(null);
			} else {
				f.setPictureBodyBase64("");
			}

		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
