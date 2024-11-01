package com.korit.thememorialday.repository.resultSet;

import com.korit.thememorialday.entity.StoreEntity;
import java.util.List;

public interface GetProductResultSet {

    Integer getProductNumber();
    String getProductName();
    String getProductIntroduce();
    Integer getProductPrice();
    Boolean getProductToday();
    String getProductTag();
    List<String> getImages();
    List<String> getOptions();
    List<String> getThemes();
    StoreEntity getStore();
}
