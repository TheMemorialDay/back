package com.korit.thememorialday.dto.request.store;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# store main search - 가게명으로 검색 요청을 하기 위한 dto

@Getter
@Setter
@NoArgsConstructor
public class PostStoreMainSearchRequestDto {
	@NotBlank
	private String storeName;
}
