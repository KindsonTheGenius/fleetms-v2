package com.kindsonthegenius.fleetmsv2.security.controllers;

import com.kindsonthegenius.fleetmsv2.exception.InvalidTokenException;
import com.kindsonthegenius.fleetmsv2.security.services.UserService;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/verify")
    public String verifyUser(@RequestParam String token, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(token)) {
            redirectAttributes.addFlashAttribute("tokenError", "Token is invlid");
            return REDIRECT_LOGIN;
        }
        try {
            userService.verifyUser(token);
        } catch (InvalidTokenException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("tokenError", "Token is invlid");
            return REDIRECT_LOGIN;
        }

        redirectAttributes.addFlashAttribute("message",
                messageSource.getMessage("verification.email.msg", null, LocaleContextHolder.getLocale()));
        return REDIRECT_LOGIN;
    }

}
