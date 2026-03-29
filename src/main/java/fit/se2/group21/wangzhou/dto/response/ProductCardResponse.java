package fit.se2.group21.wangzhou.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Product card response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCardResponse {

    private Integer id;

    private String title;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Integer viewsCount;

    private Integer likesCount;

    private String categoryName;
}

