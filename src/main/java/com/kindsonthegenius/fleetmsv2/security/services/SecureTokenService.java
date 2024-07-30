package com.kindsonthegenius.fleetmsv2.security.services;


import com.kindsonthegenius.fleetmsv2.security.models.SecureToken;

public interface SecureTokenService {
    SecureToken createSecureToken();

    void saveSecureToken(SecureToken secureToken);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);
}
