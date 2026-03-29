package fit.se2.group21.wangzhou.repository;

import fit.se2.group21.wangzhou.entity.Order;
import fit.se2.group21.wangzhou.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order entity
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByCustomerId(Integer customerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}

