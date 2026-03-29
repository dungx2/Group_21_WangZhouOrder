package fit.se2.group21.wangzhou.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * Dashboard controller - redirects by role
 */
@Controller
public class DashboardController {

    @GetMapping("/")
    public String index(Model model) {
        // TODO: Redirect to appropriate dashboard based on user role
        return "redirect:/dashboard";
    }

@GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        // Nếu chưa đăng nhập (dù SecurityConfig đã chặn, nhưng cứ check cho an toàn)
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Kiểm tra Role của User hiện tại và điều hướng
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isAffiliate = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_AFFILIATE"));

        if (isAdmin) {
            // TODO: Redirect tới controller admin hoặc render thẳng file thymeleaf của admin
            return "redirect:/admin/dashboard"; 
        } else if (isAffiliate) {
            // TODO: Redirect tới controller affiliate 
            return "redirect:/affiliate/dashboard";
        } else {
            // Mặc định là CUSTOMER
            // TODO: Khách hàng thì đẩy ra trang chủ hoặc trang mua sắm
            return "redirect:/catalog"; 
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminDashboard() {
        return "dashboard/admin"; // render file admin.html
    }

    // Chỉ AFFILIATE mới được gọi API này
    @PreAuthorize("hasRole('AFFILIATE')")
    @GetMapping("/earnings")
    public String affiliateEarnings() {
        return "dashboard/earnings";
    }
    
    // Khách hàng và Affiliate đều có thể mua hàng
    @PreAuthorize("hasAnyRole('CUSTOMER', 'AFFILIATE')")
    @GetMapping("/shop")
    public String shopPage() {
        return "shop/index";
    }
}

