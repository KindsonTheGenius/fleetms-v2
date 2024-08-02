package com.kindsonthegenius.fleetmsv2.security.controllers;

import com.kindsonthegenius.fleetmsv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetmsv2.exception.UnknownIdentifierException;
import com.kindsonthegenius.fleetmsv2.security.models.ResetPasswordData;
import com.kindsonthegenius.fleetmsv2.security.services.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordResetController {

    private String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/passwordRequest") // Display form with only email field
    public String resetPassword(final ResetPasswordData forgotPasswordForm, RedirectAttributes redirAttr) {
        try {
            userAccountService.forgottenPassword(forgotPasswordForm.getEmail()); // Send email
        } catch (UnknownIdentifierException e) {
            // log the error
            redirAttr.addFlashAttribute("error",
                    messageSource.getMessage("error", null, LocaleContextHolder.getLocale())
            );
        }
        redirAttr.addFlashAttribute("message",
                messageSource.getMessage("user.forgotpwd.msg", null, LocaleContextHolder.getLocale())
        );
        return REDIRECT_LOGIN;
    }

    @GetMapping("/passwordChange")
    public String changePassword(@RequestParam(required = false) String token, final RedirectAttributes redirAttr, final Model model) {
        if (StringUtils.isEmpty(token)) {
            redirAttr.addFlashAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale())
            );
            return REDIRECT_LOGIN;
        }
        ResetPasswordData data = new ResetPasswordData();
        data.setToken(token);
        setResetPasswordForm(model, data); // add the resetPassword form to the model to send to the template
        return "security/changePassword";
    }


    @PostMapping("/passwordChange")
    public String changePassword(final ResetPasswordData data, Model model) {
        try {
            userAccountService.updatePassword(data.getPassword(), data.getToken());
        } catch (InvalidTokenException | UnknownIdentifierException e) {
            e.printStackTrace();
            model.addAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.invalid.token", null, LocaleContextHolder.getLocale()));
            return "security/changePassword";
        }
        model.addAttribute("passwordUpdatedMsg",
                messageSource.getMessage("user.password.updated.msg", null, LocaleContextHolder.getLocale()));
        setResetPasswordForm(model, new ResetPasswordData());
        return "security/passwordChangeSuccessful";
    }

    private void setResetPasswordForm(Model model, ResetPasswordData data) {
        model.addAttribute("forgotPassword", data);
    }

}
