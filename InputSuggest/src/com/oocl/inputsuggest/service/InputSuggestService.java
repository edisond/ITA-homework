package com.oocl.inputsuggest.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oocl.inputsuggest.dao.impl.AreaDao;
import com.oocl.inputsuggest.pojo.Area;

public class InputSuggestService {
	public static Set<String> getResuts(HttpServletRequest request) {
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
		return results;
	}
}
