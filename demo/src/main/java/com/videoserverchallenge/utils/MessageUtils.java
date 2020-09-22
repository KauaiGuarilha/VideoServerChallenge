package com.videoserverchallenge.utils;

import com.videoserverchallenge.model.domain.EMessage;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    private static MessageSource MESSAGE_SOURCE;

    /**
     * Get an internationalized message from your key
     *
     * @param message message key
     * @param args The arguments for assembling the message
     * @return mounted message
     */
    public static String getMessage(EMessage message, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return MESSAGE_SOURCE.getMessage(message.getMessage(), args, locale);
    }
}
