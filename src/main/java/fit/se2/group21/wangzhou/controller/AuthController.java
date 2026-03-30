package fit.se2.group21.wangzhou.controller;

import fit.se2.group21.wangzhou.dto.request.LoginRequest;
import fit.se2.group21.wangzhou.dto.request.RegisterRequest;
import fit.se2.group21.wangzhou.dto.response.UserResponse;
import fit.se2.group21.wangzhou.service.AuthService;
import fit.se2.group21.wangzhou.service.JwtService;
import fit.se2.group21.wangzhou.service.impl.JwtServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Authentication controller for login/register
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        // TODO: Return login.html view
        return "auth/login";
    }
    @PostMapping("/login")
    public String loginPage(@ModelAttribute LoginRequest request,
                            HttpServletResponse response,
                            RedirectAttributes ra,
                            HttpSession session) {
        // TODO:
        try {
            UserResponse userResponse = authService.login(request);

            // 2. TẠO TOKEN
            String token = jwtService.generateToken(userResponse.getEmail(), userResponse.getRole().name());
            // 3. LƯU VÀO COOKIE
            Cookie jwtCookie = new Cookie("JWT_TOKEN", token);
            jwtCookie.setHttpOnly(true); // Chống XSS
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60); // 1 ngày

            response.addCookie(jwtCookie);
            // 4. Lưu user vào session để hiển thị trên UI
            session.setAttribute("user", userResponse);

            return "redirect:/auth/login?success=true";

        } catch (Exception e) {
            // Nếu thất bại, quay lại trang login với thông báo lỗi
            ra.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/auth/login";
        }

    }


    @GetMapping("/register")
    public String registerPage(Model model) {
        // 1. Cung cấp các biến th:text mà HTML đang yêu cầu
        model.addAttribute("pageTitle", "Sign Up - Guangzhou Direct");
        model.addAttribute("appName", "Guangzhou Direct");
        model.addAttribute("heroTitle", "Professional Sourcing Platform.");
        model.addAttribute("heroSubtitle", "Connect directly with leading manufacturers in Guangzhou.");
        model.addAttribute("formTitle", "Create new account");
        model.addAttribute("formSubtitle", "Start your journey to optimize your sourcing today.");

        // Các biến stats (nếu bạn muốn hiển thị số liệu thực)
        model.addAttribute("stat1Value", "10k+");
        model.addAttribute("stat1Label", "Suppliers");
        model.addAttribute("stat2Value", "24/7");
        model.addAttribute("stat2Label", "Tech Support");

        // 2. Đối tượng để binding form (th:object="${registerRequest}")
        model.addAttribute("registerRequest", new RegisterRequest());

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerRequest") RegisterRequest request,
                           RedirectAttributes redirectAttributes) {
        try {
            // 3. Gọi logic đăng ký từ Service
            authService.register(request);

            // 4. Nếu thành công, truyền thông báo và chuyển hướng
            redirectAttributes.addFlashAttribute("registerSuccess", "Please check your email to verify your account.");
            return "redirect:/auth/register"; // Hoặc redirect tới trang thông báo

        } catch (Exception e) {
            // 5. Nếu lỗi (email đã tồn tại,...), quay lại trang đăng ký với thông báo lỗi
            redirectAttributes.addFlashAttribute("registerError", e.getMessage());
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/verify-email")
    public String verifyEmailPage(@RequestParam(required = false) String email,
                                  @RequestParam(required = false) String token,
                                  Model model,
                                  RedirectAttributes ra) {
        // TODO: Return email-reverification.html view + Implement email verification
        // Click từ Link Email (Có đủ email và token)
        if (email != null && token != null) {
            try {
                authService.verifyEmail(email, token);
                ra.addFlashAttribute("message", "Xác thực thành công! Bạn có thể đăng nhập ngay.");
                return "redirect:/auth/login"; // Click xong về trang Login luôn
            } catch (Exception e) {
                // Nếu link sai, hiện lỗi ngay tại trang verify
                model.addAttribute("error", "Link xác thực không hợp lệ: " + e.getMessage());
            }
        }
        return "auth/email-reverification";
    }

        @PostMapping("/resend-verification")
    public String resendVerification(@RequestParam String email, RedirectAttributes ra) {
        try {
            authService.resendVerificationMail(email);
            ra.addFlashAttribute("message", "Một email xác thực mới đã được gửi!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Không thể gửi lại mail: " + e.getMessage());
        }
        // Quay lại trang thông báo xác thực kèm theo email trong params
        return "redirect:/auth/verify-email?email=" + email;
    }
}

