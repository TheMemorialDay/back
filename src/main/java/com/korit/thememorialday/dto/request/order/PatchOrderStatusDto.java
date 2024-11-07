package com.korit.thememorialday.dto.request.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchOrderStatusDto {

  private String orderCode;
  private String orderStatus;
  private String cancelCode;
  private String cancelReason;
}
