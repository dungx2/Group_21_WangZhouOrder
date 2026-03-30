package fit.se2.group21.wangzhou.service;

public interface JwtService {
    String generateToken(String email, String role);
    String extractRole(String token);
    String extractEmail(String token);
}
