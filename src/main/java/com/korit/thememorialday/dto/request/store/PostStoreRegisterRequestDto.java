package com.korit.thememorialday.dto.request.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostStoreRegisterRequestDto {

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
  private String storeGugun;

  @NotBlank
  private String storeDong;

  @NotBlank
  private String storeLatitude;

  @NotBlank
  private String storeLongtitude;

  private String storeTel;

  @NotNull
  private Float storeRating;

  @NotNull
  private Integer reviewCount;

  @NotNull
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
