package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.response.UserResponse;
import fit.se2.group21.wangzhou.repository.UserRepository;
import fit.se2.group21.wangzhou.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * User service implementation
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse getUserById(Integer userId) {
        // TODO: Implement get user by ID
        return new UserResponse();
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        // TODO: Implement get user by email
        return new UserResponse();
    }

    @Override
    public UserResponse updateUser(Integer userId, UserResponse request) {
        // TODO: Implement update user
        return new UserResponse();
    }

    @Override
    public void lockAccount(Integer userId) {
        // TODO: Implement account locking
    }

    @Override
    public void unlockAccount(Integer userId) {
        // TODO: Implement account unlocking
    }
}

