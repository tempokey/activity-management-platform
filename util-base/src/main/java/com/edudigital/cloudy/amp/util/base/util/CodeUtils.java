package com.edudigital.cloudy.amp.util.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * @author Tempo
 * @date 2017年8月1日 下午1:14:15
 *
 */
public class CodeUtils {

	/**
	 * 数字样本
	 */
	private final static String numSample[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	/**
	 * 字母样本
	 */
	private final static String letterSample[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	/**
	 * 数字字母样本
	 */
	private final static String allSample[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z" };

	/**
	 * 随机产生随机数
	 * 
	 * @param num
	 *            随机数位数
	 * @param _sample
	 *            随机数样本编码 1纯数字 2纯英文 3数字英文混合
	 * @return
	 */
	public static String getResource(int _sample, int num) {
		String result = null;
		String sample[] = null;
		try {
			if (_sample == 1) {
				sample = numSample;
			} else if (_sample == 2) {
				sample = letterSample;
			} else {
				sample = allSample;
			}
			result = sample[random(sample)];
			for (int i = 1; i < num; i++) {
				result = result + sample[random(sample)];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 产生随机验证码
	 * 
	 * @param num
	 *            验证码位数
	 * @return
	 */
	public static String getResource(int num) {
		String result = null;
		String sample[] = allSample;
		try {
			result = sample[random(sample)];
			for (int i = 1; i < num; i++) {
				result = result + sample[random(sample)];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 随机产生数组坐标
	 * 
	 * @param _sample
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int random(String[] _sample) {
		int result = 0;
		try {
			result = (int) (Math.random() * (_sample.length));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * 生成DB数据ID
	 * 
	 * @return
	 */
	public static String randomUUID() {
		String str = null;
		UUID uuid = UUID.randomUUID();
		str = uuid.toString().replace("-", "");
		return str;
	}

	/**
	 * 条形码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public static void encodeBarCode(String contents, int width, int height, String imgPath) {
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		codeWidth = Math.max(codeWidth, width);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height,
					null);

			MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条形码解码
	 * 
	 * @param imgPath
	 * @return String
	 */
	public static String decodeBarCode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			result = new MultiFormatReader().decode(bitmap, null);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 二维码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public static void encodeQRCode(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码解码
	 * 
	 * @param imgPath
	 * @return String
	 */
	public static String decodeQRCode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "GBK");

			result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
