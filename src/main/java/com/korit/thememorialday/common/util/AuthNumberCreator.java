package com.korit.thememorialday.common.util;

import java.util.Random;

//# 인증번호 4자리 생성

public class AuthNumberCreator {
	public static String number4() {
		String telAuthNumber = "";

		Random random = new Random();
		for (int count = 0; count < 4; count++)
		telAuthNumber += random.nextInt(10);

		return telAuthNumber;
	}
}
