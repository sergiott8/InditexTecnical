package com.inditex.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;


import java.util.Locale;

/**
 * Helper to access to the i18n messagges.
 *
 * @author Sergio Torres 2022
 */
@Component
public class Messages {

    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    /**
     * Devuelve el texto de un mensaje internacionalizado.
     *
     * @param code Código del mensaje. Si no existe lanzar la excepción
     *             org.springframework.context.NoSuchMessageException, así que ojo.
     * @return Texto del mensaje.
     */
    public String get(String code) {
        return accessor.getMessage(code);
    }

}