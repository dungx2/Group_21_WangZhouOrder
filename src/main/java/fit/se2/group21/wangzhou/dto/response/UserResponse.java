package fit.se2.group21.wangzhou.dto.response;

import fit.se2.group21.wangzhou.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

    private Boolean emailVerified;

    private String referralCode;
}

