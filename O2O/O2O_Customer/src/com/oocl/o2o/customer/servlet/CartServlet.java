package com.oocl.o2o.customer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oocl.o2o.pojo.Order;
import com.oocl.o2o.pojo.Packet;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if (act != null && !act.trim().equals("")) {
			ServletContext application = request.getServletContext();
			Order order = (Order) application.getAttribute("cart");
			if (order == null) {
				order = new Order();
				order.setFoodMap(new HashMap<Integer, Integer>());
			}

			Gson gson = new Gson();
			PrintWriter writer = response.getWriter();
			String json = null;
			switch (act) {
			case "addFood": {
				Integer id = Integer.parseInt(request.getParameter("id"));
				if (order.getFoodMap().get(id) == null) {
					order.getFoodMap().put(id, 1);
				} else {
					order.getFoodMap().put(id, order.getFoodMap().get(id) + 1);
				}
				Packet packet = new Packet();
				packet.setStatus("success");
				json = gson.toJson(packet);
				break;
			}
			case "removeFood": {
				Integer id = Integer.parseInt(request.getParameter("id"));

				break;
			}
			case "get": {
				json = gson.toJson(order);
				break;
			}
			default:
				break;
			}
			application.setAttribute("cart", order);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
