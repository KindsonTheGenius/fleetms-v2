package com.kindsonthegenius.fleetmsv2.security.services;

import com.kindsonthegenius.fleetmsv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetmsv2.exception.UnknownIdentifierException;

public interface UserAccountService {
    void forgottenPassword(final String email) throws UnknownIdentifierException;

    void updatePassword(final String password, final String token) throws InvalidTokenException, UnknownIdentifierException;
}
