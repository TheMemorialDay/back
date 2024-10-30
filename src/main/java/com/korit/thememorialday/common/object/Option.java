package com.korit.thememorialday.common.object;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    @NotBlank(message = "옵션 이름은 필수입니다.")
    private String productOptionName;

    @NotNull(message = "옵션 세부사항 리스트는 비어있을 수 없습니다.")
    private List<OptionDetail> optionDetails;
}
