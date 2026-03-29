package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.ProductCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ProductCard entity
 */
@Repository
public interface ProductCardRepository extends JpaRepository<ProductCard, Integer> {

    Page<ProductCard> findByIsPublicTrue(Pageable pageable);

    Page<ProductCard> findByCategoryIdAndIsPublicTrue(Integer categoryId, Pageable pageable);
}

