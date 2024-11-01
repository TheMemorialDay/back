package com.korit.thememorialday.entity;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.entity.pk.LikePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "like")
@IdClass(LikePk.class)
@Table(name = "`like`")
public class LikeEntity {

  @Id
  private String userId;

  @Id
  private Integer storeNumber;

  public LikeEntity(PostLikeStoreRequestDto dto) {
    this.userId = dto.getUserId();
    this.storeNumber = dto.getStoreNumber();
  }

}
