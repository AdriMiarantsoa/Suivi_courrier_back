package adri.suivi_courrier.service;

import org.springframework.security.core.userdetails.UserDetails;

import adri.suivi_courrier.data.entity.Admin;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    String generateTokenAdmin(Admin userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenValidAdmin(String token, Admin userDetails);
}
