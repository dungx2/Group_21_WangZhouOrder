package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.request.OrderRequest;
import fit.se2.group21.wangzhou.dto.response.OrderResponse;
import fit.se2.group21.wangzhou.enums.OrderStatus;

import java.util.List;

/**
 * Order service interface (State Pattern for status transitions)
 */
public interface OrderService {

    OrderResponse createOrder(Integer customerId, OrderRequest request);

    OrderResponse getOrderById(Integer orderId);

    List<OrderResponse> getOrdersByCustomer(Integer customerId);

    OrderResponse updateOrderStatus(Integer orderId, OrderStatus newStatus);

    OrderResponse cloneOrder(Integer orderId);

    void cancelOrder(Integer orderId);
}

