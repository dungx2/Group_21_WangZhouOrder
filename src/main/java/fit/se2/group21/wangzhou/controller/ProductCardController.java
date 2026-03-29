package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.service.ProductCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Product catalog controller for community product cards
 */
@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class ProductCardController {

    private final ProductCardService productCardService;

    @GetMapping
    public String catalog(Pageable pageable, Model model) {
        // TODO: Get public product cards with pagination and return catalog.html
        return "customer/catalog";
    }

    @GetMapping("/category/{categoryId}")
    public String categoryProducts(@PathVariable Integer categoryId, Pageable pageable, Model model) {
        // TODO: Get products by category and return catalog.html
        return "customer/catalog";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        // TODO: Get product card by ID and return product-card-detail.html
        return "customer/product-card-detail";
    }

    @PostMapping("/{id}/view")
    public String incrementViewCount(@PathVariable Integer id) {
        // TODO: Increment view count
        return "redirect:/catalog/{id}";
    }
}

