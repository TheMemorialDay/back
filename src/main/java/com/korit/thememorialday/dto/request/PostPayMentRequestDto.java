package com.korit.thememorialday.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPayMentRequestDto {

  @NotBlank
  private String orderCode;
  @NotBlank
  private String userId;
  @NotBlank
  private String success;
  @NotNull
  private Integer paidAmount;
}
