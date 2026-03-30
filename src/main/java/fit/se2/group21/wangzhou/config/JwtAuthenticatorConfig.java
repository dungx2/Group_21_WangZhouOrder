package fit.se2.group21.wangzhou.config;

import fit.se2.group21.wangzhou.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticatorConfig extends OncePerRequestFilter {
     private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    String token = null;
        // 1. Trích xuất Token từ Cookie "JWT_TOKEN"
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()){
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
            }
        }
    }
        // 2. Nếu không có token, bỏ qua , đi tới Filter tiếp theo
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            String email = jwtService.extractEmail(token);
            String role = jwtService.extractRole(token);
            // Nếu Email hợp lệ và SecurityContext chưa có xác thực
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Spring Security yêu cầu Role phải có tiền tố "ROLE_" để khớp với .hasRole()
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(authority);

                // Tạo đối tượng Authentication
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Lưu thông tin chi tiết của Request (IP, Session ID...)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Xác lập người dùng đã đăng nhập vào hệ thống
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        catch (Exception e){
            // Nếu token sai hoặc hết hạn, xóa SecurityContext để đảm bảo an toàn
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

}
