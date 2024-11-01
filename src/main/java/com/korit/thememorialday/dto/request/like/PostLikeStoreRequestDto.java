package com.korit.thememorialday.dto.request.like;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeStoreRequestDto {

  private String userId;
  private Integer storeNumber;
}
