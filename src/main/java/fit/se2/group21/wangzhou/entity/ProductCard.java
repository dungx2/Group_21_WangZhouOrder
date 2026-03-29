package fit.se2.group21.wangzhou.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Digital product card for the community catalog
 */
@Entity
@Table(name = "product_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "views_count")
    private Integer viewsCount = 0;

    @Column(name = "likes_count")
    private Integer likesCount = 0;
}

