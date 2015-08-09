package com.oocl.o2o.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadHelper {
	private DiskFileItemFactory factory;
	private List<FileItem> items;
	private final int MAX_CACHE_SIZE = 10240;

	public FileUploadHelper(HttpServletRequest request) {
		factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_CACHE_SIZE);
		try {
			ServletFileUpload upload = new ServletFileUpload(factory);
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				try {
					params.put(item.getFieldName(), item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return params;
	}

	public byte[] getFile() {
		for (FileItem item : items) {
			if (!item.isFormField() && item.getSize() > 0) {
				return item.get();
			}
		}
		return null;
	}

	public boolean hasFile() {
		for (FileItem item : items) {
			if (!item.isFormField() && item.getSize() > 0) {
				return true;
			}
		}
		return false;
	}

}
