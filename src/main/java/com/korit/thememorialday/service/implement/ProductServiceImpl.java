// package com.korit.thememorialday.service.implement;

// import com.korit.thememorialday.common.object.Product;
// import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
// import com.korit.thememorialday.dto.request.product.PostProductOptionDetailRequestDto;
// import com.korit.thememorialday.dto.request.product.PostProductOptionRequestDto;
// import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
// import com.korit.thememorialday.dto.response.ResponseDto;
// import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
// import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
// import com.korit.thememorialday.entity.ProductEntity;
// import com.korit.thememorialday.entity.ProductImageEntity;
// import com.korit.thememorialday.entity.ProductMappingEntity;
// import com.korit.thememorialday.entity.ProductOptionEntity;
// import com.korit.thememorialday.entity.ThemaEntity;
// import com.korit.thememorialday.repository.ProductRepository;
// import com.korit.thememorialday.repository.StoreRepository;
// import com.korit.thememorialday.repository.ThemaRepostiroy;
// import com.korit.thememorialday.service.ProductService;

// import lombok.RequiredArgsConstructor;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;

// @Service
// @RequiredArgsConstructor
// public class ProductServiceImpl implements ProductService {

//     private final StoreRepository storeRepository;
//     private final ThemaRepostiroy themaRepostiroy;
//     private final ProductRepository productRepository;

//     @Override
//     public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, Integer storeNubmer) {
        
//         try {

//             boolean isExistStore = storeRepositor

//             ProductEntity productEntity = new ProductEntity(dto, storeNubmer);
//             productRepository.save(productEntity);

//         } catch (Exception exception) {
//             exception.printStackTrace();
//             return ResponseDto.databaseError();
//         }

//         return ResponseDto.success();

//     }

//     @Override
//     public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(Integer storeNumber) {
//     // 현재 로그인한 유저의 ID를 가져온다고 가정
//     String userId = ...; // 현재 로그인한 유저의 ID를 가져오는 방법
//     List<ProductEntity> products = productRepository.findByUserId(userId);
//     return GetProductListResponseDto.success(products);
//     }
    
//     @Override
//     public ResponseEntity<GetProductListResponseDto> getProductList(String userId) {
//         List<ProductEntity> products = productRepository.findByStoreUserId(userId);
        
//         if (products == null || products.isEmpty()) {
//             System.out.println("상품이 없습니다."); 
//         } else {
//             System.out.println("상품 수: " + products.size());
//         }
    
//         ResponseEntity<GetProductListResponseDto> response = GetProductListResponseDto.success(products);
//         return response;
//     }

//     @Override
//     public ResponseEntity<? super GetProductResponseDto> getProduct(Integer productNumber) {

//         ProductEntity productEntity = null;

//         try {
//             productEntity = productRepository.findByProductNumber(productNumber);
//             if(productEntity == null) return ResponseDto.noExistProduct();
            
//         } catch (Exception exception) {
//             exception.printStackTrace();
//             return ResponseDto.databaseError();
//         }
//         return GetProductResponseDto.success(productEntity);
//     }

//     @Override
//     public ResponseEntity<ResponseDto> patchProduct(Integer productNumber, PatchProductRequestDto dto) {
        
//         try {

//             ProductEntity productEntity = productRepository.findByProductNumber(productNumber);
//             if (productEntity == null) return ResponseDto.noExistProduct();
//             themaRepostiroy.deleteByProductNumber(productNumber);

//             productEntity.patch(dto);
//             productRepository.save(productEntity);

            
//         } catch (Exception exception) {
//             exception.printStackTrace();
//             return ResponseDto.databaseError();
//         }
//         return ResponseDto.success();
//     }
    

//     @Override
//     public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(Integer storeNumber) {
//         List<ProductEntity> products = productRepository.findByStoreNumber(storeNumber);
//         return GetProductListResponseDto.success(products);
//     }

//     @Override
//     public ResponseEntity<GetProductResponseDto> getProductByNumber(Integer productNumber) {
//         ProductEntity product = productRepository.findByProductNumber(productNumber);
//         if (product == null) {
//             return ResponseEntity.notFound().build(); // 상품이 없는 경우 404 반환
//         }
//         return GetProductResponseDto.success(product);
//     }
// }