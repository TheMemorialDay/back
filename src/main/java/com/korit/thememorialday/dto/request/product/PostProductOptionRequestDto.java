package com.korit.thememorialday.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostProductOptionRequestDto {
    @NotBlank(message = "옵션 이름은 필수입니다.")
    private String productOptionName;

    @NotNull(message = "옵션 세부사항 리스트는 비어있을 수 없습니다.")
    private List<PostProductOptionDetailRequestDto> optionDetails;
}