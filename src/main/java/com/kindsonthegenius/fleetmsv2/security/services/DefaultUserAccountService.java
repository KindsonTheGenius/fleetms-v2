package com.kindsonthegenius.fleetmsv2.security.services;

import com.kindsonthegenius.fleetmsv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetmsv2.exception.UnknownIdentifierException;
import com.kindsonthegenius.fleetmsv2.mailing.EmailService;
import com.kindsonthegenius.fleetmsv2.mailing.ForgotPasswordEmailContext;
import com.kindsonthegenius.fleetmsv2.security.models.SecureToken;
import com.kindsonthegenius.fleetmsv2.security.models.User;
import com.kindsonthegenius.fleetmsv2.security.repositories.SecureTokenRepository;
import com.kindsonthegenius.fleetmsv2.security.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Objects;

@Service
public class DefaultUserAccountService implements UserAccountService {

    @Value("${site.base.url.https}")
    private String baseURL;

    @Autowired
    private SecureTokenService secureTokenService;

    @Autowired
    private SecureTokenRepository secureTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void forgottenPassword(String email) throws UnknownIdentifierException {
        User user = userRepository.findByEmail(email);
        sendResetPasswordEmail(user);
    }

    @Override
    public void updatePassword(String password, String token) throws InvalidTokenException, UnknownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);

        if (Objects.isNull(secureToken) | !StringUtils.equals(token, secureToken.getToken()) | secureToken.isExpired()) {
            throw new InvalidTokenException("Token does not exist");
        }

        User user = userRepository.getById(secureToken.getUser().getId());
        if (Objects.isNull(user)) {
            throw new UnknownIdentifierException("User does not exist for this user");
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        secureTokenService.removeToken(secureToken);
    }

    protected void sendResetPasswordEmail(User user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);

        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());

        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
