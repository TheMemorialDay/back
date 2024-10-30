package com.korit.thememorialday.common.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

// 주문코드 날짜 8자리 + 랜덤 5자리 생성
public class OrderCodeCreator {

    public static String makeOrderCode() {
		String randomNumber = "";
        String orderCode = "";

		Random random = new Random();
		for (int count = 0; count < 5; count++) {
            randomNumber += random.nextInt(10);
        }
		
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = today.format(formatter);

        orderCode = formattedDate + randomNumber;

		return orderCode;
	}

    
}
