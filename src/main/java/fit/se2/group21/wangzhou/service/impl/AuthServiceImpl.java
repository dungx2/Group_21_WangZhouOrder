package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.request.LoginRequest;
import fit.se2.group21.wangzhou.dto.request.RegisterRequest;
import fit.se2.group21.wangzhou.dto.response.UserResponse;
import fit.se2.group21.wangzhou.repository.UserRepository;
import fit.se2.group21.wangzhou.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Authentication service implementation
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse login(LoginRequest request) {
        // TODO: Implement login logic
        return new UserResponse();
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        // TODO: Implement registration logic
        return new UserResponse();
    }

    @Override
    public void verifyEmail(String email, String token) {
        // TODO: Implement email verification logic
    }

    @Override
    public void sendVerificationEmail(String email) {
        // TODO: Implement send verification email logic
    }
}

