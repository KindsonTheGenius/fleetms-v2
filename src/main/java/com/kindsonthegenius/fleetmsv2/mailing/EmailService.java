package com.kindsonthegenius.fleetmsv2.mailing;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;

}
