package fit.se2.group21.wangzhou.service;

import fit.se2.group21.wangzhou.dto.response.UserResponse;

/**
 * User service interface
 */
public interface UserService {

    UserResponse getUserById(Integer userId);

    UserResponse getUserByEmail(String email);

    UserResponse updateUser(Integer userId, UserResponse request);

    void lockAccount(Integer userId);

    void unlockAccount(Integer userId);
}

