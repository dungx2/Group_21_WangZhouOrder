package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.request.LoginRequest;
import fit.se2.group21.wangzhou.dto.request.RegisterRequest;
import fit.se2.group21.wangzhou.dto.response.UserResponse;

/**
 * Authentication service interface
 */
public interface AuthService {

    UserResponse login(LoginRequest request);

    UserResponse register(RegisterRequest request);

    void verifyEmail(String email, String token);
    void resendVerificationMail (String email);

    void sendVerificationEmail(String email);

    void authenticate(String email, String password);
}

