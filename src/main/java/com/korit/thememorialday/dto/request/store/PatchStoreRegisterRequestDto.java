package com.korit.thememorialday.dto.request.store;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchStoreRegisterRequestDto {

  @NotBlank
  private String userId;

  @NotBlank
  private String storeName;

  private String storeIntroduce;

  private String storeParticular;

  private String storeContact;

  private String storeCaution;

  @NotBlank
  private String storeAddress;

  @NotBlank
  private String storeDetailAddress;

  @NotBlank
  private String storeGugun;

  @NotBlank
  private String storeDong;

  @NotBlank
  private String storeLatitude;

  @NotBlank
  private String storeLongtitude;

  private String storeTel;

  private Float storeRating;

  private Integer reviewCount;

  private Integer likeCount;

  @NotBlank
  private String storeImageUrl;

  private String mondayOpen;

  private String mondayLast;

  private String tuesdayOpen;

  private String tuesdayLast;

  private String wednesdayOpen;

  private String wednesdayLast;

  private String thursdayOpen;

  private String thursdayLast;

  private String fridayOpen;

  private String fridayLast;

  private String saturdayOpen;

  private String saturdayLast;

  private String sundayOpen;

  private String sundayLast;
}
