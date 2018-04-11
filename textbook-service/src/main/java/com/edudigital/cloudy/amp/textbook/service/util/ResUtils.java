package com.edudigital.cloudy.amp.textbook.service.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResUtils {
	private final static String CHARSET = "Charset";
	private final static String CHARSET_UTF_8 = "UTF-8";
	private final static String CONTENT_TYPE_TEXT = "text/html;charset=UTF-8";
	private final static String R_RETURN_KEY = "data";

	public static int toJson(HttpServletResponse response, JSONObject jsonObject) {
		if (response == null || jsonObject == null) {
			return 0;
		}
		try {
			response.setHeader(CHARSET, CHARSET_UTF_8);
			response.setContentType(CONTENT_TYPE_TEXT);
			PrintWriter out = response.getWriter();
			out.write(jsonObject.toString());
			return 1;
		} catch (IOException e) {
			return 2;
		}
	}

	public static int toJson(HttpServletResponse response, JSONArray jsonArray) {
		if (response == null || jsonArray == null) {
			return 0;
		}
		try {
			response.setHeader(CHARSET, CHARSET_UTF_8);
			response.setContentType(CONTENT_TYPE_TEXT);
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
			return 1;
		} catch (IOException e) {
			return 2;
		}
	}

	public static int toJsonPatched(HttpServletResponse response, JSONArray jsonArray) {
		if (response == null || jsonArray == null) {
			return 0;
		}
		try {
			response.setHeader(CHARSET, CHARSET_UTF_8);
			response.setContentType(CONTENT_TYPE_TEXT);
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(R_RETURN_KEY, jsonArray);
			out.write(jsonObject.toString());
			return 1;
		} catch (IOException e) {
			return 2;
		}
	}

	public static int toFile(HttpServletResponse response, Object object, String fileName) {
		if (ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(object) || StringUtils.isEmpty(fileName)) {
			return 0;
		}
		String str = object.toString();
		if (object instanceof JSONArray) {
			str = ((JSONArray) object).toString();
		} else if (object instanceof JSONObject) {
			str = ((JSONObject) object).toString();
		}
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			// response.addHeader("Content-Length", "" + object.toString().length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("multipart/form-data");
			toClient.write(str.getBytes());
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			return 2;
		}
		return 1;
	};

	public static int toFile(HttpServletResponse response, byte[] buffer, String fileName) {
		if (ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(buffer) || StringUtils.isEmpty(fileName)) {
			return 0;
		}
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + buffer.length);
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("multipart/form-data");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			return 2;
		}
		return 1;
	};
}
