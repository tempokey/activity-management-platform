package com.edudigital.cloudy.amp.util.base.util.office.excel.h52excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.edudigital.cloudy.amp.util.base.util.office.file.FileUtils;

public class H52Excel {
	public static List<byte[]> readFile2Bytes(String path) throws IOException {
		File file = new File(path);
		List<byte[]> byteList = null;
		if (file.exists()) {
			@SuppressWarnings("resource")
			FileInputStream fileImputStream = new FileInputStream(file);

			byte[] bytes = new byte[1024];
			int flag = 0;

			List<Atom> atomList = new ArrayList<Atom>();
			Atom a = new Atom();
			while (flag != -1) {
				flag = fileImputStream.read(bytes);
				for (int i = 0; i < bytes.length; i++) {
					byte b = bytes[i];
					if (b == 42) {
						byte[] b1 = Arrays.copyOf(bytes, i);
						byte[] b2 = Arrays.copyOfRange(bytes, i + 1, bytes.length);
						a.getByteQueue().add(b1);
						atomList.add(a);
						a = new Atom();
						a.getByteQueue().add(b2);
					} else {
						a.getByteQueue().add(bytes);
					}
				}
			}

			byteList = new ArrayList<>();
			for (int i = 0; i < atomList.size(); i++) {
				byte[] t = atomList.get(i).getByteQueue().poll();
				if (!atomList.get(i).getByteQueue().isEmpty()) {
					while (atomList.get(i).getByteQueue().isEmpty()) {
						t = addBytes(t, atomList.get(i).getByteQueue().poll());
					}
				}
				byteList.add(t);
			}
		}
		return byteList;
	}

	/**
	 * 
	 * @param data1
	 * @param data2
	 * @return data1 与 data2拼接的结果
	 */
	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];
		System.arraycopy(data1, 0, data3, 0, data1.length);
		System.arraycopy(data2, 0, data3, data1.length, data2.length);
		return data3;

	}

	public static HSSFWorkbook creExcel(String[] title, String[][] data) {
		// 第一步创建webbook
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，添加一个sheet
		HSSFSheet sheet = wb.createSheet("文件拆分");
		// 第三步，添加表头第0行
		HSSFRow row = sheet.createRow(0);

		// 第四步，设置居中格式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 第五步，填写单元格数据
		// 正确流程 创建excel
		// 创建基础数据
		HSSFCell cell = row.createCell(0);
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}

		for (int i = 0; i < data.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < data[i].length; j++) {
				String str = data[i][j];
				// if (str.length() > 4000) {
				// List<String> list = new ArrayList<String>();
				// list = split4k(list, str);
				// for (int k = 0; k < list.size(); k++) {
				// cell = row.createCell(k);
				// cell.setCellValue(list.get(k));
				// }
				// } else {
				cell = row.createCell(j);
				cell.setCellValue(str);
				// }
			}
		}

		return wb;
	}

	public static List<String> split4k(List<String> list, String str) {
		if (str.length() > 4000) {
			list.add(str.substring(0, 4000));
			split4k(list, str.substring(4000));
		} else {
			list.add(str);
		}
		return list;
	}

	public static int exportExcel(String destFile, HSSFWorkbook wb) {
		try {
			FileOutputStream fout = new FileOutputStream(destFile.replace("html", "xls"));
			wb.write(fout);
			fout.close();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public static void main(String[] args) {
		String str = FileUtils.readFile2Str("F:/1.html");
		System.out.println(str);
	}
}
