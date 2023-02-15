package com.learn.multilanguage.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomMessageSource {

    private final MessageSource messageSource;

    public CustomMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getCurrentLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        // set
        // Accept-Language header np for nepal and ru for russian
        if (locale.getLanguage().equalsIgnoreCase("np")) {
            locale = new Locale("np", "Nepal");
        }
        if(locale.getLanguage().equalsIgnoreCase("ru")){
            locale = new Locale("ru", "Russian");
        }
        return locale;
    }

    public String get(String code) {
        return messageSource.getMessage(code, null, getCurrentLocale());
    }

    public String get(String code,Object... objects){
        return messageSource.getMessage(code, objects, getCurrentLocale());
    }

}
