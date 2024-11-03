package com.korit.thememorialday.dto.request.store;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# stores main search - 상품명으로 검색해서 가게들 불러오기 위한 dto

@Getter
@Setter
@NoArgsConstructor
public class PostStoreByProductMainSearchRequestDto {
	@NotBlank
	private String productName;
}
