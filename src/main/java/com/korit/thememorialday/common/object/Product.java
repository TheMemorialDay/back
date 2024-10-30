package com.korit.thememorialday.common.object;

import java.util.List;
import java.util.ArrayList;

import com.korit.thememorialday.entity.ProductEntity;

import lombok.Getter;

@Getter
public class Product {
  private Integer productNumber;
  private String productName;
  private String productIntroduce;
  private Integer productPrice;
  private boolean productToday;
  private String productTag;

  private Product(ProductEntity productEntity) {
    this.productNumber = productEntity.getProductNumber();
    this.productName = productEntity.getProductName();
    this.productIntroduce = productEntity.getProductIntroduce();
    this.productPrice = productEntity.getProductPrice();
    this.productToday = productEntity.isProductToday();
    this.productTag = productEntity.getProductTag();
  }

  public static List<Product> getList(List<ProductEntity> productEntities) {
    List<Product> products = new ArrayList<>();
    for (ProductEntity productEntity : productEntities) {
      Product product = new Product(productEntity);
      products.add(product);
    }
    return products;
  }
}