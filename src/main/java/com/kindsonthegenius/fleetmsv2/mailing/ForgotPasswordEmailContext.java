package com.kindsonthegenius.fleetmsv2.mailing;


import com.kindsonthegenius.fleetmsv2.security.models.User;
import org.springframework.web.util.UriComponentsBuilder;

public class ForgotPasswordEmailContext extends AbstractEmailContext {
    private String token;

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        User user = (User) context; // we pass the customer informati
        put("firstName", user.getFirstname());
        setTemplateLocation("mailing/email-verification");
        setSubject("Forgotten Password");
        setFrom("no-reply@kttpro.com");
        setTo(user.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/passwordChange").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
