package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.dto.request.LoginRequest;
import fit.se2.group21.wangzhou.dto.request.RegisterRequest;
import fit.se2.group21.wangzhou.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller for login/register
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        // TODO: Return login.html view
        return "auth/login";
    }


    @GetMapping("/register")
    public String registerPage(Model model) {
        // TODO: Return register.html view
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        // TODO: Implement registration logic
        return "redirect:/auth/verify-email";
    }

    @GetMapping("/verify-email")
    public String verifyEmailPage() {
        // TODO: Return email-verification.html view
        return "auth/email-verification";
    }

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestParam String email, @RequestParam String token) {
        // TODO: Implement email verification
        return "redirect:/auth/login";
    }
}

