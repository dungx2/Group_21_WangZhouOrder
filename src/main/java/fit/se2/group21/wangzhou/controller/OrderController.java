package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.dto.request.OrderRequest;
import fit.se2.group21.wangzhou.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Order controller for order management
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        // TODO: Get customer's orders and return orders.html
        return "customer/orders";
    }

    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Integer id, Model model) {
        // TODO: Get order by ID and return order-detail.html
        return "customer/order-detail";
    }

    @GetMapping("/create")
    public String createOrderPage(Model model) {
        // TODO: Return order-create.html
        return "customer/order-create";
    }

    @PostMapping
    public String createOrder(@ModelAttribute OrderRequest request, Model model) {
        // TODO: Create order and redirect to detail page
        return "redirect:/orders/{id}";
    }

    @PostMapping("/{id}/clone")
    public String cloneOrder(@PathVariable Integer id) {
        // TODO: Clone order (Prototype Pattern)
        return "redirect:/orders";
    }

    @GetMapping("/{id}/checkout")
    public String checkoutPage(@PathVariable Integer id, Model model) {
        // TODO: Return cart-checkout.html (two-phase payment)
        return "customer/cart-checkout";
    }
}

