package com.korit.thememorialday.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

//# Cool SMS 메세지 전송 제공자

@Component
public class SmsProvider {
	private final DefaultMessageService messageService;
	private final String from;

	public SmsProvider(
		@Value("${cool-sms.api-key}") String apiKey,
		@Value("${cool-sms.secret-key}") String apiSecretKey,
		@Value("${cool-sms.domain}") String domain,
		@Value("${cool-sms.from}") String from
	) {
		this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, domain);
		this.from = from;
	}

	public boolean sendMessage(String to, String telAuthNumber) {
		Message message = new Message();
		message.setFrom(from);
		message.setTo(to);
		message.setText("TheMemorialDay 인증 번호 [" + telAuthNumber + "] 를 정확히 입력해주세요.");

		SingleMessageSendingRequest request = new SingleMessageSendingRequest(message);
		SingleMessageSentResponse response = messageService.sendOne(request);

		boolean resultStatus = response.getStatusCode().equals("2000");
		return resultStatus;
	}

	public boolean sendPaymentMsg(String name, String storeName, String telNumber, Integer totalPrice, String productName) {
		Message message = new Message();
		message.setFrom(from);
		message.setTo(telNumber);

		String contents = String.format("[The Memorial Day]\n%s님의 %s - %s 주문이 승인되었습니다.\n홈페이지에서 로그인 후 마이페이지 - 주문 내역에서 %d원 결제 부탁드립니다. 24시간 이내에 결제가 이루어지지 않을 시, 자동 주문 취소됩니다.", 
		name, storeName, productName, totalPrice);
		message.setText(contents);

		SingleMessageSendingRequest request = new SingleMessageSendingRequest(message);
		SingleMessageSentResponse response = messageService.sendOne(request);

		boolean resultStatus = response.getStatusCode().equals("2000");
		return resultStatus;
	}
}
