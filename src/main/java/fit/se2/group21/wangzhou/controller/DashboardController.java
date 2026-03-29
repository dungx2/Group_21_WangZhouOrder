package fit.se2.group21.wangzhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String dashboard(Model model) {
        // TODO: Return role-based dashboard (customer/affiliate/admin)
        return "shared/dashboard";
    }
}

