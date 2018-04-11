package com.edudigital.cloudy.amp.util.base.util.office.doc.doc2h5.core;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.edudigital.cloudy.amp.util.base.util.office.doc.doc2h5.constant.ConfigConstant;

public class Docx {
	public static String readDoc(File file) {
		String str = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			str = doc.getDocumentText();
			// System.out.println(doc1);
			// StringBuilder doc2 = doc.getText();
			// System.out.println(doc2);
			// Range rang = doc.getRange();
			// String doc3 = rang.text();
			// System.out.println(doc3);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String readDocx(File file) {
		String str = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			XWPFDocument xdoc = new XWPFDocument(fis);
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			str = extractor.getText();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String readDocFile(String filePath) {
		StringUtils.isNotBlank(filePath);
		File file = new File(filePath);
		String tail = filePath.substring(filePath.lastIndexOf(".") + 1);
		String str = null;
		if (ConfigConstant.FILE_TYPE_DOCX.equals(tail)) {
			str = readDocx(file);
		} else if (ConfigConstant.FILE_TYPE_DOC.equals(tail)) {
			str = readDoc(file);
		}
		return str;
	}
}
