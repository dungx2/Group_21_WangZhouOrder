package fit.se2.group21.wangzhou.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Price chart controller for interactive pricing API
 */
@RestController
@RequestMapping("/chart")
public class PriceChartController {

    @GetMapping("/data")
    public Object getPriceChartData() {
        // TODO: Return price chart data as JSON
        return new Object();
    }

    @PostMapping("/calculate")
    public Object calculatePrice(@RequestParam Double basePrice, @RequestParam Integer quantity) {
        // TODO: Calculate price based on quantity and other factors
        return new Object();
    }
}

