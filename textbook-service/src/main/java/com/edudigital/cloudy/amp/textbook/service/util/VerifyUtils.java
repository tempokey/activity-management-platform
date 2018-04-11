package com.edudigital.cloudy.amp.textbook.service.util;

/****
 * 验证工具公共类
 * 
 * @author ys
 *
 */
public class VerifyUtils {

	public static Boolean checkStrLength(String str, Integer min, Integer max) {

		return (!str.isEmpty() && str.length() >= min && str.length() <= max);
	}

	public static int numberCount(String s) {
		// 检测该密码有几位数字
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)))
				count++;
		}
		return count;
	}

	public static boolean testWord(String s) {
		// 判断该密码是否仅有字母和数字组成
		boolean password = false;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))
				password = true;
			else {
				password = false;
				break;
			}
		}
		return password;
	}

	public static void testPassword(String s) {
		// 校验密码是否符合规则
		if (numberCount(s) >= 2 && s.length() >= 8 && s.length() <= 16 && testWord(s))
			System.out.println("valid password");
		else
			System.out.println("Invalid password");
	}
}
