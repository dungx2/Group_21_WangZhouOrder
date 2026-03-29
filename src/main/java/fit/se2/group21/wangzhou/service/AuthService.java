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

    void sendVerificationEmail(String email);
}

