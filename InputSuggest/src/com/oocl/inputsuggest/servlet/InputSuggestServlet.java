package com.oocl.inputsuggest.servlet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oocl.inputsuggest.dao.impl.AreaDao;
import com.oocl.inputsuggest.pojo.Area;

/**
 * Servlet implementation class InputSuggestServlet
 */
public class InputSuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InputSuggestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		Set<String> results = new HashSet<>();
		@SuppressWarnings("unchecked")
		Map<String, Set<String>> suggestMap = (Map<String, Set<String>>) application.getAttribute("suggestMap");
		if (suggestMap == null) {
			suggestMap = new HashMap<>();
		}

		String query = request.getParameter("query");
		if (query != null && query.length() > 0) {
			String queryHeader = query.substring(0, 1);
			if (suggestMap.containsKey(query)) {
				System.out.println("Seeking in query cache for '" + query + "'");
				results = suggestMap.get(query);
				if (query.length() != 1 && suggestMap.containsKey(queryHeader)) {
					suggestMap.get(queryHeader).addAll(results);
					suggestMap.remove(query);
				}
			} else if (suggestMap.containsKey(queryHeader)) {
				System.out.println("Seeking in query header cache for '" + query + "'");
				for (String str : suggestMap.get(queryHeader)) {
					if (str.startsWith(query)) {
						results.add(str);
					}
				}
			} else {
				System.out.println("Seeking in database for '" + query + "'");
				AreaDao dao = new AreaDao();
				List<Area> areas = dao.findAllByStartWith(query);
				if (areas.size() > 0) {
					for (Area a : areas) {
						results.add(a.getArea());
					}
					if (suggestMap.get(query) == null) {
						suggestMap.put(query, new HashSet<String>());
					}
					suggestMap.get(query).addAll(results);
				}
			}
			application.setAttribute("suggestMap", suggestMap);
		}

		Gson gson = new Gson();
		String json = gson.toJson(results);
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
		doGet(request, response);
	}

}
