package fit.se2.group21.wangzhou.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration with RBAC rules
 * Roles: CUSTOMER, AFFILIATE, ADMIN
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticatorConfig jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                // Public endpoints
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/catalog/**").permitAll()
                .requestMatchers("/chart/**").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/auth/verify-email", "/auth/resend-verification").permitAll()
                
                // Affiliate endpoints - require AFFILIATE role
                .requestMatchers("/affiliate/**").hasRole("AFFILIATE")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
                .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            .formLogin(form -> form
                .loginPage("/auth/login")
                 .loginProcessingUrl("/perform_login_ignored") // Chặn Spring Security chiếm quyền POST /auth/login
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .loginProcessingUrl("/auth/login")
//                    .defaultSuccessUrl("/dashboard", true)
//                    .failureUrl("/auth/login?error=true")
                    .permitAll()
            )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                        .deleteCookies("JWT_TOKEN") // Xóa Cookie khi đăng xuất
                        .invalidateHttpSession(true)
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**");
    }
}

