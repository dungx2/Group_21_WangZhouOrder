package fit.se2.group21.wangzhou.entity;

import fit.se2.group21.wangzhou.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * User entity representing customers, affiliates, and admins
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "email_verified")
    private Boolean emailVerified = false;

    @Column(name = "account_locked")
    private Boolean accountLocked = false;

    @Column(name = "referral_code")
    private String referralCode;

    // ...existing relationships...
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commission> commissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ConversationRead> conversationReads;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security yêu cầu Role phải có tiền tố "ROLE_" khi dùng với hasRole()
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email; // Sử dụng email làm tài khoản đăng nhập
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked; // Liên kết với field accountLocked của bạn
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.emailVerified; // Chỉ cho đăng nhập nếu đã verify email (tuỳ logic dự án)
    }
}


