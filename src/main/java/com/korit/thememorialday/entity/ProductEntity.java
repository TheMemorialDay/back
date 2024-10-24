package com.korit.thememorialday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="Product")
@Table (name = "Product")
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productNumber;

    private String  productName;
    private String productIntroduce;
    private Integer productPrice;
    private Boolean productToday;
    private String productTag;

    // @ManyToOne
    // @JoinColumn(name = "store_number") // 외래 키 컬럼 이름
    // private Store store;
    
}
