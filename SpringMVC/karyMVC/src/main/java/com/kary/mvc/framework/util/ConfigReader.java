package com.kary.mvc.framework.util;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kary.mvc.framework.interceptor.HandlerInterceptor;
import com.kary.mvc.framework.pojo.Config;

public class ConfigReader {
	public Config read(String path) {
		SAXReader reader = new SAXReader();
		try {
			Config config = new Config();
			Document document = reader.read(new File(path));
			Element root = document.getRootElement();
			for (Element e : root.elements("bean")) {
				Attribute id = e.attribute("id");
				if (id != null) {
					if (id.getText().equals("handlerAdapter")) {
						config.setHandlerAdapter(Class.forName(e.attributeValue("class")));
					} else if (id.getText().equals("viewResolver")) {
						config.setViewResolver(Class.forName(e.attributeValue("class")));
						for (Element i : e.elements("property")) {
							config.getViewResolverProperties().put(i.attributeValue("name"), i.attributeValue("value"));
						}
					} else if (id.getText().equals("handlerMapping")) {
						config.setHandlerMapping(Class.forName(e.attributeValue("class")));
						for (Element i : e.element("property").element("list").elements("ref")) {
							String intercepterId = i.attributeValue("bean");
							for (Element e1 : root.elements("bean")) {
								if (e1.attributeValue("id").equals(intercepterId)) {
									HandlerInterceptor interceptor = (HandlerInterceptor) Class.forName(e1.attributeValue("class"))
											.newInstance();
									config.getInterceptors().add(interceptor);
									break;
								}
							}
						}
					}
				} else {
					String handlerPpath = e.attributeValue("name");
					config.getHandlers().put(handlerPpath, Class.forName(e.attributeValue("class")));
				}
			}
			return config;
		} catch (DocumentException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
