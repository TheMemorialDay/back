package com.korit.thememorialday.service.implement;

import com.korit.thememorialday.common.object.FullProduct;
import com.korit.thememorialday.common.object.Option;
import com.korit.thememorialday.common.object.OptionDetail;
import com.korit.thememorialday.common.object.OrderProductDetail;
import com.korit.thememorialday.common.object.PreviewProduct;
import com.korit.thememorialday.common.object.Product;
import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
import com.korit.thememorialday.dto.response.store.GetProductDetailResponseDto;
import com.korit.thememorialday.dto.response.store.GetProductPreviewListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.ProductImageEntity;
import com.korit.thememorialday.entity.ProductMappingEntity;
import com.korit.thememorialday.entity.ProductOptionEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.entity.ThemaEntity;
import com.korit.thememorialday.repository.ProductImageRepository;
import com.korit.thememorialday.repository.ProductMappingRepository;
import com.korit.thememorialday.repository.ProductOptionRepository;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.ThemaRepostiroy;
import com.korit.thememorialday.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final StoreRepository storeRepository;
    private final ThemaRepostiroy themaRepostiroy;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductMappingRepository productMappingRepository;

    @Override
    public ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, Integer storeNubmer) {
        
        try {

            boolean isExistStore = storeRepository.existsById(storeNubmer);
            if (!isExistStore) return ResponseDto.noExistStore();

            ProductEntity productEntity = new ProductEntity(dto, storeNubmer);
            productRepository.save(productEntity);
            Integer productNumber = productEntity.getProductNumber();

            List<String> productImages = dto.getProductImages();
            List<ProductImageEntity> productImageEntities = new ArrayList<>();
            for (String productImage: productImages) {
                ProductImageEntity productImageEntity = new ProductImageEntity(productNumber, productImage);
                productImageEntities.add(productImageEntity);
            }
            productImageRepository.saveAll(productImageEntities);

            List<String> themes = dto.getThemes();
            if (themes != null) {
                List<ThemaEntity> themaEntities = new ArrayList<>();
                for (String theme: themes) {
                    ThemaEntity themaEntity = new ThemaEntity(theme, productNumber);
                    themaEntities.add(themaEntity);
                }
                themaRepostiroy.saveAll(themaEntities);
            }

            List<Option> options = dto.getOptions();
            for (Option option: options) {
                ProductMappingEntity productMappingEntity = new ProductMappingEntity(productNumber, option.getProductOptionName());
                productMappingRepository.save(productMappingEntity);
                Integer optionNumber = productMappingEntity.getOptionNumber();

                List<OptionDetail> optionDetails = option.getOptionDetails();
                List<ProductOptionEntity> productOptionEntities = new ArrayList<>();
                for (OptionDetail optionDetail: optionDetails) {
                    ProductOptionEntity productOptionEntity = new ProductOptionEntity(optionNumber, optionDetail);
                    productOptionEntities.add(productOptionEntity);
                }
                productOptionRepository.saveAll(productOptionEntities);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
    @Override
    public ResponseEntity<? super GetProductListResponseDto> getProductList(String userId) {

        List<FullProduct> products = new ArrayList<>();

        try {

            StoreEntity storeEntity = storeRepository.findByUserId(userId);
            if (storeEntity == null) return ResponseDto.noExistStore();

            Integer storeNumber = storeEntity.getStoreNumber();
            List<ProductEntity> productEntities = productRepository.findByStoreNumber(storeNumber);

            for (ProductEntity productEntity: productEntities) {
                Integer productNumber = productEntity.getProductNumber();
                List<ProductImageEntity> productImageEntities = productImageRepository.findByProductNumber(productNumber);
                List<ThemaEntity> themaEntities = themaRepostiroy.findByProductNumber(productNumber);
                List<ProductMappingEntity> productMappingEntities = productMappingRepository.findByProductNumber(productNumber);

                // description: 이미지 리스트 생성 //
                List<String> productImages = new ArrayList<>();
                for (ProductImageEntity productImageEntity: productImageEntities) productImages.add(productImageEntity.getProductImageUrl());

                // description: 테마 리스트 생성 //
                List<String> themes = new ArrayList<>();
                for (ThemaEntity themaEntity: themaEntities) themes.add(themaEntity.getThema());

                // description: 옵션 리스트 생성 //
                List<Option> options = new ArrayList<>();
                for (ProductMappingEntity productMappingEntity: productMappingEntities) {

                    Integer optionNumber = productMappingEntity.getOptionNumber();
                    String productOptionName = productMappingEntity.getProductOptionName();

                    List<ProductOptionEntity> productOptionEntities = productOptionRepository.findByOptionNumber(optionNumber);
                    List<OptionDetail> optionDetails = new ArrayList<>();
                    for (ProductOptionEntity productOptionEntity: productOptionEntities) {
                        OptionDetail optionDetail = new OptionDetail(productOptionEntity);
                        optionDetails.add(optionDetail);
                    }

                    Option option = new Option(productOptionName, optionDetails);
                    options.add(option);

                }

                FullProduct fullProduct = new FullProduct(productEntity, productImages, themes, options);
                products.add(fullProduct);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetProductListResponseDto.success(products);

    }

    @Override
    public ResponseEntity<? super GetProductResponseDto> getProduct(Integer productNumber) {

        ProductEntity productEntity = null;
        List<String> productImages = new ArrayList<>();
        List<String> themes = new ArrayList<>();
        List<Option> options = new ArrayList<>();

        try {

            productEntity = productRepository.findByProductNumber(productNumber);

            List<ProductImageEntity> productImageEntities = productImageRepository.findByProductNumber(productNumber);
            List<ThemaEntity> themaEntities = themaRepostiroy.findByProductNumber(productNumber);
            List<ProductMappingEntity> productMappingEntities = productMappingRepository.findByProductNumber(productNumber);

            // description: 이미지 리스트 생성 //
            for (ProductImageEntity productImageEntity: productImageEntities) productImages.add(productImageEntity.getProductImageUrl());

            // description: 테마 리스트 생성 //
            for (ThemaEntity themaEntity: themaEntities) themes.add(themaEntity.getThema());

            // description: 옵션 리스트 생성 //
            for (ProductMappingEntity productMappingEntity: productMappingEntities) {

                Integer optionNumber = productMappingEntity.getOptionNumber();
                String productOptionName = productMappingEntity.getProductOptionName();

                List<ProductOptionEntity> productOptionEntities = productOptionRepository.findByOptionNumber(optionNumber);
                List<OptionDetail> optionDetails = new ArrayList<>();
                for (ProductOptionEntity productOptionEntity: productOptionEntities) {
                    OptionDetail optionDetail = new OptionDetail(productOptionEntity);
                    optionDetails.add(optionDetail);
                }

                Option option = new Option(productOptionName, optionDetails);
                options.add(option);

            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetProductResponseDto.success(productEntity, productImages, themes, options);
    }

    @Override
    public ResponseEntity<ResponseDto> patchProduct(Integer productNumber, PatchProductRequestDto dto) {
        
        try {

            ProductEntity productEntity = productRepository.findByProductNumber(productNumber);
            if (productEntity == null) return ResponseDto.noExistProduct();
            productEntity.patch(dto);
            productRepository.save(productEntity);

            List<String> productImages = dto.getProductImages();
            List<ProductImageEntity> productImageEntities = new ArrayList<>();
            for (String productImage: productImages) {
                ProductImageEntity productImageEntity = new ProductImageEntity(productNumber, productImage);
                productImageEntities.add(productImageEntity);
            }
            productImageRepository.deleteByProductNumber(productNumber);
            productImageRepository.saveAll(productImageEntities);

            List<String> themes = dto.getThemes();
            if (themes != null) {
                List<ThemaEntity> themaEntities = new ArrayList<>();
                for (String theme: themes) {
                    ThemaEntity themaEntity = new ThemaEntity(theme, productNumber);
                    themaEntities.add(themaEntity);
                }
                themaRepostiroy.deleteByProductNumber(productNumber);
                themaRepostiroy.saveAll(themaEntities);
            }

            List<ProductMappingEntity> productMappingEntities = productMappingRepository.findByProductNumber(productNumber);
            for (ProductMappingEntity productMappingEntity: productMappingEntities) {
                Integer optionNumber = productMappingEntity.getOptionNumber();
                productOptionRepository.deleteByOptionNumber(optionNumber);
            }
            productMappingRepository.deleteAll(productMappingEntities);

            List<Option> options = dto.getOptions();
            for (Option option: options) {
                ProductMappingEntity productMappingEntity = new ProductMappingEntity(productNumber, option.getProductOptionName());
                productMappingRepository.save(productMappingEntity);
                Integer optionNumber = productMappingEntity.getOptionNumber();

                List<OptionDetail> optionDetails = option.getOptionDetails();
                List<ProductOptionEntity> productOptionEntities = new ArrayList<>();
                for (OptionDetail optionDetail: optionDetails) {
                    ProductOptionEntity productOptionEntity = new ProductOptionEntity(optionNumber, optionDetail);
                    productOptionEntities.add(productOptionEntity);
                }
                productOptionRepository.saveAll(productOptionEntities);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetProductPreviewListResponseDto> getProductPreviewList(Integer storeNumber) {

        List<PreviewProduct> previewProducts = new ArrayList<>();

        try {
            List<ProductEntity> productEntity = productRepository.findByStoreNumber(storeNumber);
            if(productEntity == null) return ResponseDto.noExistProduct();

            for(ProductEntity product: productEntity) {
                Integer productNumber = product.getProductNumber();
                String productName = product.getProductName();
                Integer productPrice = product.getProductPrice();
                boolean productToday = product.isProductToday();

                List<String> productImages = new ArrayList<>();
                List<ProductImageEntity> productImageEntities = productImageRepository.findByProductNumber(productNumber);
                for(ProductImageEntity productImageEntity: productImageEntities) {
                    productImages.add(productImageEntity.getProductImageUrl());
                }
                String imageUrl = productImages.isEmpty()? null : productImages.get(0);

                List<ThemaEntity> themaEntities = themaRepostiroy.findByProductNumber(productNumber);

                
                List<String> themes = new ArrayList<>();
                for(ThemaEntity themaEntity: themaEntities) {
                    themes.add(themaEntity.getThema());
                }
                PreviewProduct fullProduct = new PreviewProduct(productNumber, productName, productPrice, imageUrl, themes, productToday);
                previewProducts.add(fullProduct);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetProductPreviewListResponseDto.success(previewProducts);
    }

    @Override
    public ResponseEntity<? super GetProductDetailResponseDto> getOrderProductDetail(Integer productNumber, Integer storeNumber) {

        OrderProductDetail orderProductDetails = null;

        try {
            ProductEntity productEntity = productRepository.findByProductNumber(productNumber);
            if(productEntity == null) return ResponseDto.noExistProduct();

            StoreEntity storeEntity = storeRepository.findByStoreNumber(storeNumber);
            if(storeEntity == null) return ResponseDto.noExistStore();

            List<ProductImageEntity> productImageEntities = productImageRepository.findByProductNumber(productNumber);
            List<ThemaEntity> themaEntities = themaRepostiroy.findByProductNumber(productNumber);
            List<ProductMappingEntity> productMappingEntities = productMappingRepository.findByProductNumber(productNumber);

            List<String> productImages = new ArrayList<>();
            List<String> themes = new ArrayList<>();
            List<Option> options = new ArrayList<>();

            // description: 이미지 리스트 생성 //
            for (ProductImageEntity productImageEntity: productImageEntities) 
                productImages.add(productImageEntity.getProductImageUrl());

            // description: 테마 리스트 생성 //
            for (ThemaEntity themaEntity: themaEntities) themes.add(themaEntity.getThema());

            // description: 옵션 리스트 생성 //
            for (ProductMappingEntity productMappingEntity: productMappingEntities) {

                Integer optionNumber = productMappingEntity.getOptionNumber();
                String productOptionName = productMappingEntity.getProductOptionName();
                

                List<ProductOptionEntity> productOptionEntities = productOptionRepository.findByOptionNumber(optionNumber);
                List<OptionDetail> optionDetails = new ArrayList<>();
                for (ProductOptionEntity productOptionEntity: productOptionEntities) {
                    OptionDetail optionDetail = new OptionDetail(productOptionEntity);
                    optionDetails.add(optionDetail);
                }

                Option option = new Option(productOptionName, optionDetails);
                options.add(option);
            }

            orderProductDetails = new OrderProductDetail(productEntity, productImages, themes, options, storeEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetProductDetailResponseDto.success(orderProductDetails);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteProduct(Integer productNumber) {
        try {
            ProductEntity productEntity = productRepository.findByProductNumber(productNumber);
            if (productEntity == null) return ResponseDto.noExistProduct();

            productRepository.delete(productEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}

