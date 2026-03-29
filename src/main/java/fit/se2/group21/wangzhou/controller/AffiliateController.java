package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.service.CommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Affiliate controller for referral and commission management
 */
@Controller
@RequestMapping("/affiliate")
@RequiredArgsConstructor
public class AffiliateController {

    private final CommissionService commissionService;

    @GetMapping("/dashboard")
    public String affiliateDashboard(Model model) {
        // TODO: Get affiliate stats and return affiliate/dashboard.html
        return "affiliate/dashboard";
    }

    @GetMapping("/referrals")
    public String referralLinks(Model model) {
        // TODO: Get referral links and return affiliate/referral-links.html
        return "affiliate/referral-links";
    }

    @GetMapping("/commissions")
    public String commissions(Model model) {
        // TODO: Get affiliate commissions and return affiliate/commissions.html
        return "affiliate/commissions";
    }

    @PostMapping("/referral-link/generate")
    public String generateReferralLink() {
        // TODO: Generate new referral link
        return "redirect:/affiliate/referrals";
    }

    @GetMapping("/payouts")
    public String payouts(Model model) {
        // TODO: Get payout history and return affiliate/payouts.html
        return "affiliate/payouts";
    }
}

