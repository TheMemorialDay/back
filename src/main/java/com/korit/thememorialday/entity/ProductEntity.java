package com.korit.thememorialday.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductOptionRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productNumber;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_introduce")
    private String productIntroduce;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_today", nullable = false)
    private boolean productToday;

    @Column(name = "product_tag", nullable = false)
    private String productTag;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductMappingEntity> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ThemaEntity> themes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "store_number", nullable = false)
    private StoreEntity store;

    // DTO를 통한 생성자
    public ProductEntity(PostProductRequestDto dto) {
        this.productName = dto.getProductName();
        this.productIntroduce = dto.getProductIntroduce();
        this.productPrice = dto.getProductPrice();
        this.productToday = dto.isProductToday();
        this.productTag = dto.getProductTag();

        // 이미지 URL 리스트를 ProductImageEntity 리스트로 변환
        if (dto.getProductImages() != null) {
            for (String imageUrl : dto.getProductImages()) {
                this.images.add(new ProductImageEntity(imageUrl, this));
            }
        }

        // 옵션 리스트를 ProductMappingEntity 리스트로 변환
        if (dto.getOptions() != null) {
            for (PostProductOptionRequestDto option : dto.getOptions()) {
                this.options.add(new ProductMappingEntity(option, this));
            }
        }

        // 테마 리스트를 ThemaEntity 리스트로 변환
        if (dto.getThemes() != null) {
            for (String theme : dto.getThemes()) {
                this.themes.add(new ThemaEntity(theme, this));
            }
        }
    }

    public void patch(PatchProductRequestDto dto) {
        if (dto.getProductName() != null) this.productName = dto.getProductName();
        if (dto.getProductIntroduce() != null) this.productIntroduce = dto.getProductIntroduce();
        if (dto.getProductPrice() != null) this.productPrice = dto.getProductPrice();
        this.productToday = dto.isProductToday();
        if (dto.getProductTag() != null) this.productTag = dto.getProductTag();

        // 기존 리스트 지우고 새로운 리스트로 대체
        if (dto.getOptions() != null) {
            this.options.clear();
            for (PostProductOptionRequestDto option : dto.getOptions()) {
                this.options.add(new ProductMappingEntity(option, this));
            }
        }

        if (dto.getProductImages() != null) {
            this.images.clear();
            for (String imageUrl : dto.getProductImages()) {
                this.images.add(new ProductImageEntity(imageUrl, this));
            }
        }

        if (dto.getThemes() != null) {
            this.themes.clear();
            for (String theme : dto.getThemes()) {
                this.themes.add(new ThemaEntity(theme, this));
            }
        }
    }
}
