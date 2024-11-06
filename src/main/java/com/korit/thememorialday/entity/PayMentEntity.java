package com.korit.thememorialday.entity;

import com.korit.thememorialday.dto.request.PostPayMentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
@Table(name = "payment")
public class PayMentEntity {

  @Id
  private String orderCode;
  private String userId;
  private String success;
  private Integer paidAmount;

  public PayMentEntity(PostPayMentRequestDto dto) {
    this.orderCode = dto.getOrderCode();
    this.userId = dto.getUserId();
    this.success = dto.getSuccess();
    this.paidAmount = dto.getPaidAmount();
  }
}
