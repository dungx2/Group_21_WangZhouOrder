package fit.se2.group21.wangzhou.dto.request;

import fit.se2.group21.wangzhou.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Register request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;
}

