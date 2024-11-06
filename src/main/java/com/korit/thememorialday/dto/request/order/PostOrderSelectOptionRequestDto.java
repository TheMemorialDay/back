package com.korit.thememorialday.dto.request.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderSelectOptionRequestDto {
    
    @NotNull(message = "옵션 번호는 필수입니다.")
    private Integer optionNumber;

    // private Integer orderCategoryNumber;      // optionNumber 안 받기로 해당 줄에서 지웠습니다
}
