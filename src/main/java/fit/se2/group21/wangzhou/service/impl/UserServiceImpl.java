package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.dto.response.UserResponse;
import fit.se2.group21.wangzhou.entity.User;
import fit.se2.group21.wangzhou.repository.UserRepository;
import fit.se2.group21.wangzhou.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service implementation
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse getUserById(Integer userId) {
        // TODO: Implement get user by ID
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    }

    @Override
    public UserResponse getUserByEmail(String email) {
        // TODO: Implement get user by email
        return new UserResponse();
    }

    @Override
    public UserResponse updateUser(Integer userId, UserResponse request) {
        // TODO: Implement update user
        return userRepository.findById(userId).map(user -> {
            // Chỉ cập nhật thông tin cá nhân, KHÔNG cho phép đổi Email hoặc Role ở đây
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhone(request.getPhone());

            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserResponse.class);
        }).orElseThrow(() -> new RuntimeException("User not found for update"));
    }

    @Override
    public void lockAccount(Integer userId) {
        // TODO: Implement account locking
        userRepository.findById(userId).ifPresentOrElse(user -> {
            // Sử dụng đúng field account_locked trong Entity
            user.setAccountLocked(true);
            userRepository.save(user);
            // log.info("Tài khoản {} đã bị khóa.", user.getEmail());
        }, () -> {
            throw new RuntimeException("User not found with ID: " + userId);
        });
    }

    @Override
    public void unlockAccount(Integer userId) {
        // TODO: Implement account unlocking
        userRepository.findById(userId).ifPresentOrElse(user -> {
            // Sử dụng đúng field account_locked trong Entity
            user.setAccountLocked(false);
            userRepository.save(user);
            // log.info("Tài khoản {} đã mo khóa.", user.getEmail());
        }, () -> {
            throw new RuntimeException("User not found with ID: " + userId);
        });
    }
}

