package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.common.object.OrderProductDetail;
import com.korit.thememorialday.common.object.PreviewProduct;
import com.korit.thememorialday.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findByProductNumber(Integer productNumber);

    List<ProductEntity> findByStoreNumber(Integer storeNumber); // 특정 store_number에 해당하는 모든 상품 조회

    @Query(value = "SELECT " +
            "p.product_number, " +
            "p.product_name, " +
            "p.product_price, " +
            "p.product_today, " +
            "t.thema, " +
            "pi.product_image_url " +
            "FROM product AS p " +
            "LEFT JOIN thema AS t ON p.product_number = t.product_number " +
            "LEFT JOIN product_image AS pi ON p.product_number = pi.product_number " +
            "WHERE p.store_number = :storeNumber " +
            "ORDER BY p.product_number DESC", nativeQuery = true)
    List<PreviewProduct> findProductPreviewByStoreNumber(Integer storeNumber);

    @Query(value = "SELECT " +
            "p.product_number, " +
            "p.product_name, " +
            "p.product_introduce, " +
            "p.product_price, " +
            "p.product_today, " +
            "p.product_tag, " +
            "s.store_caution, " +
            "t.thema, " +
            "pi.product_image_url, " +
            "pm.product_option_name, " +
            "po.product_category, " +
            "po.product_option_price, " +
            "po.option_category_number, " +
            "po.option_number " +
            "FROM product AS p " +
            "JOIN store AS s ON p.store_number = s.store_number " +
            "LEFT JOIN thema AS t ON p.product_number = t.product_number " +
            "LEFT JOIN product_image AS pi ON p.product_number = pi.product_number " +
            "LEFT JOIN product_mapping AS pm ON p.product_number = pm.product_number " +
            "LEFT JOIN product_option AS po ON pm.option_number = po.option_number " +
            "WHERE p.product_number = :productNumber", nativeQuery = true)
    List<OrderProductDetail> findOrderProductDetailByProductNumber(Integer productNumber);

    @Query(value = "SELECT p.product_name " +
            "FROM product AS p " +
            "WHERE p.product_number = :productNumber", nativeQuery = true)
    String findProductNameByProductNumber(Integer productNumber);

    @Query(value = "SELECT pi.product_image_url " +
            "FROM product_image AS pi " +
            "WHERE pi.product_number = :productNumber " +
            "ORDER BY pi.product_image_url ASC " +
            "LIMIT 1", nativeQuery = true)
    String findFirstImageUrlByProductNumber(Integer productNumber);

    @Query(value = "SELECT po.product_category " +
            "FROM product_option AS po " +
            "WHERE po.option_category_number = :optionCategoryNumber", nativeQuery = true)
    String findProductCategoryByOptionCategoryNumber(@Param("optionCategoryNumber") Integer optionCategoryNumber);

}
