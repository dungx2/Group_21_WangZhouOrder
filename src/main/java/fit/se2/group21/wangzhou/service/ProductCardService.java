package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.response.ProductCardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Product card service interface
 */
public interface ProductCardService {

    Page<ProductCardResponse> getPublicProductCards(Pageable pageable);

    Page<ProductCardResponse> getProductCardsByCategory(Integer categoryId, Pageable pageable);

    ProductCardResponse getProductCardById(Integer cardId);

    ProductCardResponse createProductCard(ProductCardResponse request);

    ProductCardResponse updateProductCard(Integer cardId, ProductCardResponse request);

    void deleteProductCard(Integer cardId);

    void incrementViewCount(Integer cardId);
}

