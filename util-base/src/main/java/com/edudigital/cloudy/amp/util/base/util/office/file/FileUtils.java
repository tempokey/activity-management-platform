package com.edudigital.cloudy.amp.util.base.util.office.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Tempo
 * @date 2017年7月18日 上午10:30:09
 *
 */
public class FileUtils {

	private final static String MY_CHARSET = "UTF-8";

	/**
	 * 
	 * @param fileName
	 * @param charset
	 * @return
	 */
	public static String readFile2Str(String fileName, String charset) {
		String str = "";
		String mySet = StringUtils.isNotBlank(charset) ? charset : MY_CHARSET;
		File file = new File(fileName);
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, mySet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return str;
	}

	public static String readFile2Str(String fileName) {
		return readFile2Str(fileName, MY_CHARSET);
	}

	public static String readFileBLine2Str(String fileName, String rule) {
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(fileName);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null) {
				if (Pattern.matches(rule, line)) {
					if (len != 0) // 处理换行符的问题
					{
						str.append("\r\n" + line);
					} else {
						str.append(line);
					}
					len++;
				}
			}
			in.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}
}
