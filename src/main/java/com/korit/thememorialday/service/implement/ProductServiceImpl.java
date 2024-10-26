package com.korit.thememorialday.service.implement;

import com.korit.thememorialday.common.object.Product;
import com.korit.thememorialday.dto.request.product.PostProductOptionDetailRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductOptionRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.ProductImageEntity;
import com.korit.thememorialday.entity.ProductMappingEntity;
import com.korit.thememorialday.entity.ProductOptionEntity;
import com.korit.thememorialday.entity.ThemaEntity;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.glassfish.jaxb.core.annotation.OverrideAnnotationOf;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto) {
        try {
            // 상품 생성
            ProductEntity product = new ProductEntity();
            product.setProductName(dto.getProductName());
            product.setProductIntroduce(dto.getProductIntroduce());
            product.setProductPrice(dto.getProductPrice());
            product.setProductToday(dto.isProductToday());
            product.setProductTag(dto.getProductTag());

            // 이미지 설정
            List<String> imageUrls = dto.getProductImages();
            for (String imageUrl : imageUrls) {
                ProductImageEntity image = new ProductImageEntity();
                image.setProductImageUrl(imageUrl);
                image.setProduct(product);
                product.getImages().add(image);
            }

            // 옵션 설정
            List<PostProductOptionRequestDto> options = dto.getOptions();
            for (PostProductOptionRequestDto optionDto : options) {
                ProductMappingEntity mapping = new ProductMappingEntity();
                mapping.setProductOptionName(optionDto.getProductOptionName());
                mapping.setProduct(product);

                for (PostProductOptionDetailRequestDto detailDto : optionDto.getOptionDetails()) {
                    ProductOptionEntity option = new ProductOptionEntity();
                    option.setProductCategory(detailDto.getProductCategory());
                    option.setProductOptionPrice(detailDto.getProductOptionPrice());
                    option.setProductMapping(mapping);
                    mapping.getOptionDetails().add(option);
                }
                product.getOptions().add(mapping);
            }

            // 테마 설정
            List<String> themes = dto.getThemes();
            for (String theme : themes) {
                ThemaEntity thema = new ThemaEntity();
                thema.setThema(theme);
                thema.setProduct(product);
                product.getThemes().add(thema);
            }

            // 상품 저장
            productRepository.save(product);             // 원래 써놓은 부분: 에러 안남


            // // 상품 저장
            // ProductEntity savedProductEntity = productRepository.save(product);
            // // DTO로 변환
            // Product savedProduct = new Product(savedProductEntity);          저 윗줄 이랑 이 두줄 중에 뭐 쓸지 고민 중



        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // @Override
    // public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(Integer storeNumber) {
    // // 현재 로그인한 유저의 ID를 가져온다고 가정
    // String userId = ...; // 현재 로그인한 유저의 ID를 가져오는 방법
    // List<ProductEntity> products = productRepository.findByUserId(userId);
    // return GetProductListResponseDto.success(products);
    // }

    @Override
    public ResponseEntity<GetProductListResponseDto> getProductsByUserId(String userId) {
        List<ProductEntity> products = productRepository.findByStoreUserId(userId); // 조인된 쿼리 호출
        return GetProductListResponseDto.success(products);
    }

    // @Override
    // public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(Integer storeNumber) {
    //     List<ProductEntity> products = productRepository.findByStoreNumber(storeNumber);
    //     return GetProductListResponseDto.success(products);
    // }

    // @Override
    // public ResponseEntity<GetProductResponseDto> getProductByNumber(Integer productNumber) {
    //     ProductEntity product = productRepository.findByProductNumber(productNumber);
    //     if (product == null) {
    //         return ResponseEntity.notFound().build(); // 상품이 없는 경우 404 반환
    //     }
    //     return GetProductResponseDto.success(product);
    // }
}