package com.korit.thememorialday.entity.pk;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikePk implements Serializable {
  private String userId;
  private Integer storeNumber;
}
