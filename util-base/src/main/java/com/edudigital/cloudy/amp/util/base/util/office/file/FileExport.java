package com.edudigital.cloudy.amp.util.base.util.office.file;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.edudigital.cloudy.amp.util.base.util.office.doc.doc2h5.core.Docx;
import com.edudigital.cloudy.amp.util.base.util.office.doc.doc2h5.core.Docx2H5;
import com.edudigital.cloudy.amp.util.base.util.office.excel.h52excel.H52Excel;

public class FileExport {

	private final static String KEY = "Data";

	public static int creFile(String oriFile, String destPath, String destFile, String rule, String splitSentence) {
		if (0 == Docx2H5.word2Html(oriFile, destPath, destFile)) {
			return 0;
		}
		String str = FileUtils.readFileBLine2Str(destPath + destFile, rule);
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		String[] strs = str.split(splitSentence);

		String[] title = new String[1];
		title[0] = KEY;

		String[][] data = new String[strs.length][1];
		for (int i = 0; i < strs.length; i++) {
			data[i] = new String[] { strs[i] };
		}

		HSSFWorkbook wb = H52Excel.creExcel(title, data);

		return H52Excel.exportExcel(destPath + destFile, wb);
	}

	public static int creFileByWord(String filePath, String destPath, String destFile, String splitSentence) {
		String str = Docx.readDocFile(filePath);
		String[] strs = str.split(splitSentence);

		String[] title = new String[1];
		title[0] = KEY;

		String[][] data = new String[strs.length][1];
		for (int i = 0; i < strs.length; i++) {
			data[i] = new String[] { strs[i] };
		}

		HSSFWorkbook wb = H52Excel.creExcel(title, data);

		return H52Excel.exportExcel(destPath + destFile, wb);
	}
}
