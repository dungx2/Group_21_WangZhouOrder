package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.request.LoginRequest;
import fit.se2.group21.wangzhou.dto.request.RegisterRequest;
import fit.se2.group21.wangzhou.dto.response.UserResponse;
import fit.se2.group21.wangzhou.entity.User;
import fit.se2.group21.wangzhou.enums.Role;
import fit.se2.group21.wangzhou.repository.UserRepository;
import fit.se2.group21.wangzhou.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Authentication service implementation
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {
        // TODO: Implement register logic
        // 1. Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use!");
        }
        // 2. Map DTO to Entity
        User user = modelMapper.map(request, User.class);
        // 3. Hash password and save user
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4. Set roles
        if (request.getRole() == Role.ADMIN) {
            throw new RuntimeException("Unauthorized role selection!");
        }
        user.setRole(request.getRole());
        //xac thuc moi dc login
        user.setEmailVerified(false);
        user.setAccountLocked(false);
        // 5. Save and return
        User savedUser = userRepository.save(user);
        try {
            this.sendVerificationEmail(savedUser.getEmail());
        } catch (Exception e) {
            // Log lỗi thay để User vẫn được tạo
            System.err.println("Gửi mail thất bại cho: " + savedUser.getEmail() + " - " + e.getMessage());

        }
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public void authenticate(String email, String password) {
        // 1. Tìm người dùng
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email hoặc mật khẩu không chính xác"));

        // 2. Kiểm tra trạng thái xác thực email (Logic quan trọng cho dự án của bạn)
        if (!Boolean.TRUE.equals(user.getEmailVerified())) {
            throw new BadCredentialsException("Tài khoản chưa được xác thực email.");
        }

        // 3. Kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Email hoặc mật khẩu không chính xác");
        }

        // 4. Kiểm tra tài khoản có bị khóa không
        if (Boolean.TRUE.equals(user.getAccountLocked())) {
            throw new BadCredentialsException("Tài khoản đang bị khóa.");
        }
    }

    @Override
    public void resendVerificationMail (String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại trong hệ thống."));

        if (Boolean.TRUE.equals(user.getEmailVerified())) {
            throw new RuntimeException("Tài khoản này đã được xác thực rồi.");
        }

        // Gọi lại hàm gửi mail (hàm se tạo token mới + lưu vào DB)
        this.sendVerificationEmail(email);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        // TODO: Implement login logic
        this.authenticate(request.getEmail(), request.getPassword());
        // 2. Lấy thông tin User (Lúc này User tồn tại vì đã qua bước authenticate)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Lỗi hệ thống: Không tìm thấy người dùng"));
        // 3. Map Entity sang UserResponse DTO và trả về
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public void verifyEmail(String email, String token) {
        // TODO: Implement email verification logic
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không hợp lệ"));
        if (user.getVerificationToken() == null || !user.getVerificationToken().equals(token)){
            throw new RuntimeException("Mã xác thực không chính xác hoặc đã được sử dụng.");
        }
        else if (user.getTokenExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Mã xác thực đã hết hạn. Vui lòng nhấn 'Gửi lại mã'.");
        }
        else {
            user.setEmailVerified(true);
            //xoa token sau khi xac thuc thanh cong
            user.setVerificationToken(null);
            userRepository.save(user);
        }

    }

    @Override
    public void sendVerificationEmail(String email) {
        // TODO: Implement send verification email logic
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Không tìm thấy người dùng với email: " + email));
        // Tạo token ngẫu nhiên
        String token = UUID.randomUUID().toString();
        // Thiết lập thời gian hết hạn
        user.setTokenExpiryTime(LocalDateTime.now().plusMinutes(1));
        user.setVerificationToken(token);
        userRepository.save(user);

        // Xây dựng nội dung email
        String verifyLink = "http://localhost:8080/auth/verify-email?email=" + email + "&token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Wangzhou Order] Xác thực tài khoản");
        message.setText("Xin Chào " + user.getFirstName() + ",\n\n" +
                "Vui lòng nhấn vào link sau để kích hoạt tài khoản của bạn:\n" +
                verifyLink + "\n\nLink này sẽ hết hiệu lực sau 1p.");

        mailSender.send(message);
    }
}

